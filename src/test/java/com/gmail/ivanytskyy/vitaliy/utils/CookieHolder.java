package com.gmail.ivanytskyy.vitaliy.utils;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class CookieHolder {
    private static CookieHolder instance;
    private String cookie;
    private CookieHolder(){}
    public static CookieHolder getInstance(){
        if(instance == null){
            instance = new CookieHolder();
        }
        return instance;
    }
    public void setCookie(String cookie){
        getInstance().cookie = cookie;
    }
    public String getCookie(){
        return getInstance().cookie;
    }
}