package com.manager.CarPark.Repository;

import com.manager.CarPark.Entity.BookingOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingOfficeRepository extends JpaRepository<BookingOffice,Long> {
    List<BookingOffice> findByNameContains(@Param("name") String name);
    List<BookingOffice> findByPhoneNumberContains(@Param("phoneNumber") String phoneNumber);
    List<BookingOffice> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    List<BookingOffice> findByPlaceContains(@Param("place") String place);
    List<BookingOffice> findByPlace(@Param("place") String place);
    @Query(value="SELECT bo FROM BookingOffice bo INNER JOIN Trip t ON bo.id = t.id WHERE t.destination Like %:tripName%")
    List<BookingOffice> findByTripContains(@Param("tripName") String tripName);
}
