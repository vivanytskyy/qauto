package com.gmail.ivanytskyy.vitaliy.ui.utils.units;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/11/2023
 */
public enum StringConstants {
    DATE_FORMAT("dd.MM.yyyy");
    private final String value;
    StringConstants(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}