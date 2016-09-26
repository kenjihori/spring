package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.mycompany.myapp.web.validator.DateRange;
import com.mycompany.myapp.web.validator.ItemCodeExists;
import com.mycompany.myapp.web.validator.ZipCode;

public class SampleChildForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String item;
    
    @Digits(integer = 3, fraction = 0, message = "{price.digits}")
    private Integer price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    
}
