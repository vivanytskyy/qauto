package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.08
 * @date 14/01/2024
 */
public class AddCarModalBox extends CarModalBox{
    @FindBy(css = ".modal-footer .btn.btn-primary")
    private WebElement addButton;
    @FindBy(css = "select[name='carBrandId']>option:nth-of-type(1)")
    private WebElement defaultBrandOption;
    private final String targetAttribute = "class";
    private final String expectedPartOfBrandAttributeValue = "ng-dirty";
    private final String expectedPartOfModelAttributeValue = "ng-valid";

    public AddCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId);
        waitForPartOfAttributeValueChanged(modelSelect, targetAttribute, expectedPartOfModelAttributeValue);
        if(brandId != 0){
            waitForPartOfAttributeValueChanged(brandSelect, targetAttribute, expectedPartOfBrandAttributeValue);
        }
        return this;
    }
    public AddCarModalBox selectModelById(int modelId){
        selectByIndex(modelSelect, modelId);
        return this;
    }
    public AddCarModalBox selectBrandByName(String brandName){
        clickElement(brandSelect);
        String defaultBrandName = defaultBrandOption.getText();
        selectByText(brandSelect, brandName);
        waitForPartOfAttributeValueChanged(modelSelect, targetAttribute, expectedPartOfModelAttributeValue);
        if(!defaultBrandName.equals(brandName)){
            waitForPartOfAttributeValueChanged(brandSelect, targetAttribute, expectedPartOfBrandAttributeValue);
        }
        return this;
    }
    public AddCarModalBox selectModelByName(String modelName){
        selectByText(modelSelect, modelName);
        return this;
    }
    public AddCarModalBox setMileage(int mileage){
        setTextFieldValue(mileageInput, String.valueOf(mileage));
        return this;
    }
    public void clickAddCarButtonPositiveCase(){
        clickElement(addButton);
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
    }
    public void addCarPositiveCase(int brandId, int modelId, int mileage){
        selectBrandById(brandId)
                .selectModelById(modelId)
                .setMileage(mileage)
                .clickAddCarButtonPositiveCase();
    }
    public void addCarPositiveCase(String brandName, String modelName, int mileage){
        selectBrandByName(brandName)
                .selectModelByName(modelName)
                .setMileage(mileage)
                .clickAddCarButtonPositiveCase();
    }
}