package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
 */
public class SignUpModalBox extends BasePage {
    private final WebElement element;
    @FindBy(css = "h4.modal-title")
    private WebElement modalTitle;
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

    public SignUpModalBox(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public String getTitle(){
        return modalTitle.getText();
    }
    public String getNameInputFieldTitle(){
        return nameTitle.getText();
    }
    public String getLastNameInputFieldTitle(){
        return lastNameTitle.getText();
    }
    public String getEmailInputFieldTitle(){
        return emailTitle.getText();
    }
    public String getPasswordInputFieldTitle(){
        return passwordTitle.getText();
    }
    public String getReEnterPasswordInputFieldTitle(){
        return repeatPasswordTitle.getText();
    }
    public String getRegisterButtonName(){
        return registerButton.getText();
    }
    public SignUpModalBox setName(String name){
        setTextFieldValue(nameInput, name);
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
}