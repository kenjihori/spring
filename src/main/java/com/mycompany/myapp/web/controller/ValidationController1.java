package com.mycompany.myapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationSampleForm1;

@Controller
public class ValidationSampleController1 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationSampleController1.class);
    
    @RequestMapping(value = "validationSample1", method = RequestMethod.GET)
    public String index(ValidationSampleForm1 form, Model model) {
        return "validationSample1";
    }
    
    @RequestMapping(value = "validationSample1", method = RequestMethod.POST)
    public String confirm(@Validated ValidationSampleForm1 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validationSample1";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationSampleForm1", form);
        return "validationSampleConfirm1";
    }

}
