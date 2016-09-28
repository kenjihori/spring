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
        
        // ��ЃR�[�h�A�Ј��R�[�h�����͂���Ă��Ȃ��ꍇ�̓`�F�b�N���Ȃ��i�t�H�[���N���X��NotNull�Ń`�F�b�N����j
        if(companyCodeValue == null || employeeCodeValue == null) {
            return true;
        }
        // �Ј��}�X�^������
        Employee employee = employeeService.findEmployee(companyCodeValue, employeeCodeValue);
        // �Ј������݂���ꍇ�̓`�F�b�NOK�A���݂��Ȃ��ꍇ�̓`�F�b�NNG�Ƃ���
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
