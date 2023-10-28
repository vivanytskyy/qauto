package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.05
 * @date 28/10/2023
 */
public class AddCarModalBox extends CarModalBox{
    @FindBy(css = ".modal-footer .btn.btn-primary")
    private WebElement addButton;

   public AddCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId);
        return this;
    }
    public AddCarModalBox selectModelById(int modelId){
        selectByIndex(modelSelect, modelId);
        return this;
    }
    public AddCarModalBox selectBrandByName(String brandName){
        selectByText(brandSelect, brandName);
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
    public GaragePage clickAddCarButtonPositiveCase(){
        clickButton(addButton);
        return new GaragePage();
    }
    public GaragePage addCarPositiveCase(int brandId, int modelId, int mileage){
        return selectBrandById(brandId)
                .selectModelById(modelId)
                .setMileage(mileage)
                .clickAddCarButtonPositiveCase();
    }
    public GaragePage addCarPositiveCase(String brandName, String modelName, int mileage){
        return selectBrandByName(brandName)
                .selectModelByName(modelName)
                .setMileage(mileage)
                .clickAddCarButtonPositiveCase();
    }
}