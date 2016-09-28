package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;

public class ValidationForm5Child implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String item;
    
    private Integer qty;
    
    private Integer price;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
