package com.diegogarcia.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDTO {
    @NotBlank
    @Size(max = 10)
    private String parkingSpotNumber;

    @NotBlank
    @Size(max = 7)
    private String licensePlateCar;

    @NotBlank
    @Size(max = 70)
    private String brandCar;

    @NotBlank
    @Size(max = 70)
    private String modelCar;

    @NotBlank
    @Size(max = 70)
    private String colorCar;

    @NotBlank
    @Size(max = 130)
    private String responsibleName;

    @NotBlank
    @Size(max = 30)
    private String apartment;

    @NotBlank
    @Size(max = 30)
    private String block;
}
