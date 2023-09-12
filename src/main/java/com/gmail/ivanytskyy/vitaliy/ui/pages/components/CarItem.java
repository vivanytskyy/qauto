package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 12/09/2023
 */
public class CarItem extends BasePage {
    private final WebElement container;
    @FindBy(xpath = "./app-car//p[@class='car_name h2']")
    private WebElement itemTitle;
    @FindBy(xpath = "./app-car//div/button[1]")
    private WebElement carEditButton;
    @FindBy(xpath = "./app-car//div/button[2]")
    private WebElement addExpenseButton;
    @FindBy(xpath = "./app-car//div[@class='car-body']/p")
    private WebElement mileageUpdateDataInfo;
    @FindBy(xpath = "./app-car//input")
    private WebElement mileageInput;
    @FindBy(xpath = "./app-car//form//button")
    private WebElement updateMileageButton;

    public CarItem(WebElement container) {
        this.container = container;
        PageFactory.initElements(container, this);
    }
    public String getItemTitle(){
        return getText(itemTitle);
    }
    public String getMileageUpdateDataInfo(){
        return getText(mileageUpdateDataInfo);
    }
    public int getCurrentMileage(){
        return Integer.parseInt(getAttribute(mileageInput, "value"));
    }
}