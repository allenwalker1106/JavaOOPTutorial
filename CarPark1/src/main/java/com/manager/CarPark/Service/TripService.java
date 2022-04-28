package com.manager.CarPark.Service;

import com.manager.CarPark.DTO.BookingOfficeDto;
import com.manager.CarPark.DTO.EmployeeDto;
import com.manager.CarPark.DTO.TicketDto;
import com.manager.CarPark.DTO.TripDto;
import com.manager.CarPark.Entity.*;
import com.manager.CarPark.Repository.PermissionRepository;
import com.manager.CarPark.Repository.TripRepository;
import com.manager.CarPark.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TripService {

    @Autowired
    private TripRepository o_tripRepository;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    private BookingOfficeService o_bookingOfficeService;

    @Autowired
    private TicketService o_ticketService;

    @Autowired
    private Converter o_converter;

    public Optional<TripDto> save(Trip o_trip){
        try{
            o_trip.setBookedTicketNumber(0L);
            Trip o_dbTrip= o_tripRepository.save(o_trip);
            TripDto o_tripDto = o_converter.tripToDTO(o_dbTrip);
            return Optional.of(o_tripDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<TripDto> update(TripDto o_tripDto){
        if(o_tripRepository.existsById(o_tripDto.getId())){
            Trip o_trip = o_tripRepository.getById(o_tripDto.getId());

            o_trip.setMaximumOnlineTicketNumber(o_tripDto.getMaximumOnlineTicketNumber());
            o_trip.setCarType(o_tripDto.getCarType());
            o_trip.setDestination(o_tripDto.getDestination());
            o_trip.setDriver(o_tripDto.getDriver());

            o_trip=o_tripRepository.save(o_trip);
            return Optional.of(o_converter.tripToDTO(o_trip));
        }else{
            return Optional.empty();
        }
    }

    public boolean delete(TripDto o_tripDto){
        if(o_tripRepository.existsById(o_tripDto.getId())){
            Trip o_trip = o_tripRepository.getById(o_tripDto.getId());
            Set<BookingOffice> bookingOffices = o_trip.getBookingOffices();
            Set<Ticket> tickets = o_trip.getTickets();
            for(BookingOffice bookingOffice: bookingOffices)
                o_bookingOfficeService.delete(o_converter.bookingOfficeToDTO(bookingOffice));

            for(Ticket ticket: tickets)
                o_ticketService.delete(o_converter.ticketToDTO(ticket));

            o_trip = o_tripRepository.getById(o_tripDto.getId());
            o_tripRepository.delete(o_trip);
            return true;
        }
        return false;
    }

    public List<TripDto> findAll(){
        return o_converter.tripToDTO(o_tripRepository.findAll());
    }

    public List<TripDto> findByDriver(String driver){
        return o_converter.tripToDTO(o_tripRepository.findByDriverContains(driver));
    }

    public List<TripDto> findByDestination(String destination){
        return o_converter.tripToDTO(o_tripRepository.findByDestinationContains(destination));
    }

    public List<TripDto> findByDate(String date){
        try{
            LocalDate o_date = LocalDate.parse(date);
            return o_converter.tripToDTO(o_tripRepository.findByDepartureDate(o_date));
        }catch(DateTimeParseException e){
            return List.of();
        }
    }

    public List<TripDto> findById(String ids){
        Set<Long> c_ids = o_converter.toIdList(ids);
        return o_converter.tripToDTO(o_tripRepository.findAllById(c_ids));
    }

    public List<TripDto> findByTime(String time){
        try{
            LocalTime o_date = LocalTime.parse(time);
            return o_converter.tripToDTO(o_tripRepository.findByDepartureTime(o_date));
        }catch(DateTimeParseException e){
            return List.of();
        }
    }



}
