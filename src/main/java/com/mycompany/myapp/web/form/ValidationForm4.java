package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;

public class ValidationForm4 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String period;
    
    private Date dueDate;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
}
