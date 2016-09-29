package com.mycompany.myapp.web.validator;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;
import com.mycompany.myapp.web.form.ValidationForm5;
import com.mycompany.myapp.web.form.ValidationForm5Child;

@Component
public class Validator5 implements Validator {

    @Autowired
    SmartValidator smartValidator;
    
    public boolean supports(Class<?> clazz) {
        return ValidationForm5.class.isAssignableFrom(clazz);
    }

    public void validate(Object form, Errors errors) {

        ValidationForm5 validationForm5 = (ValidationForm5)form;
        
        List<ValidationForm5Child> validationForm5ChildList = validationForm5.getValidationForm5ChildList();
        
        for(int i=0; i<validationForm5ChildList.size(); i++) {

            ValidationForm5Child validationForm5Child = validationForm5ChildList.get(i);
            String item = validationForm5Child.getItem();
            
            errors.pushNestedPath("validationForm5ChildList[" + i + "].");
            // 商品が入力されている場合は入力対象の行としてチェック
            if(item != null && !item.equals("")) {
                smartValidator.validate(validationForm5Child, errors, Selected.class);
            // 商品が入力されていない場合は入力対象外の行としてチェック
            } else {
                smartValidator.validate(validationForm5Child, errors,  NotSelected.class);
            }
            errors.popNestedPath();
        }
                
    }

}
