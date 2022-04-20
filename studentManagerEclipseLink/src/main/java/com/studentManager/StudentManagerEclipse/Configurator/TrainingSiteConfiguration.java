package com.studentManager.StudentManagerEclipse.Configurator;

import com.studentManager.StudentManagerEclipse.Entity.TrainingSite;
import com.studentManager.StudentManagerEclipse.RepositoryImplement.TrainingSiteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TrainingSiteConfiguration {
    @Bean("trainingSiteInitializer")
    CommandLineRunner commandLineRunner(
            TrainingSiteRepository trainingSiteRepository){
        return args->{
            trainingSiteRepository.saveAll(
                    List.of(
                            new TrainingSite("HN","Ha Noi"),
                            new TrainingSite("DN","Da Nang"),
                            new TrainingSite("HCM","Ho Chi Minh")
                    )
            );
        };
    }

}
