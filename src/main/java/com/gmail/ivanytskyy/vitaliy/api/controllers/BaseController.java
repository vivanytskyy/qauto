package com.gmail.ivanytskyy.vitaliy.api.controllers;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public abstract class BaseController {
    private final String cookie;

    public BaseController(String cookie){
        this.cookie = cookie;
    }
    protected String getCookie(){
        return cookie;
    }
}