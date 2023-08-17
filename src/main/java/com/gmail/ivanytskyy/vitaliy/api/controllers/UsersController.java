package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.request.UserSettingsRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.UserProfileRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserDataResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserProfileResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserSettingsResponse;
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

    public UserDataResponse getCurrentUserData(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/current")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public UserProfileResponse getUserProfileData(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/profile")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(UserProfileResponse.class);
    }
    public UserProfileResponse editUserProfile(UserProfileRequest newProfileData){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/profile")
                    .header(new Header("Cookie", cookie))
                    .body(newProfileData)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(UserProfileResponse.class);
    }
    public UserSettingsResponse getUserSettings(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/settings")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(UserSettingsResponse.class);
    }
    public UserSettingsResponse editUserSettings(UserSettingsRequest newSettings){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/settings")
                    .header(new Header("Cookie", cookie))
                    .body(newSettings)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(UserSettingsResponse.class);
    }
    public StatusResponseSuccess deleteUser(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + USERS.getPath()),
                getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", cookie))
                .when()
                    .delete()
                .then()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
}