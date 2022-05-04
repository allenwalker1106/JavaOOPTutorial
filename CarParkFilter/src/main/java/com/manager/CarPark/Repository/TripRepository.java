package com.manager.CarPark.Repository;

import com.manager.CarPark.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByDriverContains(@Param("driver") String driver);
    List<Trip> findByDestinationContains(@Param("destination") String destination);
    List<Trip> findByDepartureDate(@Param("departureDate") LocalDate departureDate);
    List<Trip> findByDepartureTime(@Param("departureTime") LocalTime departureTime);
}
