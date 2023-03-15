package com.diegogarcia.parkingcontrol.dtos;

import com.diegogarcia.parkingcontrol.models.CarModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    @Size(max = 7)
    @NotBlank
    private String licensePlate;

    @Size(max = 70)
    @NotBlank
    private String brand;

    @Size(max = 70)
    @NotBlank
    private String model;

    @Size(max = 70)
    @NotBlank
    private String color;

    public CarModel getCarModel() {
        var model = new CarModel();
        BeanUtils.copyProperties(this, model);

        return model;
    }
    public CarModel getCarModelFrom(CarModel baseModel) {
        BeanUtils.copyProperties(this, baseModel);

        return baseModel;
    }
}
