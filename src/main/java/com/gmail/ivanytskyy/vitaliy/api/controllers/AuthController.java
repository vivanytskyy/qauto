package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.UserData;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.AuthorizationUserCredentials;
import com.gmail.ivanytskyy.vitaliy.api.antities.auth.RegistrationUserCredentials;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import lombok.SneakyThrows;
import org.json.JSONObject;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 08/08/2023
 */
public class AuthController extends BaseController{

    public UserData signUp(RegistrationUserCredentials credentials){
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .body(credentials)
                    .post(getApiBaseUrl() + AUTH.getPath() + "/signup")
                .then()
                    .extract()
                    .body()
                    .jsonPath()
                    .getObject(".", UserData.class);
    }
    public UserData signIn(AuthorizationUserCredentials credentials){
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .body(credentials)
                    .post(getApiBaseUrl() + AUTH.getPath() + "/signin")
                .then()
                    .extract()
                    .body()
                    .jsonPath()
                    .getObject(".", UserData.class);
    }
    public int logout(String cookie){
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .header(new Header("Cookie", cookie))
                    .get(getApiBaseUrl() + AUTH.getPath() + "/logout")
                .then()
                    .extract()
                    .statusCode();
    }
    @SneakyThrows
    public int resetPassword(String email, String cookie){
        JSONObject jsonObject = new JSONObject().put("email", email);
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .header(new Header("Cookie", cookie))
                    .body(jsonObject.toString())
                    .post(getApiBaseUrl() + AUTH.getPath() + "/resetPassword")
                .then()
                    .extract()
                    .statusCode();

    }
    public String getCookie(AuthorizationUserCredentials credentials){
        return given()
                .when()
                    .contentType(ContentType.JSON)
                    .body(credentials)
                    .post(getApiBaseUrl() + AUTH.getPath() + "/signin")
                .then()
                    .extract()
                    .headers()
                    .getValue("Set-Cookie");
    }
}