package com.dept.service.impl;

import com.dept.dto.DepartmentDto;
import com.dept.entity.Department;
import com.dept.exception.DepartmentNotFoundException;
import com.dept.repository.DepartmentRepository;
import com.dept.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department dept=new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department savedDepartment=departmentRepository.save(dept);
        DepartmentDto savedDepartmentDto=new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDto;
    }

    @Override
    public List<DepartmentDto> findAllDepartments() {
        List<Department> depts= departmentRepository.findAll();
        List<DepartmentDto> departmentDtos=new ArrayList<>();
        for (Department d:depts){
            DepartmentDto departmentDto=new DepartmentDto();
            departmentDto.setId(d.getId());
            departmentDto.setDepartmentName(d.getDepartmentName());
            departmentDto.setDepartmentDescription(d.getDepartmentDescription());
            departmentDto.setDepartmentCode(d.getDepartmentCode());
            departmentDtos.add(departmentDto);
        }
        return departmentDtos;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()

        );
        return departmentDto;
    }

    @Override
    public DepartmentDto updateDepartment(String code, DepartmentDto departmentDto) {
        for(Department d:departmentRepository.findAll()){
            if(d.getDepartmentCode().equals(code)){
                Department updatedDepartment = departmentRepository.findByDepartmentCode(code);
                updatedDepartment.setDepartmentName(departmentDto.getDepartmentName());
                updatedDepartment.setDepartmentDescription(departmentDto.getDepartmentDescription());
                updatedDepartment.setDepartmentCode(departmentDto.getDepartmentCode());
                departmentRepository.save(updatedDepartment);
                DepartmentDto savedDepartmentDto=new DepartmentDto(
                        updatedDepartment.getId(),
                        updatedDepartment.getDepartmentName(),
                        updatedDepartment.getDepartmentDescription(),
                        updatedDepartment.getDepartmentCode()
                );
                return savedDepartmentDto;
            }
        }
      return null;
    }

    @Override
    public String deleteDepartmentByCode(String code) {
      for(Department d:departmentRepository.findAll()){
          if(d.getDepartmentCode().equals(code)){
              departmentRepository.delete(d);
              return "Department with code "+code+" deleted";
          }
      }
      return "Department with code "+code+" not found";
    }
}

