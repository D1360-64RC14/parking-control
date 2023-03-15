package com.diegogarcia.parkingcontrol.repositories;

import com.diegogarcia.parkingcontrol.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID> {
    boolean existsByLicensePlate(String licensePlate);
}
