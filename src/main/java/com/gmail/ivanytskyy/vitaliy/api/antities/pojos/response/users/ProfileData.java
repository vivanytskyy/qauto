package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileData {
    private Integer userId;
    private String name;
    private String lastName;
    private String photoFilename;
    private String dateBirth;
    private String country;
}