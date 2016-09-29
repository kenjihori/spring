package com.mycompany.myapp.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationForm5;
import com.mycompany.myapp.web.form.ValidationForm5Child;
import com.mycompany.myapp.web.validator.Validator5;

@Controller
public class ValidationController5 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController5.class);
    
    @Autowired
    Validator5 validator5;
    
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(validator5);
    }
    
    @ModelAttribute("validationForm5")
    public ValidationForm5 initForm() {
        ValidationForm5 form = new ValidationForm5();
        List<ValidationForm5Child> validationForm5ChildList = new ArrayList<ValidationForm5Child>();
        for(int i=0; i<5; i++) {
            validationForm5ChildList.add(new ValidationForm5Child());
        }
        form.setValidationForm5ChildList(validationForm5ChildList);
        return form;
    }
    
    @RequestMapping(value = "validation5", method = RequestMethod.GET)
    public String index(ValidationForm5 form, Model model) {
        return "validation5";
    }
    
    @RequestMapping(value = "validation5", method = RequestMethod.POST)
    public String confirm(@Validated ValidationForm5 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validation5";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationForm5", form);
        return "validationConfirm5";
    }

}
