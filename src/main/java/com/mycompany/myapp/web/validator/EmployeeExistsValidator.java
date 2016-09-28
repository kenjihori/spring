package com.mycompany.myapp.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.domain.model.Employee;
import com.mycompany.myapp.service.EmployeeService;

public class EmployeeExistsValidator implements ConstraintValidator<EmployeeExists, Object> {

    @Autowired
    EmployeeService employeeService;
    
    private String message;
    private String companyCode;
    private String employeeCode;
    
    @Override
    public void initialize(EmployeeExists annotation) {
        this.message = annotation.message();
        this.companyCode = annotation.companyCode();
        this.employeeCode = annotation.employeeCode();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        String companyCodeValue = (String)beanWrapper.getPropertyValue(companyCode);
        String employeeCodeValue = (String)beanWrapper.getPropertyValue(employeeCode);
        
        // 会社コード、社員コードが入力されていない場合はチェックしない（フォームクラスのNotNullでチェックする）
        if(companyCodeValue == null || employeeCodeValue == null) {
            return true;
        }
        // 社員マスタを検索
        Employee employee = employeeService.findEmployee(companyCodeValue, employeeCodeValue);
        // 社員が存在する場合はチェックOK、存在しない場合はチェックNGとする
        if(employee != null) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(companyCode).addConstraintViolation();
            context.buildConstraintViolationWithTemplate("").addPropertyNode(employeeCode).addConstraintViolation();
            return false;
        }
        
    }
}
