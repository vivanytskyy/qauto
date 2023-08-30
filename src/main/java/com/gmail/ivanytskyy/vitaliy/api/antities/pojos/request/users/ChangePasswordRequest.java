package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
@Data
@Builder
public class ChangePasswordRequest {
    private String oldPassword;
    private String password;
    private String repeatPassword;
}