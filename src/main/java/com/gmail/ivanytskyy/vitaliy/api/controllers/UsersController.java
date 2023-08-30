package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.ChangeEmailRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.UserSettingsRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.UserProfileRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.ChangePasswordRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.common.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users.*;
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

    public UsersController(String cookie){
        super(cookie);
    }

    public UserDataResponse getCurrentUserData(){
        String basePath = USERS.getPath() + "/current";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(UserDataResponse.class);
    }
    public UserProfileResponse getUserProfileData(){
        String basePath = USERS.getPath() + "/profile";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(UserProfileResponse.class);
    }
    public UserProfileResponse editUserProfile(UserProfileRequest newProfileData){
        String basePath = USERS.getPath() + "/profile";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newProfileData)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(UserProfileResponse.class);
    }
    public UserSettingsResponse getUserSettings(){
        String basePath = USERS.getPath() + "/settings";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(UserSettingsResponse.class);
    }
    public UserSettingsResponse editUserSettings(UserSettingsRequest newSettings){
        String basePath = USERS.getPath() + "/settings";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newSettings)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(UserSettingsResponse.class);
    }
    public ChangeEmailResponse changeUserEmail(ChangeEmailRequest newEmail){
        String basePath = USERS.getPath() + "/email";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newEmail)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(ChangeEmailResponse.class);
    }
    public ChangePasswordResponse changeUserPassword(ChangePasswordRequest newPassword){
        String basePath = USERS.getPath() + "/password";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newPassword)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(ChangePasswordResponse.class);
    }
    public StatusResponseSuccess resetUserPassword(int userId, String token){
        String basePath = USERS.getPath() + String.format("/resetPassword/%s/%s", userId, token);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
    public StatusResponseSuccess deleteUser(){
        setSpecifications(getRequestSpecification(USERS.getPath()), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .delete()
                .then().log().all()
                    .extract()
                    .as(StatusResponseSuccess.class);
    }
}