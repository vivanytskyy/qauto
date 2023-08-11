package com.gmail.ivanytskyy.vitaliy.api.antities;

import lombok.Builder;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@lombok.Data
@Builder
public class User {
    private String status;
    private Data data;
}