package com.diegogarcia.parkingcontrol.dtos;

import com.diegogarcia.parkingcontrol.models.ParkingSpotModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDTO {
    @NotBlank
    @Size(max = 10)
    private String parkingSpotNumber;

    @NotNull
    private CarDTO car;

    @NotBlank
    @Size(max = 130)
    private String responsibleName;

    @NotBlank
    @Size(max = 30)
    private String apartment;

    @NotBlank
    @Size(max = 30)
    private String block;

    public ParkingSpotModel getParkingSpotModel() {
        var model = new ParkingSpotModel();
        BeanUtils.copyProperties(this, model);

        model.setCar(this.car.getCarModel());
        return model;
    }
}
