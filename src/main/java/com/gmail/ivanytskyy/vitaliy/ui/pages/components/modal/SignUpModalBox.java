package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.05
 * @date 01/11/2023
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
    @FindBy(xpath = "//input[@id='signupName']/../div")
    private WebElement invalidNameMessage;
    @FindBy(xpath = "//input[@id='signupLastName']/../div")
    private WebElement invalidLastNameMessage;
    @FindBy(xpath = "//input[@id='signupEmail']/../div")
    private WebElement invalidEmailMessage;
    @FindBy(xpath = "//input[@id='signupPassword']/../div")
    private WebElement invalidPasswordMessage;
    @FindBy(xpath = "//input[@id='signupRepeatPassword']/../div")
    private WebElement invalidRepeatPasswordMessage;
    @FindBy(css = "form>p")
    private WebElement formErrorAlert;

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
    public UserGaragePage clickRegisterButtonPositiveCase(){
        clickButton(registerButton);
        return new UserGaragePage();
    }
    public SignUpModalBox clickRegisterButtonNegativeCase(){
        registerButton.click();
        return this;
    }
    public UserGaragePage registerPositiveCase(String firstName, String lastName, String email, String password){
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setReEnterPassword(password)
                .clickRegisterButtonPositiveCase();
    }
    public SignUpModalBox registerNegativeCase(String firstName, String lastName, String email,
                                               String password, String repeatedPassword){
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPassword(password)
                .setReEnterPassword(repeatedPassword)
                .clickRegisterButtonNegativeCase();
    }
    public MainPage closeModalBox(){
        clickButton(closeButton);
        return new MainPage();
    }
    public String getInvalidNameMessage(){
        return getText(invalidNameMessage);
    }
    public String getInvalidLastNameMessage(){
        return getText(invalidLastNameMessage);
    }
    public String getInvalidEmailMessage(){
        return getText(invalidEmailMessage);
    }
    public String getInvalidPasswordMessage(){
        return getText(invalidPasswordMessage);
    }
    public String getInvalidRepeatPasswordMessage(){
        return getText(invalidRepeatPasswordMessage);
    }
}