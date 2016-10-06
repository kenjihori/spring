package com.mycompany.myapp.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String createdById;
    
    private Date createdTimestamp;
    
    private String updatedById;
    
    private Date updatedTimestamp;
    
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

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public Date getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Date updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }

    public String toString() {
        return "id:" + this.id + ", name:" + this.name;
    }
}
