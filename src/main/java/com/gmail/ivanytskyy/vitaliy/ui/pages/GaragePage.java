package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.AddCarModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.CarItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 12/09/2023
 */
public class GaragePage extends UserPage{
    @FindBy(xpath = "//app-garage/div/div/h1")
    private WebElement pageTitle;
    @FindBy(css = ".panel-page .btn.btn-primary")
    private WebElement addCarButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    private final By carListLocator = By.cssSelector("ul.car-list");
    @FindBy(css = "li.car-item")
    private List<WebElement> carsList;

    @Override
    public String getPageTitle(){
        return getText(pageTitle);
    }
    public AddCarModalBox addCar(){
        clickButton(addCarButton);
        return new AddCarModalBox(modalContent);
    }
    public CarItem getCarItem(int index){
        wait.until(ExpectedConditions.presenceOfElementLocated(carListLocator));
        return new CarItem(carsList.get(index - 1));
    }
}