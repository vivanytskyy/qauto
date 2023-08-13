package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.exceptions.ApiResponseException;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 11/08/2023
 */
public class UsersController extends BaseController{
    private final String cookie;

    public UsersController(String cookie){
        this.cookie = cookie;
    }

    @SneakyThrows
    public int deleteUser(){
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + USERS.getPath())
                .addHeader("Cookie", cookie)
                .delete()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if(response.code() != 200){
                assert response.body() != null;
                throw new ApiResponseException(response.code(), "Logout failed", response.body().string());
            }
            return response.code();
        }
    }
}
