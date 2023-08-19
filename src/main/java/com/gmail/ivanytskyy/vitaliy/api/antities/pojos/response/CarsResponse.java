package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class CarsResponse {
    private String status;
    private List<CarData> data;
}