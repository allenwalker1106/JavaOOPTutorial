package com.example.CarParkApi.Service;

import com.example.CarParkApi.DTO.TicketDto;
import com.example.CarParkApi.DTO.TripDto;
import com.example.CarParkApi.Entity.Ticket;
import com.example.CarParkApi.Entity.Trip;
import com.example.CarParkApi.Repository.TicketRepository;
import com.example.CarParkApi.Repository.TripRepository;
import com.example.CarParkApi.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketService {

    @Autowired
    private TicketRepository o_ticketRepository;

    @Autowired
    TripRepository o_tripRepository;

    @Autowired
    private Converter o_converter;

    public Optional<TicketDto> save(Ticket o_trip){
        try{
            Ticket o_ticketDB= o_ticketRepository.save(o_trip);
            TicketDto o_ticketDto = o_converter.TicketToDTO(o_ticketDB);
            addTicket(o_ticketDto.getTrip());
            return Optional.of(o_ticketDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean delete(TicketDto o_ticketDto){
        if(o_ticketRepository.existsById(o_ticketDto.getId())){
            Ticket o_ticket = o_ticketRepository.getById(o_ticketDto.getId());
            o_ticketRepository.delete(o_ticket);
            removeTicket(o_ticketDto.getTrip());
            return true;
        }
        return false;
    }

    public List<TicketDto> findAll(){
        return o_converter.TicketToDTO(o_ticketRepository.findAll());
    }

    public List<TicketDto> findById(String ids){
        Set<Long> c_ids = o_converter.toIdList(ids);
        return o_converter.TicketToDTO(o_ticketRepository.findAllById(c_ids));
    }

    public void addTicket(TripDto o_tripDto){
        try{
            if(o_tripRepository.existsById(o_tripDto.getId())){
                Trip o_trip = o_tripRepository.getById(o_tripDto.getId());
                o_trip.setBookedTicketNumber(o_trip.getBookedTicketNumber()+1L);
                o_tripRepository.save(o_trip);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public void removeTicket(TripDto o_tripDto){
        try{
            if(o_tripRepository.existsById(o_tripDto.getId())){
                Trip o_trip = o_tripRepository.getById(o_tripDto.getId());
                o_trip.setBookedTicketNumber(o_trip.getBookedTicketNumber()-1L);
                o_tripRepository.save(o_trip);
            }
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}
