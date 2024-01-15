package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 15/11/2023
 */
public abstract class ExpenseModalBox extends ModalBox {
    @FindBy(css = "[for='addExpenseCar']")
    private WebElement vehicleTitle;
    @FindBy(css = "[for='addExpenseDate']")
    private WebElement reportDateTitle;
    @FindBy(css = "[for='addExpenseMileage']")
    private WebElement mileageTitle;
    @FindBy(css = "[for='addExpenseLiters']")
    private WebElement numberOfLitersTitle;
    @FindBy(css = "[for='addExpenseTotalCost']")
    private WebElement totalCostTitle;
    @FindBy(css = "select[id='addExpenseCar']")
    protected WebElement vehicleSelect;
    @FindBy(css = "input[id='addExpenseDate']")
    protected WebElement reportCreationDateInput;
    @FindBy(css = ".form-group button")
    private WebElement reportCreationDatePickerButton;
    @FindBy(css = "input[id='addExpenseMileage']")
    protected WebElement mileageInput;
    @FindBy(css = "input[id='addExpenseLiters']")
    protected WebElement numberOfLitersInput;
    @FindBy(css = "input[id='addExpenseTotalCost']")
    protected WebElement totalCostInput;
    @FindBy(css = ".form-group:nth-child(3) div.input-group-text")
    private WebElement mileageUnit;
    @FindBy(css = ".form-group:nth-child(4) div.input-group-text")
    private WebElement litersUnit;
    @FindBy(css = ".form-group:nth-child(5) div.input-group-text")
    private WebElement costUnit;
    @FindBy(css = ".modal-footer .btn.btn-primary")
    protected WebElement addButton;
    @FindBy(css = ".modal-footer .btn.btn-secondary")
    private WebElement cancelButton;

    public void closeModalBox(){
        clickElement(closeButton);
    }
    public void clickCancelButton(){
        clickElement(cancelButton);
    }
    public String getVehicleSelectTitle(){
        return getText(vehicleTitle);
    }
    public String getReportCreationDateInputTitle(){
        return getText(reportDateTitle);
    }
    public String getMileageInputFieldTitle(){
        return getText(mileageTitle);
    }
    public String getNumberOfLitersInputFieldTitle(){
        return getText(numberOfLitersTitle);
    }
    public String getTotalCostInputFieldTitle(){
        return getText(totalCostTitle);
    }
    public String getMileageUnit(){
        return getText(mileageUnit);
    }
    public String getNumberOfLitersUnit(){
        return getText(litersUnit);
    }
    public String getTotalCostUnit(){
        return getText(costUnit);
    }
}