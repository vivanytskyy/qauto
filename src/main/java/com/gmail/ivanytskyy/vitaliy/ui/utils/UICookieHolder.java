package com.gmail.ivanytskyy.vitaliy.ui.utils;

import org.openqa.selenium.Cookie;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 27/12/2023
 */
public final class UICookieHolder {
    private static final ThreadLocal<Cookie> COOKIES_HOLDER = new ThreadLocal<>();

    private UICookieHolder(){}
    public static Cookie getCookie(){
        return COOKIES_HOLDER.get();
    }
    public static void setCookie(Cookie cookie){
        COOKIES_HOLDER.set(cookie);
    }
}