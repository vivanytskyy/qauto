package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.StringConstants.DATE_FORMAT;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 17/11/2023
 */
public class AddExpenseModalBox extends ExpenseModalBox{
    @FindBy(css = ".modal-footer .btn.btn-primary")
    protected WebElement addButton;

    public AddExpenseModalBox selectVehicleById(int numberOfVehicle){
        int vehicleIndex = numberOfVehicle - 1;
        selectByIndex(vehicleSelect, vehicleIndex);
        return this;
    }
    public AddExpenseModalBox selectVehicleByName(String brand, String model){
        String vehicle = brand + " " + model;
        selectByText(vehicleSelect, vehicle);
        return this;
    }
    public AddExpenseModalBox setMileage(int mileage){
        setTextFieldValue(mileageInput, String.valueOf(mileage));
        return this;
    }
    public AddExpenseModalBox setReportDate(Date reportDate){
        String dateAsString = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        setTextFieldValue(reportCreationDateInput, dateAsString);
        return this;
    }
    public AddExpenseModalBox setNumberOfLiters(float numberOfLiters){
        setTextFieldValue(numberOfLitersInput, String.valueOf(numberOfLiters));
        return this;
    }
    public AddExpenseModalBox setTotalCost(float totalCost){
        setTextFieldValue(totalCostInput, String.valueOf(totalCost));
        return this;
    }
    public void clickAddExpenseButtonPositiveCase(){
        clickButton(addButton);
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
    }
    public AddExpenseModalBox clickAddExpenseButtonNegativeCase(){
        clickButton(addButton);
        return this;
    }
    public void addExpensePositiveCase(
            String brand, String model, Date reportDate, int mileage, float numberOfLiters, float totalCost){
        selectVehicleByName(brand, model)
                .setReportDate(reportDate)
                .setMileage(mileage)
                .setNumberOfLiters(numberOfLiters)
                .setTotalCost(totalCost)
                .clickAddExpenseButtonPositiveCase();
    }
    public void addExpensePositiveCase(
            int vehicleIndex, Date reportDate, int mileage, float numberOfLiters, float totalCost){
        selectVehicleById(vehicleIndex)
                .setReportDate(reportDate)
                .setMileage(mileage)
                .setNumberOfLiters(numberOfLiters)
                .setTotalCost(totalCost)
                .clickAddExpenseButtonPositiveCase();
    }
}