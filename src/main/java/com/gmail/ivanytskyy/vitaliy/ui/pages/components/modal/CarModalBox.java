package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/09/2023
 */
public abstract class CarModalBox extends ModalBox{
    @FindBy(css = "[for='addCarBrand']")
    private WebElement brandTitle;
    @FindBy(css = "[for='addCarModel']")
    private WebElement modelTitle;
    @FindBy(css = "[for='addCarMileage']")
    private WebElement mileageTitle;
    @FindBy(css = "select[id='addCarBrand']")
    protected WebElement brandSelect;
    @FindBy(css = "select[id='addCarModel']")
    protected WebElement modelSelect;
    @FindBy(css = "input[id='addCarMileage']")
    protected WebElement mileageInput;
    @FindBy(css = "div.input-group-text")
    private WebElement mileageUnit;
    @FindBy(css = ".modal-footer .btn.btn-primary")
    protected WebElement saveButton;
    @FindBy(css = ".modal-footer .btn.btn-secondary")
    private WebElement cancelButton;
    protected String dateFormat = "dd.MM.yyyy";

    public CarModalBox(WebElement container) {
        super(container);
    }
    public GaragePage clickCancelButton(){
        clickButton(cancelButton);
        return new GaragePage();
    }
    public GaragePage closeModalBox(){
        clickButton(closeButton);
        return new GaragePage();
    }
    public String getCancelButtonName(){
        return getText(cancelButton);
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
    public String getMileageUnit(){
        return getText(mileageUnit);
    }
    public String getSaveCarButtonName(){
        return getText(saveButton);
    }
}