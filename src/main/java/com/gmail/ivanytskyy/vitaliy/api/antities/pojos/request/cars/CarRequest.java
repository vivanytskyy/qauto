package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.cars;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 20/08/2023
 */
@Data
@Builder
public class CarRequest {
    private Integer carBrandId;
    private Integer carModelId;
    private Integer mileage;
}