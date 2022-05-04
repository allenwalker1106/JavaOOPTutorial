package com.manager.CarPark.Util;

import com.manager.CarPark.DTO.*;
import com.manager.CarPark.Entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Converter {
    @Autowired
    ModelMapper o_modelMapper;


    public Set<Long> toIdList(String str_id){
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(str_id);
        Set<Long> ids = new HashSet<>();
        while(matcher.find()){
            try{
                ids.add(Long.parseLong(matcher.group()));
            }catch(NumberFormatException e){
                continue;
            }
        }
        return ids;
    }

    public EmployeeDto employeeToDTO(Employee o_employee){
        return o_modelMapper.map(o_employee,EmployeeDto.class);
    }

    public List<EmployeeDto> employeeToDTO(List<Employee> c_employee){
        List<EmployeeDto> c_employeeDto= new ArrayList<>();
        for(Employee o_employee:c_employee)
                c_employeeDto.add(employeeToDTO(o_employee));
        return c_employeeDto;
    }

    public TripDto tripToDTO(Trip o_trip){
        return o_modelMapper.map(o_trip,TripDto.class);
    }

    public List<TripDto> tripToDTO(List<Trip> c_trip){
        List<TripDto> c_tripDto= new ArrayList<>();
        for(Trip o_trip:c_trip)
            c_tripDto.add(tripToDTO(o_trip));
        return c_tripDto;
    }

    public Trip dtoToTrip(TripDto o_tripDto){
        return o_modelMapper.map(o_tripDto,Trip.class);
    }

    public List<Trip> dtoToTrip(List<TripDto> c_tripDto){
        List<Trip> c_trip= new ArrayList<>();
        for(TripDto o_tripDto:c_tripDto)
            c_trip.add(dtoToTrip(o_tripDto));
        return c_trip;
    }

    public BookingOfficeDto bookingOfficeToDTO(BookingOffice o_bookingOffice){
        return o_modelMapper.map(o_bookingOffice,BookingOfficeDto.class);
    }

    public List<BookingOfficeDto> bookingOfficeToDTO(List<BookingOffice> c_bookingOffice){
        List<BookingOfficeDto> c_bookingOfficeDto= new ArrayList<>();
        for(BookingOffice o_bookingOffice:c_bookingOffice)
            c_bookingOfficeDto.add(bookingOfficeToDTO(o_bookingOffice));
        return c_bookingOfficeDto;
    }

    public BookingOffice DtoToBookingOffice(BookingOfficeDto o_bookingOfficeDto){
        return o_modelMapper.map(o_bookingOfficeDto,BookingOffice.class);
    }

    public List<BookingOffice> DtoToBookingOffice(List<BookingOfficeDto> c_bookingOfficeDto){
        List<BookingOffice> c_bookingOffice= new ArrayList<>();
        for(BookingOfficeDto o_bookingOfficeDto:c_bookingOfficeDto)
            c_bookingOffice.add(DtoToBookingOffice(o_bookingOfficeDto));
        return c_bookingOffice;
    }

    public TicketDto ticketToDTO(Ticket o_ticket){
        return o_modelMapper.map(o_ticket,TicketDto.class);
    }

    public List<TicketDto> ticketToDTO(List<Ticket> c_ticket){
        List<TicketDto> c_ticketDto= new ArrayList<>();
        for(Ticket o_ticket:c_ticket)
            c_ticketDto.add(ticketToDTO(o_ticket));
        return c_ticketDto;
    }

    public ParkingLotDto parkingLotToDTO(ParkingLot o_parkingLot){
        return o_modelMapper.map(o_parkingLot,ParkingLotDto.class);
    }

    public List<ParkingLotDto> parkingLotToDTO(List<ParkingLot> c_parkingLot){
        List<ParkingLotDto> c_parkingLotDto= new ArrayList<>();
        for(ParkingLot o_parkingLot:c_parkingLot)
            c_parkingLotDto.add(parkingLotToDTO(o_parkingLot));
        return c_parkingLotDto;
    }

    public ParkingLot dtoToParkingLot(ParkingLotDto o_parkingLotDto){
        return o_modelMapper.map(o_parkingLotDto,ParkingLot.class);
    }

    public List<ParkingLot> dtoToParkingLot(List<ParkingLotDto> c_parkingLotDto){
        List<ParkingLot> c_parkingLot= new ArrayList<>();
        for(ParkingLotDto o_parkingLotDto:c_parkingLotDto)
            c_parkingLot.add(dtoToParkingLot(o_parkingLotDto));
        return c_parkingLot;
    }

    public Car DtoToCar(CarDto o_carDto){

        return o_modelMapper.map(o_carDto,Car.class);
    }

    public List<Car> DtoToCar(List<CarDto> c_carDto){
        List<Car> c_car= new ArrayList<>();
        for(CarDto o_carDto:c_carDto)
            c_car.add(DtoToCar(o_carDto));
        return c_car;
    }

    public CarDto carToDto(Car o_car){
        return o_modelMapper.map(o_car,CarDto.class);
    }

    public List<CarDto> carToDto(List<Car> c_car){
        List<CarDto> c_carDto= new ArrayList<>();
        for(Car o_car:c_car)
            c_carDto.add(carToDto(o_car));
        return c_carDto;
    }

    public Ticket DtoToTicket(TicketDto o_ticketDto){
        return o_modelMapper.map(o_ticketDto,Ticket.class);
    }

    public List<Ticket> DtoToTicket(List<TicketDto> c_ticketDto){
        List<Ticket> c_ticket= new ArrayList<>();
        for(TicketDto o_ticketDto:c_ticketDto)
            c_ticket.add(DtoToTicket(o_ticketDto));
        return c_ticket;
    }


}
