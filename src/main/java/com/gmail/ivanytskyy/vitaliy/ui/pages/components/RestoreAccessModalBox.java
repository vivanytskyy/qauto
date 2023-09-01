package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/09/2023
 */
public class RestoreAccessModalBox extends BasePage {
    @FindBy(css = "h4.modal-title")
    private WebElement modalTitle;
    @FindBy(css = "[for='signinEmail']")
    private WebElement emailTitle;
    @FindBy(css = "#signinEmail")
    private WebElement emailInput;
    @FindBy(xpath = ".//button[contains(text(), 'Send')]")
    private WebElement sendButton;

    public RestoreAccessModalBox(WebElement container) {
        PageFactory.initElements(container, this);
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
    public String getTitle(){
        return getText(modalTitle);
    }
    public String getEmailInputFieldTitle(){
        return getText(emailTitle);
    }
    public String getSendButtonName(){
        return getText(sendButton);
    }
}