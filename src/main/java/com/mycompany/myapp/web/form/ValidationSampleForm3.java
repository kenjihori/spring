package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

import com.mycompany.myapp.web.validator.DateRange;

@DateRange(dateFrom = "dateFrom", dateTo = "dateTo")
public class ValidationSampleForm3 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Date dateFrom;
    
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
    
}
