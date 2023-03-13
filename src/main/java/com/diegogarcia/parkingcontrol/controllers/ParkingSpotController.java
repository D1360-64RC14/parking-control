package com.diegogarcia.parkingcontrol.controllers;

import com.diegogarcia.parkingcontrol.dtos.ParkingSpotDTO;
import com.diegogarcia.parkingcontrol.models.ParkingSpotModel;
import com.diegogarcia.parkingcontrol.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/parking-spot")
public class ParkingSpotController {
    final ParkingSpotService service;

    public ParkingSpotController(ParkingSpotService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllSpots(
            @PageableDefault(sort = "id") Pageable pageable
    ) {
        var allParkingSpots = service.findAll(pageable);

        return ResponseEntity.ok(allParkingSpots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotModel> getSingleSpot(@PathVariable UUID id) {
        var maybeParkingSpot = service.findById(id);

        if(maybeParkingSpot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var parkingSpot = maybeParkingSpot.get();

        return ResponseEntity.ok(parkingSpot);
    }

    @PostMapping
    public ResponseEntity<Object> saveSpot(
            @RequestBody @Valid ParkingSpotDTO parkingSpotDTO
    ) {
        if (service.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflict: License plate is already in use!");
        }
        if (service.existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflict: Parking spot is already in use!");
        }
        if (service.existsByApartmentAndBlock(parkingSpotDTO.getApartment(), parkingSpotDTO.getBlock())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflict: Parking apot already registered for this apartment/block!");
        }

        var parkingSpot = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);

        var currentUtcDate = LocalDateTime.now(ZoneOffset.UTC);
        parkingSpot.setRegistrationDate(currentUtcDate);

        var savedParkingSpot = service.save(parkingSpot);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedParkingSpot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSpot(@PathVariable UUID id) {
        var maybeParkingSpot = service.findById(id);

        if (maybeParkingSpot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSpot(
            @PathVariable UUID id,
            @RequestBody @Valid ParkingSpotDTO parkingSpotDTO
    ) {
        var maybeParkingSpot = service.findById(id);

        if (maybeParkingSpot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var parkingSpot = maybeParkingSpot.get();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);

        var savedSpot = service.save(parkingSpot);

        return ResponseEntity.ok(savedSpot);
    }
}
