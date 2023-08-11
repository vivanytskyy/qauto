package com.gmail.ivanytskyy.vitaliy.api.antities.auth;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@Data
@Builder
public class AuthorizationUserCredentialsWrapper {
    private String email;
    private String password;
    private boolean remember;
}