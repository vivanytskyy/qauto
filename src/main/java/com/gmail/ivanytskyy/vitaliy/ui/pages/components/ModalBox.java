package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 04/09/2023
 */
public abstract class ModalBox extends BasePage {
    protected final WebElement container;
    @FindBy(css = "h4.modal-title")
    protected WebElement modalTitle;

    public ModalBox(WebElement container) {
        this.container = container;
        PageFactory.initElements(container, this);
    }
    public String getTitle(){
        return getText(modalTitle);
    }
}