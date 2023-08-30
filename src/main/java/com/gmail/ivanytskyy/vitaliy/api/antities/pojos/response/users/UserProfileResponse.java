package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponse {
    private String status;
    private ProfileData data;
}