package com.example.CarParkApi.Repository;

import com.example.CarParkApi.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {
    List<ParkingLot> findByNameContains(@Param("name")String name);
    List<ParkingLot> findByPlaceContains(@Param("place")String place);
    List<ParkingLot> findByArea(@Param("area")Long area);
    List<ParkingLot> findByPrice(@Param("price")Long price);
    List<ParkingLot> findByStatusContains(@Param("status") String status);
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM ParkingLot p WHERE p.name = :name AND p.place= :place AND p.area = :area ")
    boolean existPlot(@Param("name")String name, @Param("place")String place, @Param("area")Long area);
}
