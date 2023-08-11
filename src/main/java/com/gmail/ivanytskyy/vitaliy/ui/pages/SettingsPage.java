package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.RemoveAccountModalBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 11/07/2023
 */
public class SettingsPage extends BasePage{
    @FindBy(css = ".btn.btn-danger-bg")
    private WebElement removeAccountButton;
    @FindBy(css = "ngb-modal-window[role='dialog']")
    private WebElement removeDialogModalWindow;
    public SettingsPage() {
        PageFactory.initElements(webDriver, this);
    }
    public RemoveAccountModalBox removeAccount(){
        clickButton(removeAccountButton);
        return new RemoveAccountModalBox(removeDialogModalWindow);
    }
}