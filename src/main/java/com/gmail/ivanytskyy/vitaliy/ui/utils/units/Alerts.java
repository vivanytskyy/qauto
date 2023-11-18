package com.gmail.ivanytskyy.vitaliy.ui.utils.units;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/11/2023
 */
public enum Alerts {
    ADDED_CAR_ALERT("Car added"),
    EDITED_CAR_ALERT("Car updated"),
    ADDED_EXPENSE_ALERT("Fuel expense added"),
    EDITED_EXPENSE_ALERT("Fuel expense edited");
    private final String alert;
    Alerts(String alert){
        this.alert = alert;
    }
    public String getAlert(){
        return this.alert;
    }
}