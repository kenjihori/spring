package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.mycompany.myapp.web.validator.DateRange;

public class ValidationForm3 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "{dateFrom.notnull}")
    private Date dateFrom;
    
    @NotNull(message = "{dateTo.notnull}")
    private Date dateTo;

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    
    private boolean validDate;
    
    @AssertTrue(message = "{invalidDate}")
    public boolean isValidDate() {
        if(dateFrom == null) return true;
        if(dateTo == null) return true;
        if(dateFrom.compareTo(dateTo) <= 0) return true;
        return false;
    }
    
}
