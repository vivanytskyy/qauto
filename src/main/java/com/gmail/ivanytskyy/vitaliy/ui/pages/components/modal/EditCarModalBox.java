package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.StringConstants.*;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.Alerts.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 17/11/2023
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

    public EditCarModalBox setCarCreationDate(Date carCreationDate){
        String dateAsString = new SimpleDateFormat(DATE_FORMAT.getValue()).format(carCreationDate);
        setTextFieldValue(carCreationDateInput, dateAsString);
        return this;
    }
    public EditCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId);
        return this;
    }
    public EditCarModalBox selectModelById(int modelId){
        selectByIndex(modelSelect, modelId);
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
    public void clickSaveCarButtonPositiveCase(){
        clickButton(saveButton);
        wait.until(driver -> {
            if(driver.findElements(alertExistLocator).size() != 0){
                return !driver.findElement(alertExistLocator).getText().contains(EDITED_CAR_ALERT.getAlert());
            }
            return true;
        });
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
    }
    public void saveCarPositiveCase(int brandId, int modelId, int mileage, Date carCreationDate){
        selectBrandById(brandId)
                .selectModelById(modelId)
                .setMileage(mileage)
                .setCarCreationDate(carCreationDate)
                .clickSaveCarButtonPositiveCase();
    }
    public void saveCarPositiveCase(String brandName, String modelName, int mileage, Date carCreationDate){
        selectBrandByName(brandName)
                .selectModelByName(modelName)
                .setMileage(mileage)
                .setCarCreationDate(carCreationDate)
                .clickSaveCarButtonPositiveCase();
    }
    public RemoveCarConfirmationModalBox removeCar(){
        clickButton(removeCarButton);
        wait.until(ExpectedConditions.invisibilityOf(modalBox));
        return new RemoveCarConfirmationModalBox();
    }
    public String getCarCreationDateTitle(){
        return getText(carCreationDateTitle);
    }
}