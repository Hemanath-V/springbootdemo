package com.newpro.ems_backend.service;

import com.newpro.ems_backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(long id, EmployeeDto updatedEmployeeDto);

    void deleteEmployee(long id);
}
