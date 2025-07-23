package com.newpro.ems_backend.service;

import com.newpro.ems_backend.dto.EmployeeDto;
import com.newpro.ems_backend.entity.Employee;
import com.newpro.ems_backend.exception.ResourceNotFoundException;
import com.newpro.ems_backend.mapper.EmployeeMapper;
import com.newpro.ems_backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee createEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(createEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee Not Found With This Id : " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
//        List<EmployeeDto> employeeDtos = new ArrayList<>();
//        for(Employee employee : employees){
//            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(employee));
//        }
//        return employeeDtos;
        return employees.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto updatedEmployeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is No Employee With this Id : " + id));

        employee.setFirstName(updatedEmployeeDto.getFirstName());
        employee.setLastName(updatedEmployeeDto.getLastName());
        employee.setEmail(updatedEmployeeDto.getEmail());
        employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("There is No Employee With this Id : " + id));
        employeeRepository.deleteById(id);
    }
}
