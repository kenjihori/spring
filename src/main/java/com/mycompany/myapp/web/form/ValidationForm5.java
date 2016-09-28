package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ValidationForm5 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<ValidationForm5Child> validationSampleForm5ChildList;

    public List<ValidationForm5Child> getValidationSampleForm5ChildList() {
        return validationSampleForm5ChildList;
    }

    public void setValidationSampleForm5ChildList(List<ValidationForm5Child> validationSampleForm5ChildList) {
        this.validationSampleForm5ChildList = validationSampleForm5ChildList;
    }
    
}
