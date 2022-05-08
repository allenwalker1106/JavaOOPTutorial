package com.example.demo.Controller;

import com.example.demo.DTO.ResponseObject;
import com.example.demo.DTO.UserDto;
import com.example.demo.Service.UserService;
import com.example.demo.Util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class DummyController {

    private final UserValidator o_validator;
    private final UserService o_userService;

    @Autowired
    public DummyController(UserValidator o_validator, UserService o_userService) {
        this.o_validator = o_validator;
        this.o_userService = o_userService;
    }

    @GetMapping("/admin/user")
    @PreAuthorize("hasAnyAuthority('user:read')")
    ResponseEntity<ResponseObject> getUser(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "getUser"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/admin/user")
    @PreAuthorize("hasAnyAuthority('user:update')")
    ResponseEntity<ResponseObject> updateUser(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "updateUser"
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/admin/user")
    @PreAuthorize("hasAnyAuthority('user:write')")
    ResponseEntity<ResponseObject> insertUser(@RequestBody UserDto o_userDto){
        HashMap<String,Boolean> c_token = o_validator.validate(o_userDto);
        if(o_validator.isValid(c_token)){
            Optional<UserDto> o_userOp = o_userService.save(o_userDto);
            if(o_userOp.isPresent()){
                return new ResponseEntity(
                        new ResponseObject(
                                HttpStatus.OK,
                                "Success Insert User",
                                ""
                        ),
                        HttpStatus.OK
                );
            }else{
                return new ResponseEntity(
                        new ResponseObject(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                "Fail Insert User",
                                ""
                        ),
                        HttpStatus.INTERNAL_SERVER_ERROR
                );
            }
        }else{
            return new ResponseEntity(
                    new ResponseObject(
                            HttpStatus.EXPECTATION_FAILED,
                            "Requirement Fail",
                            c_token
                    ),
                    HttpStatus.EXPECTATION_FAILED
            );
        }

    }

    @DeleteMapping("/admin/user")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    ResponseEntity<ResponseObject> deleteUser(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "deleteUser"
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/staff/student")
    @PreAuthorize("hasAnyAuthority('student:read')")
    ResponseEntity<ResponseObject> getStudent(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "getStudent"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/staff/student")
    @PreAuthorize("hasAnyAuthority('student:update')")
    ResponseEntity<ResponseObject> updateStudent(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "updateStudent"
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/staff/student")
    @PreAuthorize("hasAnyAuthority('student:write')")
    ResponseEntity<ResponseObject> insertStudent(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "insertStudent"
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/staff/student")
    @PreAuthorize("hasAnyAuthority('student:delete')")
    ResponseEntity<ResponseObject> deleteStudent(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "deleteStudent"
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/student/course")
    @PreAuthorize("hasAnyAuthority('course:read')")
    ResponseEntity<ResponseObject> getCourse(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "getCourse"
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/staff/course")
    @PreAuthorize("hasAnyAuthority('course:update')")
    ResponseEntity<ResponseObject> updateCourse(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "updateCourse"
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/staff/course")
    @PreAuthorize("hasAnyAuthority('course:write')")
    ResponseEntity<ResponseObject> insertCourse(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "insertCourse"
                ),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/staff/course")
    @PreAuthorize("hasAnyAuthority('course:delete')")
    ResponseEntity<ResponseObject> deleteCourse(){
        return new ResponseEntity(
                new ResponseObject(
                        HttpStatus.OK,
                        "ok",
                        "deleteCourse"
                ),
                HttpStatus.OK
        );
    }

}
