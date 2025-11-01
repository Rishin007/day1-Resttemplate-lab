package com.emp.controller;

import com.emp.dto.APIResponseDto;
import com.emp.dto.EmployeeDto;
import com.emp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
//    @GetMapping("{id}")
//    public ResponseEntity<EmployeeDto> getAnEmployee(@PathVariable("id") Long id) {
//        EmployeeDto employeeDto = employeeService.getEmployeeById(id);
//        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
//    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getAnEmployee(@PathVariable("id") Long id) {
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

}
