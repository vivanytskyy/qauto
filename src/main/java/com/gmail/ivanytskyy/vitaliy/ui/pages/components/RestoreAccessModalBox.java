package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 04/09/2023
 */
public class RestoreAccessModalBox extends ModalBox {
    @FindBy(css = "[for='signinEmail']")
    private WebElement emailTitle;
    @FindBy(css = "#signinEmail")
    private WebElement emailInput;
    @FindBy(xpath = ".//button[contains(text(), 'Send')]")
    private WebElement sendButton;

    public RestoreAccessModalBox(WebElement container) {
        super(container);
    }
    public RestoreAccessModalBox setEmail(String email){
        setTextFieldValue(emailInput, email);
        return this;
    }
    public MainPage clickSendButtonPositiveCase(){
        clickButton(sendButton);
        return new MainPage();
    }
    public MainPage restoreAccessPositiveCase(String email){
        return setEmail(email)
                .clickSendButtonPositiveCase();
    }
    public String getEmailInputFieldTitle(){
        return getText(emailTitle);
    }
    public String getSendButtonName(){
        return getText(sendButton);
    }
}