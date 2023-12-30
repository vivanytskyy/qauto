package com.gmail.ivanytskyy.vitaliy.api.services;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class APICookieHolder {
    private static APICookieHolder instance;
    private String cookie;
    private APICookieHolder(){}
    public static APICookieHolder getInstance(){
        if(instance == null){
            instance = new APICookieHolder();
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