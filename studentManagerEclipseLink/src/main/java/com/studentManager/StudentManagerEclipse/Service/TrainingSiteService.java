package com.studentManager.StudentManagerEclipse.Service;

import com.studentManager.StudentManagerEclipse.DTO.TrainingSiteDto;
import com.studentManager.StudentManagerEclipse.Entity.TrainingSite;
import com.studentManager.StudentManagerEclipse.Exception.EntityNotFoundException;
import com.studentManager.StudentManagerEclipse.RepositoryImplement.TrainingSiteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingSiteService {

    private final TrainingSiteRepository o_trainingSiteRepository;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    public TrainingSiteService(TrainingSiteRepository o_trainingSiteRepository) {
        this.o_trainingSiteRepository = o_trainingSiteRepository;
    }

    public TrainingSiteDto getById(String id) throws EntityNotFoundException {
        Optional<TrainingSite> o_trainingSite = this.o_trainingSiteRepository.getById(id);
        if(o_trainingSite.isPresent())
            return o_modelMapper.map(o_trainingSite.get(),TrainingSiteDto.class);
        throw new EntityNotFoundException("Entity Not Found");
    }

    public List<TrainingSiteDto> findAll(){
        List<TrainingSite> c_trainingSites = this.o_trainingSiteRepository.findAll();
        List<TrainingSiteDto> c_trainingSiteDto = new ArrayList<>();
        for(TrainingSite o_trainingSite: c_trainingSites){
            c_trainingSiteDto.add(o_modelMapper.map(o_trainingSite,TrainingSiteDto.class));
        }
        return c_trainingSiteDto;
    }

    public List<TrainingSiteDto> findAllById(List<String> ids){
        List<TrainingSite> c_trainingSites = this.o_trainingSiteRepository.findAllById(ids);
        List<TrainingSiteDto> c_trainingSiteDto = new ArrayList<>();
        for(TrainingSite o_trainingSite: c_trainingSites){
            c_trainingSiteDto.add(o_modelMapper.map(o_trainingSite,TrainingSiteDto.class));
        }
        return c_trainingSiteDto;
    }

    public TrainingSiteDto save(TrainingSite o_trainingSite) throws EntityExistsException {
        Optional<TrainingSite> o_trainingSiteOptional = this.o_trainingSiteRepository.save(o_trainingSite);
        if(o_trainingSiteOptional.isPresent())
            return o_modelMapper.map(o_trainingSiteOptional.get(),TrainingSiteDto.class);
        else
            throw new EntityExistsException();
    }


    public List<TrainingSiteDto>  saveAll(List<TrainingSiteDto> c_trainingSites) throws EntityExistsException{
        List<TrainingSite> c_resultTrainingSites = this.o_trainingSiteRepository.saveAll(DtoToEntity(c_trainingSites));
        List<TrainingSiteDto> c_trainingSiteDto = new ArrayList<>();
        if(c_resultTrainingSites.size()!=0) {
            for(TrainingSite o_trainingSite: c_resultTrainingSites){
                c_trainingSiteDto.add(o_modelMapper.map(o_trainingSite,TrainingSiteDto.class));
            }
            return c_trainingSiteDto;
        }
        else
            throw new EntityExistsException();
    }

    private TrainingSite DtoToEntity(TrainingSiteDto o_trainingSiteDto){
        return o_modelMapper.map(o_trainingSiteDto,TrainingSite.class);
    }

    private List<TrainingSite> DtoToEntity(List<TrainingSiteDto> c_trainingSiteDto){
        List<TrainingSite> c_trainingSite= new ArrayList<>();
        for(TrainingSiteDto o_trainingSiteDto: c_trainingSiteDto){
            c_trainingSite.add(DtoToEntity(o_trainingSiteDto));
        }
        return c_trainingSite;
    }
}
