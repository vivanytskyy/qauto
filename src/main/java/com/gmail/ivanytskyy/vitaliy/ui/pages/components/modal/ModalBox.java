package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 02/11/2023
 */
public abstract class ModalBox extends BasePage {
    @FindBy(css = "h4.modal-title")
    protected WebElement modalTitle;
    @FindBy(css = "button.close")
    protected WebElement closeButton;

    public ModalBox() {
        PageFactory.initElements(webDriver, this);
    }
    public String getTitle(){
        return getText(modalTitle);
    }
}