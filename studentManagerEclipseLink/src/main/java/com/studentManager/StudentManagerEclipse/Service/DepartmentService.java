package com.studentManager.StudentManagerEclipse.Service;

import com.studentManager.StudentManagerEclipse.DTO.DepartmentDto;
import com.studentManager.StudentManagerEclipse.Entity.Department;
import com.studentManager.StudentManagerEclipse.Exception.EntityNotFoundException;
import com.studentManager.StudentManagerEclipse.RepositoryImplement.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepository o_departmentRepository;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    public DepartmentService(DepartmentRepository o_departmentRepository) {
        this.o_departmentRepository = o_departmentRepository;
    }

    public DepartmentDto getById(String id) throws EntityNotFoundException {
        Optional<Department> o_department = this.o_departmentRepository.getById(id);
        if(o_department.isPresent())
            return o_modelMapper.map(o_department.get(),DepartmentDto.class);
        throw new EntityNotFoundException("Entity Not Found");
    }

    public List<DepartmentDto> findAll(){
        List<Department> c_departments = this.o_departmentRepository.findAll();
        List<DepartmentDto> c_departmentDto = new ArrayList<>();
        for(Department o_department: c_departments){
            c_departmentDto.add(o_modelMapper.map(o_department,DepartmentDto.class));
        }
        return c_departmentDto;
    }

    public List<DepartmentDto> findAllById(List<String> ids){
        List<Department> c_departments = this.o_departmentRepository.findAllById(ids);
        List<DepartmentDto> c_departmentDto = new ArrayList<>();
        for(Department o_department: c_departments){
            c_departmentDto.add(o_modelMapper.map(o_department,DepartmentDto.class));
        }
        return c_departmentDto;
    }

    public DepartmentDto save(Department department) throws EntityExistsException{
        Optional<Department> o_department = this.o_departmentRepository.save(department);
        if(o_department.isPresent())
            return o_modelMapper.map(o_department.get(),DepartmentDto.class);
        else
            throw new EntityExistsException();
    }


    public List<DepartmentDto>  saveAll(List<DepartmentDto> c_departments) throws EntityExistsException{
        List<Department> c_resultDepartments = this.o_departmentRepository.saveAll(DtoToEntity(c_departments));
        List<DepartmentDto> c_departmentDto = new ArrayList<>();
        if(c_resultDepartments.size()!=0) {
            for(Department o_department: c_resultDepartments){
                c_departmentDto.add(o_modelMapper.map(o_department,DepartmentDto.class));
            }
            return c_departmentDto;
        }
        else
            throw new EntityExistsException();
    }

    private Department DtoToEntity(DepartmentDto departmentDto){
        return o_modelMapper.map(departmentDto,Department.class);
    }

    private List<Department> DtoToEntity(List<DepartmentDto> c_departmentDto){
        List<Department> c_department= new ArrayList<>();
        for(DepartmentDto departmentDto: c_departmentDto){
            c_department.add(DtoToEntity(departmentDto));
        }
        return c_department;
    }
}
