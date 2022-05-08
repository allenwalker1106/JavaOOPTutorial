package com.example.demo.Service;

import com.example.demo.DTO.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.ApplicationRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository o_userRepository;
    private final ModelMapper o_modelMapper;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository o_userRepository, ModelMapper o_modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.o_userRepository = o_userRepository;
        this.o_modelMapper = o_modelMapper;
    }




    public Optional<UserDto> save(UserDto o_userDto){
        User o_user = new User(
                o_userDto.getAccount(),
                passwordEncoder.encode(o_userDto.getPassword()),
                o_userDto.getName(),
                o_userDto.getEmail(),
                o_userDto.getPhone(),
                o_userDto.getAddress(),
                o_userDto.getDate()
        );
        o_user.setAuthorities(ApplicationRole.valueOf(o_userDto.getRole().trim().toUpperCase()).getAuthorities());
        return Optional.of(o_modelMapper.map(o_userRepository.save(o_user),UserDto.class));
    }
}
