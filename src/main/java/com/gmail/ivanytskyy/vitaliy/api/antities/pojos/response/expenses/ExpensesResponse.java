package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.expenses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesResponse {
    private String status;
    private List<ExpenseData> data;
    private Integer currentPage;
    private Integer totalItems;
}