package com.gmail.ivanytskyy.vitaliy.api.pojos.antities;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
public enum Currency {
    EUR("eur"), GBR("gbr"), USD("usd"), UAH("uah"), PLN("pln");
    private final String value;
    Currency(String value){
       this.value = value;
    }
    public String getValue(){
        return value;
    }
}