package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/08/2023
 */
@Data
@Builder
public class ResetPasswordRequest {
    private String email;
}