package com.mycompany.myapp.web.form;

import java.io.Serializable;
import com.mycompany.myapp.web.validator.ZipCode;

public class ValidationSampleForm1 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ZipCode
    private String zipCode;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
}
