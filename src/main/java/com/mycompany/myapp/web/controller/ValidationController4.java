package com.mycompany.myapp.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.web.form.ValidationForm4;
import com.mycompany.myapp.web.validator.Validator4;

@Controller
public class ValidationController4 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController4.class);
    
    @Autowired
    Validator4 validator4;
    
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(validator4);
    }
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    @ModelAttribute("radios")
    public Map<String, String> radioList() {
        return Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
            {
                put("A", "当日");
                put("B", "期間指定");
            }
        });
    }
    
    @RequestMapping(value = "validation4", method = RequestMethod.GET)
    public String index(ValidationForm4 form, Model model) {
        return "validation4";
    }
    
    @RequestMapping(value = "validation4", method = RequestMethod.POST)
    public String confirm(@Validated ValidationForm4 form, BindingResult bindingResult, Model model) {
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validation4";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationForm4", form);
        //ラジオボタンの表示名を設定
        model.addAttribute("selectedPeriod", radioList().get(form.getPeriod())); 
        return "validationConfirm4";
    }

}
