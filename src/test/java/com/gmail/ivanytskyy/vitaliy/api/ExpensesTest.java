package com.gmail.ivanytskyy.vitaliy.api;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.CarRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.ExpenseRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.CarResponse;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.ExpenseData;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.ExpenseResponse;
import com.gmail.ivanytskyy.vitaliy.api.controllers.CarsController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.ExpensesController;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Random;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
public class ExpensesTest extends BaseTest{

    @Test(description = "Create an expense. Positive case", priority = 10)
    public void createExpenseTest(){
        CarsController carController = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(1, 1000);
        int carBrandId = 1;
        int carModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(carBrandId)
                .carModelId(carModelId)
                .mileage(mileage)
                .build();

        CarResponse carResponse = carController.createNewCar(newCarRequest);

        ExpensesController expensesController = new ExpensesController(cookie);
        //Create new expense
        mileage = random.nextInt(999999);
        int carId = carResponse.getData().getId();
        float liters = random.nextFloat(0.01f, 9999f);
        float totalCost = random.nextFloat(0.01f, 1000000f);
        boolean forceMileage = false;
        String reportedAt = carResponse.getData().getCarCreatedAt().substring(0, 10);
        ExpenseRequest expenseRequest = ExpenseRequest
                .builder()
                .carId(carId)
                .mileage(mileage)
                .liters(liters)
                .totalCost(totalCost)
                .forceMileage(forceMileage)
                .reportedAt(reportedAt)
                .build();
        ExpenseResponse expenseResponse = expensesController.createExpense(expenseRequest);
        Assert.assertEquals(carResponse.getStatus(), "ok", "Status isn't ok");
        ExpenseData expense = expenseResponse.getData();
        Assert.assertNotNull(expense.getId(), "Expense id is null");
        Assert.assertEquals(expense.getCarId(), carId, "Car id is incorrect");
        Assert.assertEquals(expense.getMileage(), mileage, "Mileage is incorrect");
        Assert.assertEquals(expense.getLiters(), liters, "Liters is incorrect");
        Assert.assertEquals(expense.getTotalCost(), totalCost, "Total cost is incorrect");
        Assert.assertEquals(expense.getReportedAt(), reportedAt, "ReportedAt is incorrect");
    }
}