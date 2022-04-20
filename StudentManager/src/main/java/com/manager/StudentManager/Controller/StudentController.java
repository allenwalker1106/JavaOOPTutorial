package com.manager.StudentManager.Controller;


import com.manager.StudentManager.DTO.RegularStudentDto;
import com.manager.StudentManager.DTO.StudentDto;
import com.manager.StudentManager.Entity.Department;
import com.manager.StudentManager.Entity.RegularStudent;
import com.manager.StudentManager.Entity.Student;
import com.manager.StudentManager.Exception.*;
import com.manager.StudentManager.Model.RegularStudentModel;
import com.manager.StudentManager.Service.StudentService;
import com.manager.StudentManager.Validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Validator validator;


    @GetMapping("/student-by-id")
    @ResponseBody
    ResponseEntity<Object> getStudentById(@RequestParam Long id){
        Optional<StudentDto> o_student =studentService.getStudentById(id);
        if(o_student.isPresent())
               return ResponseEntity.status(HttpStatus.OK).body(
                        o_student.get()
                );
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                   "Student not found !"
            );
    }

    @GetMapping("/students")
    @ResponseBody
    ResponseEntity<Object> getStudents(){
        List<StudentDto> c_student = studentService.getStudents();
        return ResponseEntity.status(HttpStatus.OK).body(c_student);
    }


    @PostMapping("insert-regular-student")
    ResponseEntity<Object>  insertRegularStudent(@RequestBody RegularStudentModel student){
            try{
                //validate user input model
                /*
                    VALIDATION
                 */
                validator.checkNameValid(student.getName());
                validator.checkEmailValid(student.getEmail());
                RegularStudent o_student = modelMapper.map(student,RegularStudent.class);
                StudentDto o_studentDto = studentService.insertStudent(o_student);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        o_studentDto
                );
            }catch(UserExistException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        e.getLocalizedMessage()
                );
            }catch(EmailExistException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        e.getLocalizedMessage()
                );
            }catch(InvalidNameException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        e.getLocalizedMessage()
                );
            }catch(InvalidEmailFormatException e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        e.getLocalizedMessage()
                );
            }
    }


}
