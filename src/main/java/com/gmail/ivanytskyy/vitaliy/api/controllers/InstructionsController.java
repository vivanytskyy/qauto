package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.InstructionResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.InstructionsResponse;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static io.restassured.RestAssured.given;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 28/08/2023
 */
public class InstructionsController extends BaseController{
    private final String cookie;

    public InstructionsController(String cookie){
        this.cookie = cookie;
    }
    public InstructionsResponse getAllInstructions(int carBrandId, int carModelId, int page){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + INSTRUCTIONS.getPath()),
                getResponseSpecification(200));
        return given()
                    .queryParam("carBrandId", carBrandId)
                    .queryParam("carModelId", carModelId)
                    .queryParam("page", page)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(InstructionsResponse.class);
    }
    public InstructionResponse getInstructionById(int instructionId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + INSTRUCTIONS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", instructionId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(InstructionResponse.class);
    }
}