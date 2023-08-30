package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.instructions.InstructionResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.instructions.InstructionsResponse;
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

    public InstructionsController(String cookie){
        super(cookie);
    }
    public InstructionsResponse getAllInstructions(int carBrandId, int carModelId, int page){
        setSpecifications(getRequestSpecification(INSTRUCTIONS.getPath()), getResponseSpecification(200));
        return given()
                    .queryParam("carBrandId", carBrandId)
                    .queryParam("carModelId", carModelId)
                    .queryParam("page", page)
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(InstructionsResponse.class);
    }
    public InstructionResponse getInstructionById(int instructionId){
        setSpecifications(getRequestSpecification(INSTRUCTIONS.getPath()), getResponseSpecification(200));
        String basePath = String.format("/%s", instructionId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(InstructionResponse.class);
    }
}