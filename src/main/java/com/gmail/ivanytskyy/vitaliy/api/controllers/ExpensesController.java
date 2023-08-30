package com.gmail.ivanytskyy.vitaliy.api.controllers;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.expenses.ExpenseRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.expenses.DeleteExpenseResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.expenses.ExpenseResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.expenses.ExpensesResponse;
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

    public ExpensesController(String cookie) {
        super(cookie);
    }
    public ExpensesResponse getAllExpenses(int carId, int page){
        setSpecifications(getRequestSpecification(EXPENSES.getPath()), getResponseSpecification(200));
        return given()
                    .queryParam("carId", carId)
                    .queryParam("page", page)
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(ExpensesResponse.class);
    }
    public ExpenseResponse getExpensesById(int expenseId){
        String basePath = EXPENSES.getPath() + String.format("/%s", expenseId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .get()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public ExpenseResponse createExpense(ExpenseRequest expenseRequest){
        setSpecifications(getRequestSpecification(EXPENSES.getPath()), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(expenseRequest)
                .when()
                    .post()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public ExpenseResponse editExpenseById(ExpenseRequest expenseRequest, int expenseId){
        String basePath = EXPENSES.getPath() + String.format("/%s", expenseId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                    .body(expenseRequest)
                .when()
                    .put()
                .then().log().all()
                    .extract()
                    .as(ExpenseResponse.class);
    }
    public DeleteExpenseResponse deleteExpenseById(int expenseId){
        String basePath = EXPENSES.getPath() + String.format("/%s", expenseId);
        setSpecifications(getRequestSpecification(basePath), getResponseSpecification(200));
        return given()
                    .header(new Header("Cookie", getCookie()))
                .when()
                    .delete()
                .then().log().all()
                    .extract()
                    .as(DeleteExpenseResponse.class);
    }
}