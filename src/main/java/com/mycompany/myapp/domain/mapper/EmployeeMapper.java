package com.mycompany.myapp.domain.mapper;

import org.apache.ibatis.annotations.Param;

import com.mycompany.myapp.domain.model.Employee;

public interface EmployeeMapper {

    Employee findOne(@Param("companyCode") String companyCode, @Param("employeeCode") String employeeCode);

}
