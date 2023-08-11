package com.gmail.ivanytskyy.vitaliy.api.utils;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public enum ControllerNames {
    AUTH("/auth"),
    USERS("/users"),
    CARS("/cars"),
    EXPENSES("/expenses"),
    INSTRUCTIONS("/instructions");
    private final String path;
    ControllerNames(String endPoint){
        this.path = endPoint;
    }
    public String getPath(){
        return path;
    }
}