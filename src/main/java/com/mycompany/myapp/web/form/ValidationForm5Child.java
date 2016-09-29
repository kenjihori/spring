package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.mycompany.myapp.web.validator.ItemExists;
import com.mycompany.myapp.web.validator.NotSelected;
import com.mycompany.myapp.web.validator.Selected;

public class ValidationForm5Child implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ItemExists(groups = Selected.class)
    private String item;
    
    @NotNull(groups = Selected.class, message = "{qty.notnull}")
    @Null(groups = NotSelected.class, message = "{qty.null}")
    @Digits(integer = 3, fraction = 0, groups = Selected.class, message = "{qty.digits}")
    private Integer qty;
    
    @NotNull(groups = Selected.class, message = "{price.notnull}")
    @Null(groups = NotSelected.class, message = "{price.null}")
    @Digits(integer = 3, fraction = 0, groups = Selected.class, message = "{price.digits}")
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
