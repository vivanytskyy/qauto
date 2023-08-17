package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.request.ResetPasswordRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserDataResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.auth.RegistrationUserCredentials;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class AuthController extends BaseController{

    public UserDataResponse signUp(RegistrationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + AUTH.getPath()));
        return given()
                    .basePath("/signup")
                    .body(credentials)
                .when()
                    .post()
                .then()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public UserDataResponse signIn(AuthorizationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + AUTH.getPath()));
        return given()
                    .basePath("/signin")
                    .body(credentials)
                .when()
                    .post()
                .then()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public StatusResponseSuccess logout(String cookie){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + AUTH.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/logout")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
    public StatusResponseSuccess resetPassword(ResetPasswordRequest resetPassword, String cookie){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + AUTH.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/resetPassword")
                    .header(new Header("Cookie", cookie))
                    .body(resetPassword)
                .when()
                    .post()
                .then()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
    public String getCookie(AuthorizationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + AUTH.getPath()));
        return given()
                    .basePath("/signin")
                    .body(credentials)
                .when()
                    .post()
                .then()
                    .extract()
                    .headers()
                    .getValue("Set-Cookie");
    }
}