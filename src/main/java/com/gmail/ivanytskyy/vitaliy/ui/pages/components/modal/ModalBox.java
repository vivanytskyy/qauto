package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 28/10/2023
 */
public abstract class ModalBox extends BasePage {
    @FindBy(css = "h4.modal-title")
    protected WebElement modalTitle;
    @FindBy(css = "button.close")
    protected WebElement closeButton;
    protected String dateFormat = "dd.MM.yyyy";

    public ModalBox() {
        PageFactory.initElements(webDriver, this);
    }
    public String getTitle(){
        return getText(modalTitle);
    }
}