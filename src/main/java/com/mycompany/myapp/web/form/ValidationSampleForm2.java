package com.mycompany.myapp.web.form;

import java.io.Serializable;
import com.mycompany.myapp.web.validator.ItemCodeExists;

public class ValidationSampleForm2 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ItemCodeExists
    private String itemCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
}
