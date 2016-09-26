package com.mycompany.myapp.web.validator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.myapp.web.form.SampleChildForm;
import com.mycompany.myapp.web.form.SampleForm;

@Component
public class SampleFormValidator implements Validator {

    @Autowired
    private Validator validator;
    
    public boolean supports(Class<?> clazz) {
        return SampleForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object form, Errors errors) {

        SampleForm sampleForm = (SampleForm)form;
        String period = sampleForm.getPeriod();
        Date dueDate = sampleForm.getDueDate();
        System.out.println("period:" + period);
        System.out.println("dueDate:" + dueDate);
        if (period != null && period.equals("B")  && dueDate == null) {
            errors.rejectValue("dueDate", "dueDate.needed");
        }
        
        // リスト取得
        List<SampleChildForm> sampleChildFormList = sampleForm.getSampleChildFormList();

        for(int i=0; i<sampleChildFormList.size(); i++) {
            SampleChildForm sampleChildForm = sampleChildFormList.get(i);
            String item = sampleChildForm.getItem();
            if(item != null && !item.equals("")) {  // nullでない場合はチェック
                errors.pushNestedPath("sampleChildFormList[" + i + "].");// これをしないと、errorsに含まれるエラーフィールドがbooknoとなってしまう。ただしくは、compositeLendChildFormList[0].bookno
                validator.validate(sampleChildForm, errors);
                errors.popNestedPath();
            }
        }
    }

}
