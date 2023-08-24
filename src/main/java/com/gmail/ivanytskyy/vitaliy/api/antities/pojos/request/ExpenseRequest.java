package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {
    private Integer carId;
    private String reportedAt;
    private Integer mileage;
    private Float liters;
    private Float totalCost;
    private Boolean forceMileage;
}