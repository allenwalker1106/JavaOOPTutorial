package com.example.CarParkApi.Service;

import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Entity.BookingOffice;
import com.example.CarParkApi.Entity.Ticket;
import com.example.CarParkApi.Entity.Trip;
import com.example.CarParkApi.Repository.TripRepository;
import com.example.CarParkApi.Util.Converter;
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
            TripDto o_tripDto = o_converter.TripToDTO(o_dbTrip);
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
            return Optional.of(o_converter.TripToDTO(o_trip));
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
                o_bookingOfficeService.delete(o_converter.BookingOfficeToDTO(bookingOffice));

            for(Ticket ticket: tickets)
                o_ticketService.delete(o_converter.TicketToDTO(ticket));

            o_trip = o_tripRepository.getById(o_tripDto.getId());
            o_tripRepository.delete(o_trip);
            return true;
        }
        return false;
    }

    public List<TripDto> findAll(){
        return o_converter.TripToDTO(o_tripRepository.findAll());
    }

    public List<TripDto> findByDriver(String driver){
        return o_converter.TripToDTO(o_tripRepository.findByDriverContains(driver));
    }

    public List<TripDto> findByDestination(String destination){
        return o_converter.TripToDTO(o_tripRepository.findByDestinationContains(destination));
    }

    public List<TripDto> findByDate(String date){
        try{
            LocalDate o_date = LocalDate.parse(date);
            return o_converter.TripToDTO(o_tripRepository.findByDepartureDate(o_date));
        }catch(DateTimeParseException e){
            return List.of();
        }
    }

    public List<TripDto> findById(String ids){
        Set<Long> c_ids = o_converter.toIdList(ids);
        return o_converter.TripToDTO(o_tripRepository.findAllById(c_ids));
    }

    public List<TripDto> findByTime(String time){
        try{
            LocalTime o_date = LocalTime.parse(time);
            return o_converter.TripToDTO(o_tripRepository.findByDepartureTime(o_date));
        }catch(DateTimeParseException e){
            return List.of();
        }
    }



}
