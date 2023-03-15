package com.diegogarcia.parkingcontrol.repositories;

import com.diegogarcia.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    @Query("""
            SELECT COUNT(car) > 0
            FROM ParkingSpotModel
            WHERE car.licensePlate = :licensePlate
            """)
    boolean existsByCarLicensePlate(String licensePlate);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartmentAndBlock(String apartment, String block);
}
