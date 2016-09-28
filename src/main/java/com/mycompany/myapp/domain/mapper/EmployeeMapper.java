package com.mycompany.myapp.domain.mapper;

import com.mycompany.myapp.domain.model.Employee;

public interface EmployeeMapper {

    Employee findOne(String companyCode, String employeeCode);

}
