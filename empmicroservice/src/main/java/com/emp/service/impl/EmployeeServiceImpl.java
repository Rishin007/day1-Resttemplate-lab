package com.emp.service.impl;

import com.emp.dto.APIResponseDto;
import com.emp.dto.DepartmentDto;
import com.emp.dto.EmployeeDto;
import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;
import com.emp.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
@Data
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()

        );

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                saveDEmployee.getId(),
                saveDEmployee.getFirstName(),
                saveDEmployee.getLastName(),
                saveDEmployee.getEmail(),
                 saveDEmployee.getDepartmentCode()
        );

        return savedEmployeeDto;
    }

//    @Override
//    public EmployeeDto getEmployeeById(Long id) {
//        Employee emp=employeeRepository.findById(id).get();
//        EmployeeDto employeeDto=new EmployeeDto(
//                emp.getId(),
//                emp.getFirstName(),
//                emp.getLastName(),
//                emp.getEmail(),
//                emp.getDepartmentCode()
//        );
//        return employeeDto;
//    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity
                ("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);

        DepartmentDto departmentDto = responseEntity.getBody();

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()

        );
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentdto(departmentDto);

        return apiResponseDto;

    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        for(Employee e:employeeRepository.findAll()){
            if(e.getDepartmentCode().equals(id)){
                Employee updatedEmployee = employeeRepository.findById(id).get();
                updatedEmployee.setFirstName(employeeDto.getFirstName());
                updatedEmployee.setLastName(employeeDto.getLastName());
                updatedEmployee.setEmail(employeeDto.getEmail());
                updatedEmployee.setDepartmentCode(employeeDto.getDepartmentCode());
                employeeRepository.save(updatedEmployee);
                EmployeeDto savedEmployeeDto=new EmployeeDto(
                        updatedEmployee.getId(),
                        updatedEmployee.getFirstName(),
                        updatedEmployee.getLastName(),
                        updatedEmployee.getEmail(),
                        updatedEmployee.getDepartmentCode()
                );
                return savedEmployeeDto;
            }
        }
        return null;
    }

    @Override
    public String deleteEmployee(Long id) {
        for(Employee e:employeeRepository.findAll()){
            if(e.getId().equals(id)){
                employeeRepository.deleteById(id);
                return "Employee with code "+id+" deleted";
            }
        }
        return "Employee with code "+id+" not found";
    }
}

