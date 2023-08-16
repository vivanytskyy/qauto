package com.gmail.ivanytskyy.vitaliy.api.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/08/2023
 */
public class RestAssuredSpecifications {
    public static RequestSpecification getRequestSpecification(String baseUrl){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static ResponseSpecification getResponseSpecification(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }
    public static void setSpecifications(RequestSpecification requestSpecification,
                                         ResponseSpecification responseSpecification){
        RestAssured.reset();
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
    public static void setSpecifications(RequestSpecification requestSpecification){
        RestAssured.reset();
        RestAssured.requestSpecification = requestSpecification;
    }
}