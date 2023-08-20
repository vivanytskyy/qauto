package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.CarRequest;
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
    private final String cookie;
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
    public CarResponse createNewCar(CarRequest newCar){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(201));
        return given()
                    .header(new Header("Cookie", cookie))
                    .body(newCar)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(CarResponse.class);
    }
    public CarResponse getCarById(int carId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", carId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                .as(CarResponse.class);
    }
    public CarResponse editCarById(CarRequest newCarData, int carId){
        System.out.println("newCarData " + newCarData);
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", carId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                    .body(newCarData)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(CarResponse.class);
    }
    public DeleteCarResponse deleteCarById(int carId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + CARS.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", carId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .delete()
                .then().log().all()
                    .extract()
                    .as(DeleteCarResponse.class);
    }
}