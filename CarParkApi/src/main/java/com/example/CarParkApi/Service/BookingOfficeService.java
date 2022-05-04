package com.example.CarParkApi.Service;

import com.example.CarParkApi.DTO.BookingOfficeDto;
import com.example.CarParkApi.Entity.BookingOffice;
import com.example.CarParkApi.Repository.BookingOfficeRepository;
import com.example.CarParkApi.Util.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookingOfficeService {

    @Autowired
    private BookingOfficeRepository o_bookingOfficeRepository;

    @Autowired
    private ModelMapper o_modelMapper;

    @Autowired
    private Converter o_converter;

    public Optional<BookingOfficeDto> save(BookingOffice o_bookingOffice){
        try{
            BookingOffice o_dbBookingOffice= o_bookingOfficeRepository.save(o_bookingOffice);
            BookingOfficeDto o_bookingOfficeDto = o_converter.BookingOfficeToDTO(o_dbBookingOffice);
            return Optional.of(o_bookingOfficeDto);
        }catch(Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<BookingOfficeDto> update(BookingOfficeDto bookingOffice){
        if(o_bookingOfficeRepository.existsById(bookingOffice.getId())){
            BookingOffice o_srcBookingOffice = o_bookingOfficeRepository.getById(bookingOffice.getId());
            if(bookingOffice!=null){
                o_srcBookingOffice.setName(bookingOffice.getName());
                o_srcBookingOffice.setPhoneNumber(bookingOffice.getPhoneNumber());
                o_srcBookingOffice.setPlace(bookingOffice.getPlace());
                o_srcBookingOffice.setPrice(bookingOffice.getPrice());

                o_bookingOfficeRepository.save(o_srcBookingOffice);
                return Optional.of(o_modelMapper.map(o_srcBookingOffice,BookingOfficeDto.class));
            }else{
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public boolean delete(BookingOfficeDto o_bookingOfficeDto){
        if(o_bookingOfficeRepository.existsById(o_bookingOfficeDto.getId())){
            BookingOffice o_bookingOffice = o_bookingOfficeRepository.getById(o_bookingOfficeDto.getId());
            o_bookingOfficeRepository.delete(o_bookingOffice);
            return true;
        }
        return false;
    }

    public List<BookingOfficeDto> findAll(){
        return o_converter.BookingOfficeToDTO(o_bookingOfficeRepository.findAll());
    }

    public List<BookingOfficeDto> findByName(String name){
        return o_converter.BookingOfficeToDTO(o_bookingOfficeRepository.findByNameContains(name));
    }

    public List<BookingOfficeDto> findByTrip(String tripName){
        return o_converter.BookingOfficeToDTO(o_bookingOfficeRepository.findByTripContains(tripName));
    }


    public List<BookingOfficeDto> findByPhone(String phoneNumber){
        return o_converter.BookingOfficeToDTO(o_bookingOfficeRepository.findByPhoneNumberContains(phoneNumber));
    }

    public List<BookingOfficeDto> findById(String ids){
        Set<Long> c_ids = o_converter.toIdList(ids);
        return o_converter.BookingOfficeToDTO(o_bookingOfficeRepository.findAllById(c_ids));
    }

}
