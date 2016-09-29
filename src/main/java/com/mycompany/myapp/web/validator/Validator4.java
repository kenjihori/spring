package com.mycompany.myapp.web.validator;

import java.util.Date;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mycompany.myapp.web.form.ValidationForm4;

@Component
public class Validator4 implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ValidationForm4.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object form, Errors errors) {
        ValidationForm4 validationForm4 = (ValidationForm4)form;
        String period = validationForm4.getPeriod();
        Date dueDate = validationForm4.getDueDate();
        // 期間のラジオボタン（A:当日、B:期間指定）でBが選択された場合は、日付の入力が必要
        if (period != null && period.equals("B")  && dueDate == null) {
            errors.rejectValue("dueDate", "dueDate.notnull");
        }
    }

}
