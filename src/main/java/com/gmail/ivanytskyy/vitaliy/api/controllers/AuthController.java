package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.request.ResetPassword;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.ResponseStatusSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserData;
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

    public UserData signUp(RegistrationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl()));
        return given()
                .when()
                    .body(credentials)
                    .post(AUTH.getPath() + "/signup")
                .then()
                    .extract()
                    .as(UserData.class);
    }
    public UserData signIn(AuthorizationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl()));
        return given()
                .when()
                    .body(credentials)
                    .post(AUTH.getPath() + "/signin")
                .then()
                    .extract()
                    .as(UserData.class);
    }
    public ResponseStatusSuccess logout(String cookie){
        setSpecifications(getRequestSpecification(getApiBaseUrl()), getResponseSpecification(200));
        return given()
                .when()
                    .header(new Header("Cookie", cookie))
                    .get(AUTH.getPath() + "/logout")
                .then()
                    .extract()
                    .as(ResponseStatusSuccess.class);
    }
    public ResponseStatusSuccess resetPassword(ResetPassword resetPassword, String cookie){
        setSpecifications(getRequestSpecification(getApiBaseUrl()), getResponseSpecification(200));
        return given()
                .when()
                    .header(new Header("Cookie", cookie))
                    .body(resetPassword)
                    .post(AUTH.getPath() + "/resetPassword")
                .then()
                    .extract()
                    .as(ResponseStatusSuccess.class);
    }
    public String getCookie(AuthorizationUserCredentials credentials){
        setSpecifications(getRequestSpecification(getApiBaseUrl()));
        return given()
                .when()
                    .body(credentials)
                    .post(AUTH.getPath() + "/signin")
                .then()
                    .extract()
                    .headers()
                    .getValue("Set-Cookie");
    }
}