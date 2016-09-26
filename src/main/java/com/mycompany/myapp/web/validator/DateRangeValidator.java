package com.mycompany.myapp.web.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.myapp.domain.model.Item;
import com.mycompany.myapp.service.ItemService;

public class DateRangeValidator implements ConstraintValidator<DateRange, Object> {

    private String message;
    private String dateFrom;
    private String dateTo;
    
    @Override
    public void initialize(DateRange annotation) {
        this.dateFrom = annotation.dateFrom();
        this.dateTo = annotation.dateTo();
        this.message = annotation.message();
    }
    
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Date dateFromValue = (Date)beanWrapper.getPropertyValue(dateFrom);
        Date dateToValue = (Date)beanWrapper.getPropertyValue(dateTo);
        if(dateFromValue == null || dateToValue == null || dateFromValue.compareTo(dateToValue) <= 0) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(dateFrom).addConstraintViolation();
            context.buildConstraintViolationWithTemplate("").addPropertyNode(dateTo).addConstraintViolation();
            return false;
        }        
    }
}
