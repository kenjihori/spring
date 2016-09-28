package com.mycompany.myapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationForm1;

@Controller
public class ValidationController1 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController1.class);
    
    @RequestMapping(value = "validation1", method = RequestMethod.GET)
    public String index(ValidationForm1 form, Model model) {
        return "validation1";
    }
    
    @RequestMapping(value = "validation1", method = RequestMethod.POST)
    public String confirm(@Validated ValidationForm1 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validation1";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationForm1", form);
        return "validationConfirm1";
    }

}
