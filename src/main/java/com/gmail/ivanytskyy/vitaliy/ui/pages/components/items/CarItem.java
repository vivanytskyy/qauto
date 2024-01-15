package com.gmail.ivanytskyy.vitaliy.ui.pages.components.items;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.EditCarModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.AddExpenseModalBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 16/11/2023
 */
public class CarItem extends BasePage {
    private final WebElement container;
    @FindBy(xpath = ".//app-car//p[@class='car_name h2']")
    private WebElement itemTitle;
    @FindBy(xpath = ".//app-car//div/button[1]/span")
    private WebElement carEditButton;
    @FindBy(xpath = ".//app-car//div/button[2]")
    private WebElement addExpenseButton;
    @FindBy(xpath = ".//app-car//div[@class='car-body']/p")
    private WebElement mileageUpdateDataInfo;
    @FindBy(xpath = ".//app-car//input")
    private WebElement mileageInput;
    @FindBy(xpath = ".//app-car//form//button")
    private WebElement updateMileageButton;
    @FindBy(css = "div.modal-content>app-edit-car-modal")
    private WebElement modalBox;
    private final By modalContentLocator = By.cssSelector("div.modal-content");
    private final String addedExpenseAlert = "Fuel expense added";

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
    public EditCarModalBox editCar(){
        clickElement(carEditButton);
        return new EditCarModalBox();
    }
    public AddExpenseModalBox addExpense(){
        wait.until(driver -> {
            if(driver.findElements(alertExistLocator).size() != 0){
                return !driver.findElement(alertExistLocator).getText().contains(addedExpenseAlert);
            }
            return true;
        });
        clickElement(addExpenseButton);
        return new AddExpenseModalBox();
    }
}