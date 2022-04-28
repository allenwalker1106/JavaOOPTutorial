package com.manager.CarPark.Service;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.Entity.Employee;
import com.manager.CarPark.Entity.Permission;
import com.manager.CarPark.Repository.EmployeeRepository;
import com.manager.CarPark.Repository.PermissionRepository;
import com.manager.CarPark.Util.Converter;
import com.manager.CarPark.Util.Encoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository o_employeeRepository;

    @Autowired
    private PermissionRepository o_permissionRepository;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    private Encoder o_encoder;

    @Autowired
    private Converter o_converter;

    public Optional<EmployeeDto> save(Employee o_employee){
        try{
            o_employee.setPassword(o_encoder.getMd5(o_employee.getPassword()));
            Permission o_permission = new Permission("Employee");
            if(o_employee.getPermission()!=null)
                o_permission = o_employee.getPermission();
            o_permission=o_permissionRepository.save(o_permission);
            o_employee.setPermission(o_permission);
            Employee db_employee= o_employeeRepository.save(o_employee);
            EmployeeDto o_employeeDto = o_modelMapper.map(db_employee,EmployeeDto.class);
            return Optional.of(o_employeeDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<EmployeeDto> update(EmployeeDto o_employeeDto){
        if(o_employeeRepository.existsById(o_employeeDto.getId())){
            Employee o_employee = o_employeeRepository.getById(o_employeeDto.getId());
            if(o_employee.getAccount().equals(o_employeeDto.getAccount())){
                o_employee.setName(o_employeeDto.getName());
                o_employee.setAddress(o_employeeDto.getAddress());
                o_employee.setDepartment(o_employeeDto.getDepartment());
                o_employee.setEmail(o_employeeDto.getEmail());
                o_employee.setPhoneNumber(o_employeeDto.getPhoneNumber());

                o_employee=o_employeeRepository.save(o_employee);
                return Optional.of(o_modelMapper.map(o_employee,EmployeeDto.class));
            }
        }
        return Optional.empty();
    }

    public boolean delete(EmployeeDto o_employeeDto){
        if(o_employeeRepository.existsById(o_employeeDto.getId())){
            Employee o_employee = o_employeeRepository.getById(o_employeeDto.getId());
            Permission permission = o_employee.getPermission();
            o_employeeRepository.delete(o_employee);
            o_permissionRepository.delete(permission);
            return true;
        }else{
            return false;
        }
    }

    public List<EmployeeDto> findAll(){
        return o_converter.employeeToDTO(o_employeeRepository.findAll());
    }

    public List<EmployeeDto> findByName(String name){
        return o_converter.employeeToDTO(o_employeeRepository.findByNameContains(name));
    }

    public List<EmployeeDto> findByDepartment(String department){
        return o_converter.employeeToDTO(o_employeeRepository.findByDepartmentContains(department));
    }

    public List<EmployeeDto> findById(String str_id){
        Set<Long> c_ids =  o_converter.toIdList(str_id);
        return o_converter.employeeToDTO(o_employeeRepository.findAllById(c_ids));
    }


}
