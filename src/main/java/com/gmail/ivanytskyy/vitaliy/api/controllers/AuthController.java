package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.User;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.AuthorizationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.RegistrationUserCredentialsWrapper;
import com.gmail.ivanytskyy.vitaliy.api.exceptions.ApiResponseException;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class AuthController extends BaseController{

    @SneakyThrows
    public User signUp(RegistrationUserCredentialsWrapper credentials){
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(credentials), mediaType);
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + AUTH.getPath() + "/signup")
                .post(requestBody)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            if(response.code() != 201){
                throw new ApiResponseException(response.code(), "Registration failed", response.body().string());
            }
            return gson.fromJson(response.body().string(), User.class);
        }
    }
    @SneakyThrows
    public User signIn(AuthorizationUserCredentialsWrapper credentials){
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(credentials), mediaType);
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + AUTH.getPath() + "/signin")
                .post(requestBody)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            if(response.code() != 200){
                throw new ApiResponseException(response.code(), "Authorization failed", response.body().string());
            }
            return gson.fromJson(response.body().string(), User.class);
        }
    }
    @SneakyThrows
    public int logout(String cookie){
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + AUTH.getPath() + "/logout")
                .addHeader("Cookie", cookie)
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if(response.code() != 200){
                assert response.body() != null;
                throw new ApiResponseException(response.code(), "Logout failed", response.body().string());
            }
            return response.code();
        }
    }
    @SneakyThrows
    public int resetPassword(String email, String cookie){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + AUTH.getPath() + "/resetPassword")
                .addHeader("Cookie", cookie)
                .post(requestBody)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if(response.code() != 200){
                assert response.body() != null;
                throw new ApiResponseException(response.code(), "Password reset failed", response.body().string());
            }
            return response.code();
        }
    }
    @SneakyThrows
    public String getCookie(AuthorizationUserCredentialsWrapper credentials){
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(credentials), mediaType);
        Request request = new Request.Builder()
                .url(getApiBaseUrl() + AUTH.getPath() + "/signin")
                .post(requestBody)
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.header("Set-Cookie");
        }
    }
}