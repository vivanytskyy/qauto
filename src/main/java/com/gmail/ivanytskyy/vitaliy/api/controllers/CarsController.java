package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.*;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.*;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;
import static io.restassured.RestAssured.given;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 19/08/2023
 */
public class CarsController extends BaseController{
    private String cookie;
    public CarsController(String cookie){
        this.cookie = cookie;
    }
    public CarBrandsResponse getCarBrands(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/brands")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarBrandsResponse.class);
    }
    public CarBrandResponse getCarBrandById(int brandId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/brands/%s", brandId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarBrandResponse.class);
    }
    public CarModelsResponse getCarModels(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        return given()
                    .basePath("/models")
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarModelsResponse.class);
    }
    public CarModelResponse getCarModelById(int modelId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/models/%s", modelId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarModelResponse.class);
    }
    public CarsResponse getCurrentUserCars(){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarsResponse.class);
    }
}
