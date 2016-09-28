package com.mycompany.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.myapp.domain.mapper.EmployeeMapper;
import com.mycompany.myapp.domain.model.Employee;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee findEmployee(String companyCode, String employeeCode) {
        return employeeMapper.findOne(companyCode, employeeCode);
    }

}
