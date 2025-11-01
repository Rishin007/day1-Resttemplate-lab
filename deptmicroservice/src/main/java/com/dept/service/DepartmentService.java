package com.dept.service;

import com.dept.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> findAllDepartments();
    DepartmentDto getDepartmentByCode(String code);
    DepartmentDto updateDepartment(String code,DepartmentDto departmentDto);
    String deleteDepartmentByCode(String code);
}
