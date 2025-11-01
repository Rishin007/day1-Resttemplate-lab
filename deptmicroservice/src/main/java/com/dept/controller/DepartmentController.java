package com.dept.controller;

import com.dept.dto.DepartmentDto;
import com.dept.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto departmentDto1 = departmentService.saveDepartment(departmentDto);
        return new  ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.findAllDepartments(), HttpStatus.OK);
    }
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
    @PutMapping("{code}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable String code, @RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(code, departmentDto), HttpStatus.OK);
    }
    @DeleteMapping("{departmentCode}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentCode){
        return new ResponseEntity<>(departmentService.deleteDepartmentByCode(departmentCode),HttpStatus.OK);
    }
}
