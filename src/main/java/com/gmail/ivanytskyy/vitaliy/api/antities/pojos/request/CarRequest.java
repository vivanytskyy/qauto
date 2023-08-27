package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 20/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRequest {
    private Integer carBrandId;
    private Integer carModelId;
    private Integer mileage;
}