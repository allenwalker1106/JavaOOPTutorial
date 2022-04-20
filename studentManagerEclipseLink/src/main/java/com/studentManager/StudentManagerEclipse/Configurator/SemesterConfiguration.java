package com.studentManager.StudentManagerEclipse.Configurator;

import com.studentManager.StudentManagerEclipse.Entity.Semester;
import com.studentManager.StudentManagerEclipse.RepositoryImplement.SemesterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class SemesterConfiguration {
    @Bean("semesterInitializer")
    public CommandLineRunner commandLineRunner(SemesterRepository semesterRepository){
        return args->{
            semesterRepository.saveAll(
                    List.of(
                            new Semester(20001L, "First Semester of 2000",LocalDate.of(2000,Month.SEPTEMBER,6)),
                            new Semester(20002L, "Second Semester of 2000",LocalDate.of(2000,Month.JANUARY,17)),
                            new Semester(20011L, "First Semester of 2001",LocalDate.of(2001,Month.SEPTEMBER,6)),
                            new Semester(20012L, "Second Semester of 2001",LocalDate.of(2001,Month.JANUARY,17)),
                            new Semester(20021L, "First Semester of 2002",LocalDate.of(2002,Month.SEPTEMBER,6)),
                            new Semester(20022L, "Second Semester of 2002",LocalDate.of(2002,Month.JANUARY,17)),
                            new Semester(20031L, "First Semester of 2003",LocalDate.of(2003,Month.SEPTEMBER,6)),
                            new Semester(20032L, "Second Semester of 2003",LocalDate.of(2003,Month.JANUARY,17)),
                            new Semester(20041L, "First Semester of 2004",LocalDate.of(2004,Month.SEPTEMBER,6)),
                            new Semester(20042L, "Second Semester of 2004",LocalDate.of(2004,Month.JANUARY,17)),
                            new Semester(20051L, "First Semester of 2005",LocalDate.of(2005,Month.SEPTEMBER,6)),
                            new Semester(20052L, "Second Semester of 2005",LocalDate.of(2005,Month.JANUARY,17)),
                            new Semester(20061L, "First Semester of 2006",LocalDate.of(2006,Month.SEPTEMBER,6)),
                            new Semester(20062L, "Second Semester of 2006",LocalDate.of(2006,Month.JANUARY,17)),
                            new Semester(20071L, "First Semester of 2007",LocalDate.of(2007,Month.SEPTEMBER,6)),
                            new Semester(20072L, "Second Semester of 2007",LocalDate.of(2007,Month.JANUARY,17)),
                            new Semester(20081L, "First Semester of 2008",LocalDate.of(2008,Month.SEPTEMBER,6)),
                            new Semester(20082L, "Second Semester of 2008",LocalDate.of(2008,Month.JANUARY,17)),
                            new Semester(20091L, "First Semester of 2009",LocalDate.of(2009,Month.SEPTEMBER,6)),
                            new Semester(20092L, "Second Semester of 2009",LocalDate.of(2009,Month.JANUARY,17)),
                            new Semester(20101L, "First Semester of 2010",LocalDate.of(2010,Month.SEPTEMBER,6)),
                            new Semester(20102L, "Second Semester of 2010",LocalDate.of(2010,Month.JANUARY,17)),
                            new Semester(20111L, "First Semester of 2011",LocalDate.of(2011,Month.SEPTEMBER,6)),
                            new Semester(20112L, "Second Semester of 2011",LocalDate.of(2011,Month.JANUARY,17)),
                            new Semester(20121L, "First Semester of 2012",LocalDate.of(2012,Month.SEPTEMBER,6)),
                            new Semester(20122L, "Second Semester of 2012",LocalDate.of(2012,Month.JANUARY,17)),
                            new Semester(20131L, "First Semester of 2013",LocalDate.of(2013,Month.SEPTEMBER,6)),
                            new Semester(20132L, "Second Semester of 2013",LocalDate.of(2013,Month.JANUARY,17)),
                            new Semester(20141L, "First Semester of 2014",LocalDate.of(2014,Month.SEPTEMBER,6)),
                            new Semester(20142L, "Second Semester of 2014",LocalDate.of(2014,Month.JANUARY,17)),
                            new Semester(20151L, "First Semester of 2015",LocalDate.of(2015,Month.SEPTEMBER,6)),
                            new Semester(20152L, "Second Semester of 2015",LocalDate.of(2015,Month.JANUARY,17)),
                            new Semester(20161L, "First Semester of 2016",LocalDate.of(2016,Month.SEPTEMBER,6)),
                            new Semester(20162L, "Second Semester of 2016",LocalDate.of(2016,Month.JANUARY,17)),
                            new Semester(20171L, "First Semester of 2017",LocalDate.of(2017,Month.SEPTEMBER,6)),
                            new Semester(20172L, "Second Semester of 2017",LocalDate.of(2017,Month.JANUARY,17)),
                            new Semester(20181L, "First Semester of 2018",LocalDate.of(2018,Month.SEPTEMBER,6)),
                            new Semester(20182L, "Second Semester of 2018",LocalDate.of(2018,Month.JANUARY,17)),
                            new Semester(20191L, "First Semester of 2019",LocalDate.of(2019,Month.SEPTEMBER,6)),
                            new Semester(20192L, "Second Semester of 2019",LocalDate.of(2019,Month.JANUARY,17)),
                            new Semester(20201L, "First Semester of 2020",LocalDate.of(2020,Month.SEPTEMBER,6)),
                            new Semester(20202L, "Second Semester of 2020",LocalDate.of(2020,Month.JANUARY,17)),
                            new Semester(20211L, "First Semester of 2021",LocalDate.of(2021,Month.SEPTEMBER,6)),
                            new Semester(20212L, "Second Semester of 2021",LocalDate.of(2021,Month.JANUARY,17)),
                            new Semester(20221L, "First Semester of 2022",LocalDate.of(2022,Month.SEPTEMBER,6)),
                            new Semester(20222L, "Second Semester of 2022",LocalDate.of(2022,Month.JANUARY,17)),
                            new Semester(20231L, "First Semester of 2023",LocalDate.of(2023,Month.SEPTEMBER,6)),
                            new Semester(20232L, "Second Semester of 2023",LocalDate.of(2023,Month.JANUARY,17))

                    )
            );
        };
    }
}
