package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.07
 * @date 16/11/2023
 */
public class AddCarModalBox extends CarModalBox{
    @FindBy(css = ".modal-footer .btn.btn-primary")
    private WebElement addButton;

   public AddCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId);
        waitForPartOfAttributeValueChanged(brandSelect, "class", "ng-valid");
        return this;
   }
    public AddCarModalBox selectModelById(int modelId){
        selectByIndex(modelSelect, modelId);
        return this;
    }
    public AddCarModalBox selectBrandByName(String brandName){
        selectByText(brandSelect, brandName);
        waitForPartOfAttributeValueChanged(brandSelect, "class", "ng-valid");
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
        clickButton(addButton);
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
        wait.until(driver -> {
            if(driver.findElements(alertExistLocator).size() != 0){
                return driver.findElement(alertExistLocator).getText().contains(addedCarAlert);
            }
            return true;
        });
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