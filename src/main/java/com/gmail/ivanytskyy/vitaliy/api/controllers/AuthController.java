package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.ResetPasswordRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.common.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users.UserDataResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.auth.RegistrationUserCredentials;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class AuthController{

    public UserDataResponse signUp(RegistrationUserCredentials credentials){
        String basePath = AUTH.getPath() + "/signup";
        setSpecifications(getRequestSpecification(basePath));
        return given()
                    .body(credentials)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public UserDataResponse signIn(AuthorizationUserCredentials credentials){
        String basePath = AUTH.getPath() + "/signin";
        setSpecifications(getRequestSpecification(basePath));
        return given()
                    .body(credentials)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public StatusResponseSuccess logout(String cookie){
        String basePath = AUTH.getPath() + "/logout";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
    public StatusResponseSuccess resetPassword(ResetPasswordRequest resetPassword, String cookie){
        String basePath = AUTH.getPath() + "/resetPassword";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", cookie))
                    .body(resetPassword)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
    public String getCookie(AuthorizationUserCredentials credentials){
        String basePath = AUTH.getPath() + "/signin";
        setSpecifications(getRequestSpecification(basePath));
        return given()
                    .body(credentials)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .headers()
                    .getValue("Set-Cookie");
    }
}