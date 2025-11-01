package com.emp.service;

import com.emp.dto.APIResponseDto;
import com.emp.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

//    EmployeeDto getEmployeeById(Long id);
    APIResponseDto getEmployeeById(Long employeeId);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    String deleteEmployee(Long id);
}
