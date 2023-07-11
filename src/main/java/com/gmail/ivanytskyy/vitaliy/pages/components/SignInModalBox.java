package com.gmail.ivanytskyy.vitaliy.pages.components;

import com.gmail.ivanytskyy.vitaliy.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.pages.ProfilePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
 */
public class SignInModalBox extends BasePage {
    private final WebElement element;
    @FindBy(css = "h4.modal-title")
    private WebElement modalTitle;
    @FindBy(css = "[for='signinEmail']")
    private WebElement emailTitle;
    @FindBy(css = "[for='signupPassword']")
    private WebElement passwordTitle;
    @FindBy(css = "[for='remember']")
    private WebElement checkboxTitle;
    @FindBy(css = "#signinEmail")
    private WebElement emailInput;
    @FindBy(css = "#signinPassword")
    private WebElement passwordInput;
    @FindBy(css = "#remember")
    private WebElement rememberMeCheckbox;
    @FindBy(xpath = ".//button[contains(text(), 'Forgot password')]")
    private WebElement forgotPasswordButton;
    @FindBy(xpath = ".//button[contains(text(), 'Registration')]")
    private WebElement registrationButton;
    @FindBy(xpath = ".//button[contains(text(), 'Login')]")
    private WebElement loginButton;
    @FindBy(xpath = ".//p[@class='alert alert-danger']")
    private WebElement alertMessage;

    public SignInModalBox(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public String getTitle(){
        return getText(modalTitle);
    }
    public SignInModalBox setEmail(String email){
        setTextFieldValue(emailInput, email);
        return this;
    }
    public SignInModalBox setPassword(String password){
        setTextFieldValue(passwordInput, password);
        return this;
    }
    public GaragePage clickLoginButtonPositiveCase(){
        clickButton(loginButton);
        return new GaragePage();
    }
    public SignInModalBox clickLoginButtonNegativeCase(){
        clickButton(loginButton);
        return this;
    }
    public String getAlertMessage(){
        return getText(alertMessage);
    }
}