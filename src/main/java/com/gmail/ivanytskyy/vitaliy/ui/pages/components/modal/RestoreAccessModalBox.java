package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public class RestoreAccessModalBox extends ModalBox {
    @FindBy(css = "[for='signinEmail']")
    private WebElement emailTitle;
    @FindBy(css = "#signinEmail")
    private WebElement emailInput;
    @FindBy(xpath = "//button[contains(text(), 'Send')]")
    private WebElement sendButton;

    public RestoreAccessModalBox setEmail(String email){
        setTextFieldValue(emailInput, email);
        return this;
    }
    public void clickSendButtonPositiveCase(){
        clickButton(sendButton);
    }
    public void restoreAccessPositiveCase(String email){
        setEmail(email).clickSendButtonPositiveCase();
    }
    public String getEmailInputFieldTitle(){
        return getText(emailTitle);
    }
    public String getSendButtonName(){
        return getText(sendButton);
    }
}