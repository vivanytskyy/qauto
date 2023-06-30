package com.gmail.ivanytskyy.vitaliy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
public class MainPage extends BasePage{
    @FindBy(xpath = "//button[text()='Guest log in']")
    private WebElement guestLoginButton;
    public MainPage(){
        PageFactory.initElements(webDriver, this);
    }
    public GuestPage openGuestPage(){
        clickButton(guestLoginButton);
        return new GuestPage();
    }
}