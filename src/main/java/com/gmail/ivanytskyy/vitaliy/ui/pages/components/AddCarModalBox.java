package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 12/09/2023
 */
public class AddCarModalBox extends ModalBox{
    @FindBy(css = "[for='addCarBrand']")
    private WebElement brandTitle;
    @FindBy(css = "[for='addCarModel']")
    private WebElement modelTitle;
    @FindBy(css = "[for='addCarMileage']")
    private WebElement mileageTitle;
    @FindBy(css = "select[id='addCarBrand']")
    private WebElement brandSelect;
    @FindBy(css = "select[id='addCarModel']")
    private WebElement modelSelect;
    @FindBy(css = "input[id='addCarMileage']")
    private WebElement mileageInput;
    @FindBy(css = "div.input-group-text")
    private WebElement mileageUnit;
    @FindBy(css = ".btn.btn-secondary")
    private WebElement cancelButton;
    @FindBy(css = ".modal-footer .btn.btn-primary")
    private WebElement addButton;
    @FindBy(css = "button.close")
    private WebElement closeButton;
    public AddCarModalBox(WebElement container){
        super(container);
    }
    public AddCarModalBox selectBrandById(int brandId){
        selectByIndex(brandSelect, brandId + 1);
        return this;
    }
    public AddCarModalBox selectModelById(int modelId){
        selectByIndex(brandSelect, modelId + 1);
        return this;
    }
    public AddCarModalBox selectBrandByName(String brandName){
        selectByText(brandSelect, brandName);
        return this;
    }
    public AddCarModalBox selectModelByName(String modelName){
        selectByText(brandSelect, modelName);
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
    public GaragePage clickCancelButton(){
        clickButton(cancelButton);
        return new GaragePage();
    }
    public String getMileageUnit(){
        return getText(mileageUnit);
    }
    public String getBrandSelectTitle(){
        return getText(brandTitle);
    }
    public String getModelSelectTitle(){
        return getText(modelTitle);
    }
    public String getMileageInputFieldTitle(){
        return getText(mileageTitle);
    }
    public String getAddCarButtonName(){
        return getText(addButton);
    }
    public String getCancelButtonName(){
        return getText(cancelButton);
    }
}