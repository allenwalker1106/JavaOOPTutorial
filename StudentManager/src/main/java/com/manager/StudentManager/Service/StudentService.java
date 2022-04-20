package com.manager.StudentManager.Service;

import com.manager.StudentManager.DTO.RegularStudentDto;
import com.manager.StudentManager.DTO.ServiceStudentDto;
import com.manager.StudentManager.DTO.StudentDto;
import com.manager.StudentManager.Entity.RegularStudent;
import com.manager.StudentManager.Entity.Student;
import com.manager.StudentManager.Exception.EmailExistException;
import com.manager.StudentManager.Exception.UserExistException;
import com.manager.StudentManager.Repository.RegularStudentRepository;
import com.manager.StudentManager.Repository.ServiceStudentRepository;
import com.manager.StudentManager.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final RegularStudentRepository regularStudentRepository;
    private final ServiceStudentRepository serviceStudentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public StudentService(RegularStudentRepository regularStudentRepository, ServiceStudentRepository serviceStudentRepository, StudentRepository studentRepository) {
        this.regularStudentRepository = regularStudentRepository;
        this.serviceStudentRepository = serviceStudentRepository;
        this.studentRepository = studentRepository;
    }


    public StudentDto insertStudent(Student student) throws UserExistException, EmailExistException {
        if(userExist(student.getId()))
            throw new UserExistException("User  Already Exist!");
        else if(emailExist(student.getEmail()))
            throw new EmailExistException("Email Already Exist");
        else{
            if (student instanceof RegularStudent)
                return modelMapper.map(
                        regularStudentRepository.save(student),
                        RegularStudentDto.class
                );
            else {
                return  modelMapper.map(
                        serviceStudentRepository.save(student),
                        ServiceStudentDto.class
                );
            }
        }
    }

    public Optional<StudentDto> getStudentById(Long id){
        try{
            Student o_student = studentRepository.getById(id);
            StudentDto o_studentDto = modelMapper.map(o_student,StudentDto.class);
            return Optional.of(o_studentDto);
        }catch(JpaObjectRetrievalFailureException|EntityNotFoundException e){
            return Optional.empty();
        }
    }

    public List<StudentDto> getStudents(){
        List<Student> c_students = studentRepository.findAll();
        List<StudentDto> c_studentDto= new ArrayList<>();
        for(Student student:c_students){
            c_studentDto.add(modelMapper.map(student,StudentDto.class));
        }
        return c_studentDto;
    }

    private boolean userExist(Long id){
        try{
            Student o_student = studentRepository.getById(id);
            return true;
        }catch(JpaObjectRetrievalFailureException|EntityNotFoundException e){
            return false;
        }
    }

    private boolean emailExist(String email){
        List<Student> c_students = studentRepository.findByEmail(email);
        if(c_students.size()!=0)
            return true;
        return false;
    }

}
