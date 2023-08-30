package com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.instructions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructionData {
    private Integer id;
    private String filename;
    private String description;
    private Integer carBrandId;
    private Integer carModelId;
}