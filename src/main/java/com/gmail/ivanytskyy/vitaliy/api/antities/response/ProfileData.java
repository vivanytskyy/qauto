package com.gmail.ivanytskyy.vitaliy.api.antities.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileData {
    private Integer userId;
    private String name;
    private String lastName;
    private String photoFilename;
    private String dateBirth;
    private String country;
}