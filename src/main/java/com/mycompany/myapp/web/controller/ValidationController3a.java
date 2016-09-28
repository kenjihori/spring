package com.mycompany.myapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationForm3;

@Controller
public class ValidationController3a {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController3a.class);
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    @RequestMapping(value = "validation3a", method = RequestMethod.GET)
    public String index(ValidationForm3 form, Model model) {
        return "validation3a";
    }
    
    @RequestMapping(value = "validation3a", method = RequestMethod.POST)
    public String confirm(@Validated ValidationForm3 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validation3";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationForm3a", form);
        return "validationConfirm3a";
    }

}
