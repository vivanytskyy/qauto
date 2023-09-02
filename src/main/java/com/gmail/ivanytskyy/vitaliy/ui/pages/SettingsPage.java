package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.RemoveAccountModalBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 02/09/2023
 */
public class SettingsPage extends UserPage{
    @FindBy(xpath = "//app-settings/div/div/h1")
    private WebElement pageTitle;
    @FindBy(css = ".btn.btn-danger-bg")
    private WebElement removeAccountButton;
    @FindBy(css = "ngb-modal-window[role='dialog']")
    private WebElement removeDialogModalWindow;

    @Override
    public String getPageTitle() {
        return getText(pageTitle);
    }
    public RemoveAccountModalBox removeAccount(){
        clickButton(removeAccountButton);
        return new RemoveAccountModalBox(removeDialogModalWindow);
    }
}