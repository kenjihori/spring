package com.mycompany.myapp.web.validator;

import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mycompany.myapp.web.form.ValidationForm4;

@Component
public class Validator4 implements Validator {

    public boolean supports(Class<?> clazz) {
        return ValidationForm4.class.isAssignableFrom(clazz);
    }

    public void validate(Object form, Errors errors) {

        ValidationForm4 validationForm4 = (ValidationForm4)form;
        String period = validationForm4.getPeriod();
        Date dueDate = validationForm4.getDueDate();
        if (period != null && period.equals("B")  && dueDate == null) {
            errors.rejectValue("dueDate", "dueDate.needed");
        }
        
    }

}
