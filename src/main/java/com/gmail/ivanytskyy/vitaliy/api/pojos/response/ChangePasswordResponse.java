package com.gmail.ivanytskyy.vitaliy.api.pojos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class ChangePasswordResponse {
    private String status;
    private ChangePasswordData data;
}