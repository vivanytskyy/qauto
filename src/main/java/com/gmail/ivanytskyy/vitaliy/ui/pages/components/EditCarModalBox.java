package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/09/2023
 */
public class EditCarModalBox extends CarModalBox{
    @FindBy(css = "[for='carCreationDate']")
    private WebElement carCreationDateTitle;
    @FindBy(css = "input[id='carCreationDate']")
    private WebElement carCreationDateInput;
    @FindBy(css = ".form-group button")
    private WebElement carCreationDatePickerButton;
    @FindBy(css = ".modal-footer .btn.btn-outline-danger")
    private WebElement removeCarButton;
    @FindBy(css = "div.modal-content>app-edit-car-modal")
    private WebElement modalBox;
    private final By modalContentLocator = By.cssSelector("div.modal-content");

    public EditCarModalBox(WebElement container){
        super(container);
    }
    public EditCarModalBox setCarCreationDate(Date carCreationDate){
        String dateAsString = new SimpleDateFormat(dateFormat).format(carCreationDate);
        System.out.println("date: " + dateAsString);
        setTextFieldValue(carCreationDateInput, dateAsString);
        return this;
    }
    public EditCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId + 1);
        return this;
    }
    public EditCarModalBox selectModelById(int modelId){
        selectByIndex(modelSelect, modelId + 1);
        return this;
    }
    public EditCarModalBox selectBrandByName(String brandName){
        selectByText(brandSelect, brandName);
        return this;
    }
    public EditCarModalBox selectModelByName(String modelName){
        selectByText(modelSelect, modelName);
        return this;
    }
    public EditCarModalBox setMileage(int mileage){
        setTextFieldValue(mileageInput, String.valueOf(mileage));
        return this;
    }
    public GaragePage clickSaveCarButtonPositiveCase(){
        clickButton(saveButton);
        wait.until(ExpectedConditions.invisibilityOf(modalBox));
        return new GaragePage();
    }
    public GaragePage saveCarPositiveCase(int brandId, int modelId, int mileage, Date carCreationDate){
        return selectBrandById(brandId)
                .selectModelById(modelId)
                .setMileage(mileage)
                .setCarCreationDate(carCreationDate)
                .clickSaveCarButtonPositiveCase();
    }
    public GaragePage saveCarPositiveCase(String brandName, String modelName, int mileage, Date carCreationDate){
        return selectBrandByName(brandName)
                .selectModelByName(modelName)
                .setMileage(mileage)
                .setCarCreationDate(carCreationDate)
                .clickSaveCarButtonPositiveCase();
    }
    public RemoveCarConfirmationModalBox removeCar(){
        clickButton(removeCarButton);
        wait.until(ExpectedConditions.invisibilityOf(modalBox));
        return new RemoveCarConfirmationModalBox(webDriver.findElement(modalContentLocator));
    }
    public String getCarCreationDateTitle(){
        return getText(carCreationDateTitle);
    }
}