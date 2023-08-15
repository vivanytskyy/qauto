package com.gmail.ivanytskyy.vitaliy.api.antities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    private String status;
    private Data data;
}