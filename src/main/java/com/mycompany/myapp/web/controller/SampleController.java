package com.mycompany.myapp.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
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

import com.mycompany.myapp.domain.model.Item;
import com.mycompany.myapp.service.ItemService;
import com.mycompany.myapp.web.form.SampleChildForm;
import com.mycompany.myapp.web.form.SampleForm;
import com.mycompany.myapp.web.validator.SampleFormValidator;

@Controller
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    SampleFormValidator sampleFormValidator;
    
    @ModelAttribute("sampleForm")
    public SampleForm initForm() {
        SampleForm sampleForm = new SampleForm();
        List<SampleChildForm> sampleChildFormList = new ArrayList<SampleChildForm>();
        for(int i=0; i<5; i++) {
            sampleChildFormList.add(new SampleChildForm());
        }
        sampleForm.setSampleChildFormList(sampleChildFormList);
        return sampleForm;
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
    
    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    @RequestMapping(value = "sample", method = RequestMethod.GET)
    public String index(SampleForm form, Model model) {
        return "sample";
    }
    
    @RequestMapping(value = "sample", method = RequestMethod.POST)
    public String confirm(@Validated SampleForm form, BindingResult bindingResult, Model model) {
        
        // カスタムのバリデーターを呼び出す
        sampleFormValidator.validate(form, bindingResult);
        
        if (bindingResult.hasErrors()) {
            return "sample";
        }
        model.addAttribute("sampleForm", form);
        String key = form.getPeriod();
        String val = radioList().get(form.getPeriod());
        model.addAttribute("selectedPeriod", val);//ラジオボタンの表示名
        System.out.println("key:" + key);
        System.out.println("val:" + val);
        return "sampleConfirm";
    }

}
