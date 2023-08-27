package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.ExpenseRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.DeleteExpenseResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.ExpenseResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.ExpensesResponse;
import io.restassured.http.Header;
import static com.gmail.ivanytskyy.vitaliy.api.utils.ControllerNames.EXPENSES;
import static com.gmail.ivanytskyy.vitaliy.api.utils.RestAssuredSpecifications.*;
import static io.restassured.RestAssured.given;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
public class ExpensesController extends BaseController{
    private final String cookie;

    public ExpensesController(String cookie) {
        this.cookie = cookie;
    }
    public ExpensesResponse getAllExpenses(int carId, int page){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + EXPENSES.getPath()),
                getResponseSpecification(200));
        return given()
                    .queryParam("carId", carId)
                    .queryParam("page", page)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(ExpensesResponse.class);
    }
    public ExpenseResponse getExpensesById(int expenseId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + EXPENSES.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", expenseId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public ExpenseResponse createExpense(ExpenseRequest expenseRequest){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + EXPENSES.getPath()),
                getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", cookie))
                    .body(expenseRequest)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public ExpenseResponse editExpenseById(ExpenseRequest expenseRequest, int expenseId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + EXPENSES.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", expenseId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                    .body(expenseRequest)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public DeleteExpenseResponse deleteExpenseById(int expenseId){
        setSpecifications(getRequestSpecification(getApiBaseUrl() + EXPENSES.getPath()),
                getResponseSpecification(200));
        String basePath = String.format("/%s", expenseId);
        return given()
                    .basePath(basePath)
                    .header(new Header("Cookie", cookie))
                .when()
                    .delete()
                .then().log().all()
                    .extract()
                    .as(DeleteExpenseResponse.class);
    }
}