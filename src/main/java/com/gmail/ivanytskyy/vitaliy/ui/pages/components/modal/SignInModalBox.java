package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 16/09/2023
 */
public class SignInModalBox extends ModalBox {
    @FindBy(css = "[for='signinEmail']")
    private WebElement emailTitle;
    @FindBy(css = "[for='signinPassword']")
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
    private WebElement formErrorAlert;
    @FindBy(css = "div.modal-content>app-signin-modal")
    private WebElement modalBox;
    private final By modalContentLocator = By.cssSelector("div.modal-content");

    public SignInModalBox(WebElement container) {
        super(container);
    }
    public SignInModalBox setEmail(String email){
        setTextFieldValue(emailInput, email);
        return this;
    }
    public SignInModalBox setPassword(String password){
        setTextFieldValue(passwordInput, password);
        return this;
    }
    public SignInModalBox setRememberMe(boolean needRemember){
        selectCheckbox(rememberMeCheckbox, needRemember);
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
    public String getFormErrorMessage(){
        return getText(formErrorAlert);
    }
    public GaragePage loginPositiveCase(String email, String password, boolean needRemember){
        return setEmail(email)
                .setPassword(password)
                .setRememberMe(needRemember)
                .clickLoginButtonPositiveCase();
    }
    public SignInModalBox loginNegativeCase(String email, String password, boolean needRemember){
        return setEmail(email)
                .setPassword(password)
                .setRememberMe(needRemember)
                .clickLoginButtonNegativeCase();
    }
    public SignUpModalBox toRegistration(){
        clickButton(registrationButton);
        wait.until(ExpectedConditions.invisibilityOf(modalBox));
        return new SignUpModalBox(webDriver.findElement(modalContentLocator));
    }
    public RestoreAccessModalBox toRestoreAccess(){
        clickButton(forgotPasswordButton);
        wait.until(ExpectedConditions.invisibilityOf(modalBox));
        return new RestoreAccessModalBox(webDriver.findElement(modalContentLocator));
    }
    public MainPage closeModalBox(){
        clickButton(closeButton);
        return new MainPage();
    }
    public String getEmailInputFieldTitle(){
        return getText(emailTitle);
    }
    public String getPasswordInputFieldTitle(){
        return getText(passwordTitle);
    }
    public String getCheckboxTitle(){
        return getText(checkboxTitle);
    }
    public String getLoginButtonName(){
        return getText(loginButton);
    }
}