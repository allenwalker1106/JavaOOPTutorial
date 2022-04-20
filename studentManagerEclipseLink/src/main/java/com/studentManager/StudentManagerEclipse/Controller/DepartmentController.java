package com.studentManager.StudentManagerEclipse.Controller;


import com.studentManager.StudentManagerEclipse.DTO.DepartmentDto;
import com.studentManager.StudentManagerEclipse.Entity.Department;
import com.studentManager.StudentManagerEclipse.Exception.EntityNotFoundException;
import com.studentManager.StudentManagerEclipse.Model.DepartmentModel;
import com.studentManager.StudentManagerEclipse.Model.ResponseObject;
import com.studentManager.StudentManagerEclipse.Service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

    @Autowired
    private DepartmentService o_departmentService;


    @Autowired
    private ModelMapper o_modelMapper;



    @GetMapping("/getById")
    @ResponseBody
    public ResponseEntity<ResponseObject> getById(@RequestParam String id){
        try{
            DepartmentDto o_departmentDto=  o_departmentService.getById(id);
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            HttpStatus.OK,
                            List.of(o_departmentDto)
                    )
            );
        }catch(EntityNotFoundException e){
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            HttpStatus.OK,
                            List.of()
                    )
            );
        }

    }

    @GetMapping("/findAll")
    @ResponseBody
    public ResponseEntity<ResponseObject> findAll(){
            List<DepartmentDto> o_departmentDto=  o_departmentService.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            HttpStatus.OK,
                            o_departmentDto
                    )
            );
    }



    @GetMapping("/findAllById")
    @ResponseBody
    ResponseEntity<ResponseObject> findAllById(@RequestBody List<String> ids){
        List<DepartmentDto> o_departmentDto=  o_departmentService.findAllById(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        HttpStatus.OK,
                        o_departmentDto
                )
        );
    }

    @PostMapping("/save")
    @ResponseBody
    ResponseEntity<ResponseObject> save(@RequestBody DepartmentModel department){
        try{
            DepartmentDto o_departmentDto=  o_departmentService.save(o_modelMapper.map(department, Department.class));

            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            HttpStatus.OK,
                            o_departmentDto
                    )
            );
        }catch(EntityExistsException e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Entity Already Exist!"
                    )
            );
        }
    }

    @PostMapping("/saveAll")
    @ResponseBody
    ResponseEntity<ResponseObject> saveAll(@RequestBody List<DepartmentModel> department){
        try{
            List<DepartmentDto>  c_departmentDto=  o_departmentService.saveAll(this.modelToDto(department));

            return  ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            HttpStatus.OK,
                            c_departmentDto
                    )
            );
        }catch(EntityExistsException e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Entity Already Exist!"
                    )
            );
        }
    }

    private List<DepartmentDto> modelToDto(List<DepartmentModel> c_departments){
        List<DepartmentDto> c_departmentDto = new ArrayList<>();
        for(DepartmentModel model: c_departments){
            c_departmentDto.add(modelToDto(model));
        }
        return c_departmentDto;
    }

    private DepartmentDto modelToDto(DepartmentModel departmentModel){
        return o_modelMapper.map(departmentModel,DepartmentDto.class);
    }
}
