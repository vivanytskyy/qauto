package com.gmail.ivanytskyy.vitaliy.api.pojos.request;

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
public class ChangeEmailRequest {
    private String email;
    private String password;
}