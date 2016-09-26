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

import com.mycompany.myapp.web.form.ValidationSampleForm4;
import com.mycompany.myapp.web.validator.ValidationSampleFormValidator4;

@Controller
public class ValidationSampleController4 {

    private static final Logger logger = LoggerFactory.getLogger(ValidationSampleController4.class);
    
    @Autowired
    ValidationSampleFormValidator4 validationSampleFormValidator4;
    
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
    
    @RequestMapping(value = "validationSample4", method = RequestMethod.GET)
    public String index(ValidationSampleForm4 form, Model model) {
        return "validationSample4";
    }
    
    @RequestMapping(value = "validationSample4", method = RequestMethod.POST)
    public String confirm(@Validated ValidationSampleForm4 form, BindingResult bindingResult, Model model) {
        // カスタムのバリデータを呼び出す
        validationSampleFormValidator4.validate(form, bindingResult);
        // 入力チェックエラーがある場合は入力画面に戻る
        if (bindingResult.hasErrors()) {
            return "validationSample4";
        }
        // 入力チェックエラーがない場合は確認画面に遷移する
        model.addAttribute("validationSampleForm4", form);
        model.addAttribute("selectedPeriod", radioList().get(form.getPeriod())); //ラジオボタンの表示名
        return "validationSampleConfirm4";
    }

}
