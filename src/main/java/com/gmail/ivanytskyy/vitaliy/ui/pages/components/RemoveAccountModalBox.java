package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.SettingsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 11/07/2023
 */
public class RemoveAccountModalBox extends BasePage {
    private final WebElement element;
    @FindBy(css = ".modal-content .modal-title")
    private WebElement title;
    @FindBy(xpath = ".//button[@class='btn btn-secondary']")
    private WebElement cancelButton;
    @FindBy(xpath = ".//button[@class='btn btn-danger']")
    private WebElement removeButton;

    public RemoveAccountModalBox(WebElement element) {
        this.element = element;
        PageFactory.initElements(element, this);
    }
    public String getTitle(){
        return getText(title);
    }
    public SettingsPage clickCancel(){
        clickButton(cancelButton);
        return new SettingsPage();
    }
    public MainPage clickRemove(){
        clickButton(removeButton);
        return new MainPage();
    }
}