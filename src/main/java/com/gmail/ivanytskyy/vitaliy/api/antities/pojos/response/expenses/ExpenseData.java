package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.expenses;

import lombok.AllArgsConstructor;
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
public class ExpenseData {
    private Integer id;
    private Integer carId;
    private String reportedAt;
    private Integer mileage;
    private Float liters;
    private Float totalCost;
}