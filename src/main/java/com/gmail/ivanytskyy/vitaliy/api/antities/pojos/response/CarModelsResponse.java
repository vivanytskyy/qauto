package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 19/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelsResponse {
    private String status;
    private List<CarModelData> data;
}