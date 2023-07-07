package com.gmail.ivanytskyy.vitaliy.pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
 */
public class SignInModalBox {
    private final WebElement element;
    @FindBy(css = "h4.modal-title")
    private WebElement modalHeading;

    public SignInModalBox(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public String getTitle(){
        return modalHeading.getText();
    }
}