package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.cars;

import lombok.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 19/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarData {
    private Integer id;
    private Integer carBrandId;
    private Integer carModelId;
    private Integer initialMileage;
    @EqualsAndHashCode.Exclude
    private String updatedMileageAt;
    @EqualsAndHashCode.Exclude
    private String carCreatedAt;
    private Integer mileage;
    private String brand;
    private String model;
    private String logo;
}