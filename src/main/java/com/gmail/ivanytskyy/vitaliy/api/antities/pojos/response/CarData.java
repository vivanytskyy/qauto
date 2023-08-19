package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 19/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarData {
    private Integer id;
    private Integer carBrandId;
    private Integer carModelId;
    private Integer initialMileage;
    private String updatedMileageAt;
    private Integer mileage;
    private String brand;
    private String model;
    private String logo;
}