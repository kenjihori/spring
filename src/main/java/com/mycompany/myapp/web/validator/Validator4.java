package com.mycompany.myapp.web.validator;

import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mycompany.myapp.web.form.ValidationSampleForm4;

@Component
public class ValidationSampleFormValidator4 implements Validator {

    public boolean supports(Class<?> clazz) {
        return ValidationSampleForm4.class.isAssignableFrom(clazz);
    }

    public void validate(Object form, Errors errors) {

        ValidationSampleForm4 validationSampleForm4 = (ValidationSampleForm4)form;
        String period = validationSampleForm4.getPeriod();
        Date dueDate = validationSampleForm4.getDueDate();
        if (period != null && period.equals("B")  && dueDate == null) {
            errors.rejectValue("dueDate", "dueDate.needed");
        }
        
    }

}
