package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.AddCarModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.CarItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 28/10/2023
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
        return new AddCarModalBox();
    }
    public CarItem getCarItem(int index){
        wait.until(ExpectedConditions.presenceOfElementLocated(carListLocator));
        return new CarItem(carsList.get(index - 1));
    }
}