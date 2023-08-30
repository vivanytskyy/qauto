package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.cars.CarRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.cars.*;
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

    public CarsController(String cookie) {
        super(cookie);
    }
    public CarBrandsResponse getCarBrands(){
        String basePath = CARS.getPath() + "/brands";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarBrandsResponse.class);
    }
    public CarBrandResponse getCarBrandById(int brandId){
        String basePath = CARS.getPath() + String.format("/brands/%s", brandId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarBrandResponse.class);
    }
    public CarModelsResponse getCarModels(){
        String basePath = CARS.getPath() + "/models";
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarModelsResponse.class);
    }
    public CarModelResponse getCarModelById(int modelId){
        String basePath = CARS.getPath() + String.format("/models/%s", modelId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarModelResponse.class);
    }
    public CarsResponse getCurrentUserCars(){
        setSpecifications(getRequestSpecification(CARS.getPath()), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(CarsResponse.class);
    }
    public CarResponse createNewCar(CarRequest newCar){
        setSpecifications(getRequestSpecification(CARS.getPath()), getResponseSpecification(201));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newCar)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(CarResponse.class);
    }
    public CarResponse getCarById(int carId){
        String basePath = CARS.getPath() + String.format("/%s", carId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                .as(CarResponse.class);
    }
    public CarResponse editCarById(CarRequest newCarData, int carId){
        String basePath = CARS.getPath() + String.format("/%s", carId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(newCarData)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(CarResponse.class);
    }
    public DeleteCarResponse deleteCarById(int carId){
        String basePath = CARS.getPath() + String.format("/%s", carId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .delete()
                .then().log().all()
                    .extract()
                    .as(DeleteCarResponse.class);
    }
}