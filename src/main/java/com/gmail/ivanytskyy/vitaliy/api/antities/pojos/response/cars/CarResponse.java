package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.cars;

import lombok.AllArgsConstructor;
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
public class CarResponse {
    public String status;
    public CarData data;
}