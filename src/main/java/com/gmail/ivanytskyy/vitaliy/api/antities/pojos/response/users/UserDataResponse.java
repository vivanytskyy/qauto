package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
    private String status;
    private UserData data;
}