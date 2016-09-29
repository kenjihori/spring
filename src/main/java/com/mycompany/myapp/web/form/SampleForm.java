package com.mycompany.myapp.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.mycompany.myapp.web.validator.DateRange;
import com.mycompany.myapp.web.validator.ItemExists;
import com.mycompany.myapp.web.validator.ZipCode;

@DateRange(dateFrom = "dateFrom", dateTo = "dateTo")
public class SampleForm implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ZipCode
    private String zipCode;

    @ItemExists
    private String itemCode;
    
    @NotNull(message = "{dateFrom.notnull}")
    private Date dateFrom;
    
    @NotNull(message = "{dateTo.notnull}")
    private Date dateTo;
    
    @NotBlank(message = "{period.radio}")
    private String period;
    
    private Date dueDate;
    
    private List<SampleChildForm> sampleChildFormList;
    
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

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

    public List<SampleChildForm> getSampleChildFormList() {
        return sampleChildFormList;
    }

    public void setSampleChildFormList(List<SampleChildForm> sampleChildFormList) {
        this.sampleChildFormList = sampleChildFormList;
    }
    
}
