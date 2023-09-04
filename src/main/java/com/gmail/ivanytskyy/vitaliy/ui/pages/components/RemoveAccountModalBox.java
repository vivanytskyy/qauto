package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.SettingsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 04/09/2023
 */
public class RemoveAccountModalBox extends ModalBox {
    @FindBy(xpath = ".//button[@class='btn btn-secondary']")
    private WebElement cancelButton;
    @FindBy(xpath = ".//button[@class='btn btn-danger']")
    private WebElement removeButton;

    public RemoveAccountModalBox(WebElement container) {
        super(container);
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