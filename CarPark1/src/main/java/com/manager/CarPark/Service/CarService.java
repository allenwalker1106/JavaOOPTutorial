package com.manager.CarPark.Service;

import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.CarDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.*;
import com.manager.CarPark.Repository.BookingOfficeRepository;
import com.manager.CarPark.Repository.CarRepository;
import com.manager.CarPark.Repository.ParkingLotRepository;
import com.manager.CarPark.Repository.TicketRepository;
import com.manager.CarPark.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService {
    @Autowired
    private CarRepository o_carRepository;

    @Autowired
    private TicketService o_ticketService;

    @Autowired
    private ParkingLotService o_parkingLotService;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    private Converter o_converter;

    public Optional<CarDto> save(Car o_car){
        try{
            o_parkingLotService.takeSpot(o_converter.parkingLotToDTO(o_car.getParkingLot()));
            Car o_dbCar= o_carRepository.save(o_car);
            CarDto o_carDto = o_converter.carToDto(o_dbCar);
            return Optional.of(o_carDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<CarDto> update(CarDto o_carDto){
        if(o_carRepository.existsById(o_carDto.getId())){
            Car o_car = o_carRepository.getById(o_carDto.getId());

            o_car.setType(o_carDto.getType());
            o_car.setColor(o_carDto.getColor());
            o_car.setCompany(o_carDto.getCompany());

            o_car=o_carRepository.save(o_car);
            return Optional.of(o_converter.carToDto(o_car));
        }else{
            return Optional.empty();
        }
    }

    public boolean delete(CarDto o_carDto){
        if(o_carRepository.existsById(o_carDto.getId())){
            Car o_car = o_carRepository.getById(o_carDto.getId());
            Set<Ticket> c_tickets = o_car.getTickets();
            for(Ticket o_ticket : c_tickets){
                o_ticketService.delete(o_converter.ticketToDTO(o_ticket));
            }

            ParkingLot o_parkingLot = o_car.getParkingLot();
            o_parkingLotService.clearSpot(o_converter.parkingLotToDTO(o_parkingLot));

            o_carRepository.delete(o_car);
            return true;
        }
        return false;
    }

    public List<CarDto> findAll(){
        return o_converter.carToDto(o_carRepository.findAll());
    }

    public List<CarDto> findById(String ids){
        return o_converter.carToDto(o_carRepository.findByIdContains(ids));
    }

    public List<CarDto> findByCompany(String company){
        return o_converter.carToDto(o_carRepository.findByCompanyContains(company));
    }


}
