package com.mycompany.myapp.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.mycompany.myapp.web.validator.EmployeeExists;
import com.mycompany.myapp.web.validator.ItemExists;

@EmployeeExists(companyCode = "companyCode", employeeCode = "employeeCode")
public class ValidationForm2 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull
    @ItemExists
    private String itemCode;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    // 複合キーの拡張
    
    private String companyCode;
    
    private String employeeCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    
}
