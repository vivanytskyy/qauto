package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.cars;

import lombok.AllArgsConstructor;
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
public class CarModelData {
    private Integer id;
    private Integer carBrandId;
    private String title;
}