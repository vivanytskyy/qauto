package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.SettingsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 28/10/2023
 */
public class RemoveAccountModalBox extends ModalBox {
    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement cancelButton;
    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement removeButton;

    public SettingsPage clickCancel(){
        clickButton(cancelButton);
        return new SettingsPage();
    }
    public MainPage clickRemove(){
        clickButton(removeButton);
        return new MainPage();
    }
}