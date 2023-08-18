package com.gmail.ivanytskyy.vitaliy.api.pojos.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@Data
@Builder
public class RegistrationUserCredentials {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}