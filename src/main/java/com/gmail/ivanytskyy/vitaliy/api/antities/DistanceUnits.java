package com.gmail.ivanytskyy.vitaliy.api.antities;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
public enum DistanceUnits {
    KM("km"), ML("ml");
    private final String value;
    DistanceUnits(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}