package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.AddCarModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.Footer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 29/09/2023
 */
public class GuestPage extends BasePage{
    @FindBy(xpath = "//app-garage/div/div/h1")
    private WebElement garageTitle;
    @FindBy(css = "button.btn.btn-primary")
    private WebElement addCarButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    public GuestPage(){
        PageFactory.initElements(webDriver, this);
    }
    public String getPageTitle(){
        return getText(garageTitle);
    }
    public AddCarModalBox openAddCarModalBox(){
        clickButton(addCarButton);
        return new AddCarModalBox(modalContent);
    }
    public Footer moveToFooter(){
        return new Footer();
    }
}