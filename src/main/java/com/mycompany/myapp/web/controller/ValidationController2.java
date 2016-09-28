package com.mycompany.myapp.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationForm2;

@Controller
public class ValidationController2 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController2.class);
    
    @RequestMapping(value = "validation2", method = RequestMethod.GET)
    public String index(ValidationForm2 form, Model model) {
        return "validation2";
    }
    
    @RequestMapping(value = "validation2", method = RequestMethod.POST)
    public String confirm(@Validated ValidationForm2 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validation2";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationForm2", form);
        return "validationConfirm2";
    }

}
