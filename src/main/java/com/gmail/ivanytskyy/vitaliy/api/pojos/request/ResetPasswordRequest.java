package com.gmail.ivanytskyy.vitaliy.api.pojos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/08/2023
 */
@Data
@AllArgsConstructor
public class ResetPasswordRequest {
    private String email;
}