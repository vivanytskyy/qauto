package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 16/09/2023
 */
public class SignUpModalBox extends ModalBox {
    @FindBy(css = "[for='signupName']")
    private WebElement nameTitle;
    @FindBy(css = "[for='signupLastName']")
    private WebElement lastNameTitle;
    @FindBy(css = "[for='signupEmail']")
    private WebElement emailTitle;
    @FindBy(css = "[for='signupPassword']")
    private WebElement passwordTitle;
    @FindBy(css = "[for='signupRepeatPassword']")
    private WebElement repeatPasswordTitle;
    @FindBy(css = "#signupName")
    private WebElement nameInput;
    @FindBy(css = "#signupLastName")
    private WebElement lastNameInput;
    @FindBy(css = "#signupEmail")
    private WebElement emailInput;
    @FindBy(css = "#signupPassword")
    private WebElement passwordInput;
    @FindBy(css = "#signupRepeatPassword")
    private WebElement repeatPasswordInput;
    @FindBy(css = "div.modal-footer button")
    private WebElement registerButton;
    @FindBy(css = "form>p")
    private WebElement formErrorAlert;

    public SignUpModalBox(WebElement container) {
        super(container);
    }
    public String getNameInputFieldTitle(){
        return getText(nameTitle);
    }
    public String getLastNameInputFieldTitle(){
        return getText(lastNameTitle);
    }
    public String getEmailInputFieldTitle(){
        return getText(emailTitle);
    }
    public String getPasswordInputFieldTitle(){
        return getText(passwordTitle);
    }
    public String getReEnterPasswordInputFieldTitle(){
        return getText(repeatPasswordTitle);
    }
    public String getRegisterButtonName(){
        return getText(registerButton);
    }
    public String getFormErrorMessage(){
        return getText(formErrorAlert);
    }
    public SignUpModalBox setFirstName(String firstName){
        setTextFieldValue(nameInput, firstName);
        return this;
    }
    public SignUpModalBox setLastName(String lastName){
        setTextFieldValue(lastNameInput, lastName);
        return this;
    }
    public SignUpModalBox setEmail(String email){
        setTextFieldValue(emailInput, email);
        return this;
    }
    public SignUpModalBox setPassword(String password){
        setTextFieldValue(passwordInput, password);
        return this;
    }
    public SignUpModalBox setReEnterPassword(String reEnterPassword){
        setTextFieldValue(repeatPasswordInput, reEnterPassword);
        return this;
    }
    public GaragePage clickRegisterButtonPositiveCase(){
        clickButton(registerButton);
        return new GaragePage();
    }
    public SignUpModalBox clickRegisterButtonNegativeCase(){
        clickButton(registerButton);
        return this;
    }
    public GaragePage registerPositiveCase(String firstName, String lastName, String email, String password){
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setReEnterPassword(password)
                .clickRegisterButtonPositiveCase();
    }
    public SignUpModalBox registerNegativeCase(String firstName, String lastName, String email, String password){
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setReEnterPassword(password)
                .clickRegisterButtonNegativeCase();
    }
    public MainPage closeModalBox(){
        clickButton(closeButton);
        return new MainPage();
    }
}