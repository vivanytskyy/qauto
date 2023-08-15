package com.gmail.ivanytskyy.vitaliy.api.controllers;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;

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

    public int deleteUser(){
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .header(new Header("Cookie", cookie))
                    .delete(getApiBaseUrl() + USERS.getPath())
                .then()
                    .extract()
                    .statusCode();
    }
}