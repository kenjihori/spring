package com.mycompany.myapp.domain.model;

public class MyAppUser {

    private String id;

    private String name;
    
    private String departmentCode;
    
    private String password;
    
    private String userCategory;

    public boolean isAdmin() {
        if(userCategory.equals("2")) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

}
