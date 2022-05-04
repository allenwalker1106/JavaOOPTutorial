package com.example.CarParkApi.Service;

import com.example.CarParkApi.DTO.ParkingLotDto;
import com.example.CarParkApi.Entity.Car;
import com.example.CarParkApi.Entity.ParkingLot;
import com.example.CarParkApi.Repository.CarRepository;
import com.example.CarParkApi.Repository.ParkingLotRepository;
import com.example.CarParkApi.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository o_parkingLotRepository;

    @Autowired
    private Converter o_converter;

    @Autowired
    private CarRepository o_carRepository;

    public Optional<ParkingLotDto> save(ParkingLot o_parkingLot){
        try{
            o_parkingLot.setStatus("Empty");
            ParkingLot o_parkingLotDB= o_parkingLotRepository.save(o_parkingLot);
            ParkingLotDto o_parkingLotDto = o_converter.ParkingLotToDTO(o_parkingLotDB);
            return Optional.of(o_parkingLotDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<ParkingLotDto> update(ParkingLotDto o_parkingLotDto){
        if(o_parkingLotRepository.existsById(o_parkingLotDto.getId())){
            ParkingLot o_parkingLot = o_parkingLotRepository.getById(o_parkingLotDto.getId());

            o_parkingLot.setName(o_parkingLotDto.getName());
            o_parkingLot.setPlace(o_parkingLotDto.getPlace());
            o_parkingLot.setArea(o_parkingLotDto.getArea());
            o_parkingLot.setPrice(o_parkingLotDto.getPrice());

            o_parkingLot=o_parkingLotRepository.save(o_parkingLot);
            return Optional.of(o_converter.ParkingLotToDTO(o_parkingLot));
        }else{
            return Optional.empty();
        }
    }

    public boolean delete(ParkingLotDto o_parkingLotDto){
        if(o_parkingLotRepository.existsById(o_parkingLotDto.getId())){
            ParkingLot o_parkingLot = o_parkingLotRepository.getById(o_parkingLotDto.getId());
            Set<Car> o_cars = o_parkingLot.getCars();
            for(Car o_car : o_cars){
                o_carRepository.delete(o_car);
            }
            o_parkingLotRepository.delete(o_parkingLot);
            return true;
        }
        return false;
    }

    public List<ParkingLotDto> findAll(){
        return o_converter.ParkingLotToDTO(o_parkingLotRepository.findAll());
    }

    public List<ParkingLotDto> findByName(String name){
        return o_converter.ParkingLotToDTO(o_parkingLotRepository.findByNameContains(name));
    }

    public List<ParkingLotDto> findByPlace(String place){
        return o_converter.ParkingLotToDTO(o_parkingLotRepository.findByPlaceContains(place));
    }

    public List<ParkingLotDto> findByArea(String area){
        try{
            Long l_area = Long.parseLong(area);
            return o_converter.ParkingLotToDTO(o_parkingLotRepository.findByArea(l_area));
        }catch(NumberFormatException e){
            return List.of();
        }
    }

    public List<ParkingLotDto> findByPrice(String price){
        try{
            Long l_price = Long.parseLong(price);
            return o_converter.ParkingLotToDTO(o_parkingLotRepository.findByPrice(l_price));
        }catch(NumberFormatException e){
            return List.of();
        }
    }

    public List<ParkingLotDto> findByStatus(String status){
        return o_converter.ParkingLotToDTO(o_parkingLotRepository.findByStatusContains(status));
    }

    public List<ParkingLotDto> findById(String ids){
        Set<Long> c_ids = o_converter.toIdList(ids);
        return o_converter.ParkingLotToDTO(o_parkingLotRepository.findAllById(c_ids));
    }

    public boolean takeSpot(ParkingLotDto o_parkingLotDto){
        if(o_parkingLotRepository.existsById(o_parkingLotDto.getId())){
            ParkingLot o_parkingLot = o_parkingLotRepository.getById(o_parkingLotDto.getId());
            if(o_parkingLot.getStatus().equalsIgnoreCase("EMPTY")){
                o_parkingLot.setStatus("Occupied");
            }
            o_parkingLot = o_parkingLotRepository.save(o_parkingLot);
            return (o_parkingLot!=null);
        }
        return false;
    }

    public boolean clearSpot(ParkingLotDto o_parkingLotDto){
        if(o_parkingLotRepository.existsById(o_parkingLotDto.getId())){
            ParkingLot o_parkingLot = o_parkingLotRepository.getById(o_parkingLotDto.getId());
            if(!o_parkingLot.getStatus().equalsIgnoreCase("EMPTY")){
                o_parkingLot.setStatus("Empty");
            }
            o_parkingLot = o_parkingLotRepository.save(o_parkingLot);
            return (o_parkingLot!=null);
        }
        return false;
    }

    public boolean isEmptySpot(ParkingLotDto o_parkingLotDto){
        if(o_parkingLotRepository.existsById(o_parkingLotDto.getId())){
            return o_parkingLotRepository.getById(o_parkingLotDto.getId()).getStatus().equalsIgnoreCase("EMPTY");
        }
        return false;
    }
}
