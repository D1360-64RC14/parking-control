package com.diegogarcia.parkingcontrol.services;

import com.diegogarcia.parkingcontrol.models.ParkingSpotModel;
import com.diegogarcia.parkingcontrol.repositories.CarRepository;
import com.diegogarcia.parkingcontrol.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {
    final ParkingSpotRepository spotRepository;
    final CarRepository carRepository;

    public ParkingSpotService(ParkingSpotRepository spotRepository, CarRepository carRepository) {
        this.spotRepository = spotRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return spotRepository.save(parkingSpotModel);
    }

    public boolean existsByCarLicensePlate(String licensePlate) {
        return carRepository.existsByLicensePlate(licensePlate);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return spotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return spotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return spotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return spotRepository.findById(id);
    }

    @Transactional
    public void deleteById(UUID id) {
        spotRepository.deleteById(id);
    }
}
