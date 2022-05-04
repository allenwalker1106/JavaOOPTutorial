package com.example.CarParkApi.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper o_modelMapper= new ModelMapper();
        o_modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return o_modelMapper;
    }
}
