package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.response.ResponseStatusSuccess;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;

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

    public ResponseStatusSuccess deleteUser(){
        setSpecifications(getRequestSpecification(getApiBaseUrl()), getResponseSpecification(200));
        return given()
                .when()
                    .header(new Header("Cookie", cookie))
                    .delete(USERS.getPath())
                .then()
                    .extract()
                    .as(ResponseStatusSuccess.class);
    }
}