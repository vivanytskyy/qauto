package com.gmail.ivanytskyy.vitaliy.api;

import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.CarRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.ExpenseRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.*;
import com.gmail.ivanytskyy.vitaliy.api.controllers.CarsController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.ExpensesController;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.function.Function;
import static com.gmail.ivanytskyy.vitaliy.api.antities.ExpensesValues.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 24/08/2023
 */
public class ExpensesTest extends BaseTest{
    private static final DecimalFormat DF;
    private static final Function<Float, Float> TO_FORMAT;
    static {
        DF = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        TO_FORMAT = n -> Float.parseFloat(DF.format(n));
    }

    @Test(description = "Create an expense. Positive case", priority = 10)
    public void createExpenseTest(){
        CarsController carController = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(MIN_MILEAGE_VALUE, MAX_MILEAGE_VALUE);
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
        mileage = random.nextInt(mileage + 1, MAX_MILEAGE_VALUE);
        int carId = carResponse.getData().getId();
        float liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
        float totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
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
    //"Bug added to Jira (Edit Issue : U1QM241022-35)
    @Test(description = "Get all the expenses. Positive case.", priority = 20)
    public void getAllExpensesTest(){
        int numberOfExpenses = 5;
        List<ExpenseData> expenses = new ArrayList<>();
        CarsController carController = new CarsController(cookie);
        ExpensesController expensesController = new ExpensesController(cookie);
        Random random = new Random();
        int mileage = random.nextInt(MAX_MILEAGE_VALUE);
        int carBrandId = 1;
        int carModelId = 1;
        CarRequest newCarRequest = CarRequest
                .builder()
                .carBrandId(carBrandId)
                .carModelId(carModelId)
                .mileage(mileage)
                .build();
        CarResponse carResponse = carController.createNewCar(newCarRequest);
        CarData carData = carResponse.getData();
        for(int i = 0; i < numberOfExpenses; i++){
            mileage = random.nextInt(carData.getMileage() + 1, MAX_MILEAGE_VALUE);
            int carId = carResponse.getData().getId();
            float liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
            float totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
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
            expenses.add(expenseResponse.getData());
        }
        ExpensesResponse expensesResponse = expensesController
                .getAllExpenses(carResponse.getData().getId(), 1);
        Assert.assertEquals(expensesResponse.getStatus(), "ok", "Status isn't ok");
        List<ExpenseData> resultExpenses = expensesResponse.getData();
        expenses.sort(Comparator.comparing(ExpenseData::getId));
        resultExpenses.sort(Comparator.comparing(ExpenseData::getId));
        Assert.assertEquals(resultExpenses.size(), expenses.size(), "Number of the expenses is wrong");
        for (int i = 0; i < resultExpenses.size(); i++){
            ExpenseData expectedExpense = expenses.get(i);
            ExpenseData resultExpense = resultExpenses.get(i);
            Assert.assertEquals(resultExpense.getId(), expectedExpense.getId(), "Expense ids isn't equal");
            Assert.assertEquals(resultExpense.getCarId(), expectedExpense.getCarId(), "Car id is incorrect");
            Assert.assertEquals(resultExpense.getReportedAt(), expectedExpense.getReportedAt(),
                    "ReportedAt is incorrect");
            Assert.assertEquals(resultExpense.getMileage(), expectedExpense.getMileage(), "Mileage is incorrect");
            Assert.assertEquals(resultExpense.getLiters(), expectedExpense.getLiters(), "Liters is incorrect");
        }
        // This assertion will have checked separately by the moment this bug will be corrected
        for (int i = 0; i < resultExpenses.size(); i++){
            ExpenseData expectedExpense = expenses.get(i);
            ExpenseData resultExpense = resultExpenses.get(i);
            Assert.assertEquals(resultExpense.getTotalCost(), expectedExpense.getTotalCost(),
                    "Total cost is incorrect. Bug added to Jira (Edit Issue : U1QM241022-35)");
        }
    }
    @Test(description = "Edit an expense by id. Positive case", priority = 30)
    public void editExpenseByIdTest(){
        CarsController carController = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(MIN_MILEAGE_VALUE, MAX_MILEAGE_VALUE);
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
        mileage = random.nextInt(mileage + 1, MAX_MILEAGE_VALUE);
        int carId = carResponse.getData().getId();
        float liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
        float totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
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
        ExpenseResponse createdExpenseResponse = expensesController.createExpense(expenseRequest);
        //Update an expense
        mileage = random.nextInt(mileage + 1, MAX_MILEAGE_VALUE);
        carId = carResponse.getData().getId();
        liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
        totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
        reportedAt = carResponse.getData().getCarCreatedAt().substring(0, 10);
        ExpenseRequest updatedExpenseRequest = ExpenseRequest
                .builder()
                .carId(carId)
                .mileage(mileage)
                .liters(liters)
                .totalCost(totalCost)
                .forceMileage(forceMileage)
                .reportedAt(reportedAt)
                .build();
        ExpenseResponse updatedExpenseResponse = expensesController
                .editExpenseById(updatedExpenseRequest, createdExpenseResponse.getData().getId());
        Assert.assertEquals(updatedExpenseResponse.getData().getId(), createdExpenseResponse.getData().getId(),
                "Expense ids isn't equals");
        Assert.assertNotEquals(updatedExpenseResponse, createdExpenseResponse, "Expense wasn't updated");
        Assert.assertEquals(carResponse.getStatus(), "ok", "Status isn't ok");
        ExpenseData updatedExpense = updatedExpenseResponse.getData();
        Assert.assertNotNull(updatedExpense.getId(), "Expense id is null");
        Assert.assertEquals(updatedExpense.getCarId(), carId, "Car id is incorrect");
        Assert.assertEquals(updatedExpense.getMileage(), mileage, "Mileage is incorrect");
        Assert.assertEquals(updatedExpense.getLiters(), liters, "Liters is incorrect");
        Assert.assertEquals(updatedExpense.getTotalCost(), totalCost, "Total cost is incorrect");
        Assert.assertEquals(updatedExpense.getReportedAt(), reportedAt, "ReportedAt is incorrect");
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-36)
    @Test(description = "Get an expense by id. Positive case", priority = 40)
    public void getExpenseByIdTest(){
        CarsController carController = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(MIN_MILEAGE_VALUE, MAX_MILEAGE_VALUE);
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
        mileage = random.nextInt(mileage + 1, MAX_MILEAGE_VALUE);
        int carId = carResponse.getData().getId();
        float liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
        float totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
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
        ExpenseResponse expectedExpenseResponse = expensesController.createExpense(expenseRequest);
        ExpenseData expectedExpense = expectedExpenseResponse.getData();
        ExpenseResponse resultExpenseResponse = expensesController.getExpensesById(expectedExpense.getId());
        Assert.assertEquals(resultExpenseResponse.getStatus(), "ok", "Status isn't ok");
        ExpenseData resultExpense = resultExpenseResponse.getData();
        Assert.assertEquals(resultExpense.getId(), expectedExpense.getId(), "Expense id is incorrect");
        Assert.assertEquals(resultExpense.getCarId(), expectedExpense.getCarId(), "Car id is incorrect");
        Assert.assertEquals(resultExpense.getMileage(), expectedExpense.getMileage(), "Mileage is incorrect");
        Assert.assertEquals(resultExpense.getLiters(), expectedExpense.getLiters(), "Liters is incorrect");
        Assert.assertEquals(resultExpense.getReportedAt(), expectedExpense.getReportedAt(),
                "ReportedAt is incorrect");
        Assert.assertEquals(resultExpense.getTotalCost(), expectedExpense.getTotalCost(),
                "Total cost is incorrect. Bug added to Jira (Edit Issue : U1QM241022-36)");
    }
    @Test(description = "Delete an expense by id. Positive case", priority = 50)
    public void deleteExpenseByIdTest(){
        CarsController carController = new CarsController(cookie);
        //Create new car for current user
        Random random = new Random();
        int mileage = random.nextInt(MIN_MILEAGE_VALUE, MAX_MILEAGE_VALUE);
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
        mileage = random.nextInt(mileage + 1, MAX_MILEAGE_VALUE);
        int carId = carResponse.getData().getId();
        float liters = TO_FORMAT.apply(random.nextFloat(MIN_LITERS_VALUE, MAX_LITERS_VALUE));
        float totalCost = TO_FORMAT.apply(random.nextFloat(MIN_TOTAL_COST_VALUE, MAX_TOTAL_COST_VALUE));
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
        ExpenseResponse createdExpenseResponse = expensesController.createExpense(expenseRequest);
        ExpenseData createdExpense = createdExpenseResponse.getData();
        // Delete an expense
        DeleteExpenseResponse deleteExpenseResponse = expensesController.deleteExpenseById(createdExpense.getId());
        Assert.assertEquals(deleteExpenseResponse.getStatus(), "ok", "Status isn't ok");
        DeletedExpenseData deletedExpenseData = deleteExpenseResponse.getData();
        Assert.assertEquals(deletedExpenseData.getExpenseId(), createdExpense.getId(), "Expense wasn't deleted");
    }
}