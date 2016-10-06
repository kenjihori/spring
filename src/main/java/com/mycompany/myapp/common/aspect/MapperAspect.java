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
        
        // Mapper�̃��\�b�h�����擾
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        
        // �F�؏��̎擾
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
        
        // ���ݓ����̎擾
        Date now = new Date();
        
        // Mapper�̑��������擾
        Object[] args = jp.getArgs();
        Object dto = args[0];
        
        // create*���\�b�h�͍쐬�ҁE�쐬�����E�X�V�ҁE�X�V�������Z�b�g
        if(methodName.startsWith("create")) {
            setCreatedProperty(loginUserInfo, now, dto);
            setUpdatedProperty(loginUserInfo, now, dto);
        // update*���\�b�h�͍X�V�ҁE�X�V�������Z�b�g
        } else if(methodName.startsWith("update")) {
            setUpdatedProperty(loginUserInfo, now, dto);
        }
        
        System.out.println("AOP MapperAspect end");
        
    }
    
    // �쐬��ID�ƍ쐬�������Z�b�g
    private void setCreatedProperty(LoginUserInfo loginUserInfo, Date now, Object dto) throws Throwable {
        
        // Mapper�̈�����setCreatedById���\�b�h������ꍇ�A�F�؏����Z�b�g
        Method setCreatedById = ReflectionUtils.findMethod(dto.getClass(), "setCreatedById", String.class);
        if(setCreatedById != null) {
            setCreatedById.invoke(dto, loginUserInfo.getId());
        }
        
        // Mapper�̈�����setCreatedTimestamp���\�b�h������ꍇ�A���ݓ������Z�b�g
        Method setCreatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setCreatedTimestamp", Date.class);
        if(setCreatedTimestamp != null) {
            setCreatedTimestamp.invoke(dto, now);
        }
        
    }
    
    // �X�V��ID�ƍX�V�������Z�b�g
    private void setUpdatedProperty(LoginUserInfo loginUserInfo, Date now, Object dto) throws Throwable {
        
        // Mapper�̈�����setUpdatedById���\�b�h������ꍇ�A�F�؏����Z�b�g
        Method setUpdatedById  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedById", String.class);
        if(setUpdatedById != null) {
            setUpdatedById.invoke(dto, loginUserInfo.getId());
        }
        
        // Mapper�̈�����setUpdatedTimestamp���\�b�h������ꍇ�A���ݓ������Z�b�g
        Method setUpdatedTimestamp  = ReflectionUtils.findMethod(dto.getClass(), "setUpdatedTimestamp", Date.class);
        if(setUpdatedTimestamp != null) {
            setUpdatedTimestamp.invoke(dto, now);
        }
        
    }
    
}
