package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.SignInModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.SignUpModalBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
public class MainPage extends BasePage{
    @FindBy(css = "button.header-link.-guest")
    private WebElement guestLoginButton;
    @FindBy(css = "button.btn.btn-outline-white.header_signin")
    private WebElement signInButton;
    @FindBy(css = "button.hero-descriptor_btn.btn.btn-primary")
    private WebElement signUpButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    @FindBy(css = "div>h1")
    private WebElement title;
    public MainPage(){
        PageFactory.initElements(webDriver, this);
    }
    public GuestPage openGuestPage(){
        clickButton(guestLoginButton);
        return new GuestPage();
    }
    public SignInModalBox openSingInBox(){
        clickButton(signInButton);
        return new SignInModalBox(modalContent);
    }
    public SignUpModalBox openSingUpBox(){
        clickButton(signUpButton);
        return new SignUpModalBox(modalContent);
    }
    public String getPageTitle(){
        return getText(title);
    }
}