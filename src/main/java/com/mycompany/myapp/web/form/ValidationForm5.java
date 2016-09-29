package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ValidationForm5 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<ValidationForm5Child> validationForm5ChildList;

    public List<ValidationForm5Child> getValidationForm5ChildList() {
        return validationForm5ChildList;
    }

    public void setValidationForm5ChildList(List<ValidationForm5Child> validationForm5ChildList) {
        this.validationForm5ChildList = validationForm5ChildList;
    }
    
}
