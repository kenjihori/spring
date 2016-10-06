package com.mycompany.myapp.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.mycompany.myapp.common.security.LoginUserInfo;

@Aspect
@Component
public class MapperAspect {

    @Before("execution(* com.mycompany.myapp.domain.mapper.*Mapper.create*(..)) || " + 
            "execution(* com.mycompany.myapp.domain.mapper.*Mapper.update*(..))")
    public void setCommonProperty(JoinPoint jp) throws Throwable {
        
        System.out.println("AOP MapperAspect start:" + jp.getSignature());
        
        // Mapperのメソッド名を取得
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        
        // 認証情報の取得
        LoginUserInfo loginUserInfo = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof LoginUserInfo) {
            loginUserInfo = LoginUserInfo.class.cast(authentication.getPrincipal());
        }
        if(loginUserInfo != null) {
            System.out.println("loginClerkInfo:" + loginUserInfo);
        } else {
            System.out.println("loginClerkInfo null");
        }
        
        // 現在日時の取得
        Date now = new Date();
        
        // Mapperの第一引数を取得
        Object[] args = jp.getArgs();
        Object dto = args[0];
        
        // create*メソッドは作成者・作成日時・更新者・更新日時をセット
        if(methodName.startsWith("create")) {
            setCreatedProperty(loginUserInfo, now, dto);
            setUpdatedProperty(loginUserInfo, now, dto);
        // update*メソッドは更新者・更新日時をセット
        } else if(methodName.startsWith("update")) {
            setUpdatedProperty(loginUserInfo, now, dto);
        }
        
        System.out.println("AOP MapperAspect end");
        
    }
    
    // 作成者IDと作成日時をセット
    private void setCreatedProperty(LoginUserInfo loginUserInfo, Date now, Object dto) throws Throwable {
        
        // Mapperの引数にsetCreatedByIdメソッドがある場合、認証情報をセット
        Method setCreatedById = ReflectionUtils.findMethod(dto.getClass(), "setCreatedById", String.class);
        if(setCreatedById != null) {
            setCreatedById.invoke(dto, loginUserInfo.getId());
        }
        
        // Mapperの引数にsetCreatedTimestampメソッドがある場合、現在日時をセット
        Method setCreatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setCreatedTimestamp", Date.class);
        if(setCreatedTimestamp != null) {
            setCreatedTimestamp.invoke(dto, now);
        }
        
    }
    
    // 更新者IDと更新日時をセット
    private void setUpdatedProperty(LoginUserInfo loginUserInfo, Date now, Object dto) throws Throwable {
        
        // Mapperの引数にsetUpdatedByIdメソッドがある場合、認証情報をセット
        Method setUpdatedById  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedById", String.class);
        if(setUpdatedById != null) {
            setUpdatedById.invoke(dto, loginUserInfo.getId());
        }
        
        // Mapperの引数にsetUpdatedTimestampメソッドがある場合、現在日時をセット
        Method setUpdatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedTimestamp", Date.class);
        if(setUpdatedTimestamp != null) {
            setUpdatedTimestamp.invoke(dto, now);
        }
        
    }
    
}
