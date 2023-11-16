package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.StringConstants.DATE_FORMAT;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 15/11/2023
 */
public class EditExpenseModalBox extends ExpenseModalBox{
    @FindBy(css = ".modal-footer .btn.btn-primary")
    protected WebElement saveButton;

    public EditExpenseModalBox selectVehicleById(int numberOfVehicle){
        int vehicleIndex = numberOfVehicle - 1;
        selectByIndex(vehicleSelect, vehicleIndex);
        return this;
    }
    public EditExpenseModalBox selectVehicleByName(String brand, String model){
        String vehicle = brand + " " + model;
        selectByText(vehicleSelect, vehicle);
        return this;
    }
    public EditExpenseModalBox setMileage(int mileage){
        setTextFieldValue(mileageInput, String.valueOf(mileage));
        return this;
    }
    public EditExpenseModalBox setReportDate(Date reportDate){
        String dateAsString = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        setTextFieldValue(reportCreationDateInput, dateAsString);
        return this;
    }
    public EditExpenseModalBox setNumberOfLiters(float numberOfLiters){
        setTextFieldValue(numberOfLitersInput, String.valueOf(numberOfLiters));
        return this;
    }
    public EditExpenseModalBox setTotalCost(float totalCost){
        setTextFieldValue(totalCostInput, String.valueOf(totalCost));
        return this;
    }
    public void clickSaveExpenseButtonPositiveCase(){
        clickButton(saveButton);
        wait.until(driver -> {
            if(driver.findElements(alertExistLocator).size() != 0){
                return !driver.findElement(alertExistLocator).getText().contains(editedExpenseAlert);
            }
            return true;
        });
    }
    public EditExpenseModalBox clickSaveExpenseButtonNegativeCase(){
        clickButton(saveButton);
        return this;
    }
    public void saveExpensePositiveCase(
            String brand, String model, Date reportDate, int mileage, float numberOfLiters, float totalCost){
        selectVehicleByName(brand, model)
                .setReportDate(reportDate)
                .setMileage(mileage)
                .setNumberOfLiters(numberOfLiters)
                .setTotalCost(totalCost)
                .clickSaveExpenseButtonPositiveCase();
    }
    public void saveExpensePositiveCase(
            int vehicleIndex, Date reportDate, int mileage, float numberOfLiters, float totalCost){
        selectVehicleById(vehicleIndex)
                .setReportDate(reportDate)
                .setMileage(mileage)
                .setNumberOfLiters(numberOfLiters)
                .setTotalCost(totalCost)
                .clickSaveExpenseButtonPositiveCase();
    }
}