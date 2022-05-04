package com.example.CarParkApi.Util;

import com.example.CarParkApi.DTO.*;
import com.example.CarParkApi.Entity.*;
import com.example.CarParkApi.Model.EmployeeModel;
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

    public EmployeeDto ModelToDto(EmployeeModel o_employee){
        return o_modelMapper.map(o_employee,EmployeeDto.class);
    }

    public List<EmployeeDto> ModelToDto(List<EmployeeModel> c_employee){
        List<EmployeeDto> c_employeeDto= new ArrayList<>();
        for(EmployeeModel o_employee:c_employee)
            c_employeeDto.add(ModelToDto(o_employee));
        return c_employeeDto;
    }

    public Employee ModelToEntity(EmployeeModel o_employeeModel){
        return o_modelMapper.map(o_employeeModel,Employee.class);
    }

    public List<Employee> ModelToEntity(List<EmployeeModel> c_employeeModel){
        List<Employee> c_employee= new ArrayList<>();
        for(EmployeeModel o_employeeModel:c_employeeModel)
            c_employee.add(ModelToEntity(o_employeeModel));
        return c_employee;
    }

    public EmployeeDto EmployeeToDto(Employee o_employee){
        return o_modelMapper.map(o_employee,EmployeeDto.class);
    }

    public List<EmployeeDto> EmployeeToDto(List<Employee> c_employee){
        List<EmployeeDto> c_employeeDto= new ArrayList<>();
        for(Employee o_employee:c_employee)
            c_employeeDto.add(EmployeeToDto(o_employee));
        return c_employeeDto;
    }

    public Employee DtoToEmployee(EmployeeDto o_employeeDto ){
        return o_modelMapper.map(o_employeeDto,Employee.class);
    }




    public List<Employee> DtoToEmployee(List<EmployeeDto> c_employeeDto){
        List<Employee> c_employee= new ArrayList<>();
        for(EmployeeDto o_employeeDto:c_employeeDto)
            c_employee.add(DtoToEmployee(o_employeeDto));
        return c_employee;
    }

    public BookingOfficeDto BookingOfficeToDTO(BookingOffice o_bookingOffice){
        return o_modelMapper.map(o_bookingOffice,BookingOfficeDto.class);
    }

    public List<BookingOfficeDto> BookingOfficeToDTO(List<BookingOffice> c_bookingOffice){
        List<BookingOfficeDto> c_bookingOfficeDto= new ArrayList<>();
        for(BookingOffice o_bookingOffice:c_bookingOffice)
            c_bookingOfficeDto.add(BookingOfficeToDTO(o_bookingOffice));
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





    public ParkingLotDto ParkingLotToDTO(ParkingLot o_parkingLot){
        return o_modelMapper.map(o_parkingLot,ParkingLotDto.class);
    }

    public List<ParkingLotDto> ParkingLotToDTO(List<ParkingLot> c_parkingLot){
        List<ParkingLotDto> c_parkingLotDto= new ArrayList<>();
        for(ParkingLot o_parkingLot:c_parkingLot)
            c_parkingLotDto.add(ParkingLotToDTO(o_parkingLot));
        return c_parkingLotDto;
    }

    public ParkingLot DtoToParkingLot(ParkingLotDto o_parkingLotDto){
        return o_modelMapper.map(o_parkingLotDto,ParkingLot.class);
    }

    public List<ParkingLot> DtoToParkingLot(List<ParkingLotDto> c_parkingLotDto){
        List<ParkingLot> c_parkingLot= new ArrayList<>();
        for(ParkingLotDto o_parkingLotDto:c_parkingLotDto)
            c_parkingLot.add(DtoToParkingLot(o_parkingLotDto));
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

    public CarDto CarToDto(Car o_car){
        return o_modelMapper.map(o_car,CarDto.class);
    }

    public List<CarDto> CarToDto(List<Car> c_car){
        List<CarDto> c_carDto= new ArrayList<>();
        for(Car o_car:c_car)
            c_carDto.add(CarToDto(o_car));
        return c_carDto;
    }




    public TicketDto TicketToDTO(Ticket o_ticket){
        return o_modelMapper.map(o_ticket,TicketDto.class);
    }

    public List<TicketDto> TicketToDTO(List<Ticket> c_ticket){
        List<TicketDto> c_ticketDto= new ArrayList<>();
        for(Ticket o_ticket:c_ticket)
            c_ticketDto.add(TicketToDTO(o_ticket));
        return c_ticketDto;
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




    public TripDto TripToDTO(Trip o_trip){
        return o_modelMapper.map(o_trip,TripDto.class);
    }

    public List<TripDto> TripToDTO(List<Trip> c_trip){
        List<TripDto> c_tripDto= new ArrayList<>();
        for(Trip o_trip:c_trip)
            c_tripDto.add(TripToDTO(o_trip));
        return c_tripDto;
    }

    public Trip DtoToTrip(TripDto o_tripDto){
        return o_modelMapper.map(o_tripDto,Trip.class);
    }

    public List<Trip> DtoToTrip(List<TripDto> c_tripDto){
        List<Trip> c_trip= new ArrayList<>();
        for(TripDto o_tripDto:c_tripDto)
            c_trip.add(DtoToTrip(o_tripDto));
        return c_trip;
    }
}
