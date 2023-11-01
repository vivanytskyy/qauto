package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public class RemoveAccountModalBox extends ModalBox {
    @FindBy(xpath = "//button[@class='btn btn-secondary']")
    private WebElement cancelButton;
    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement removeButton;

    public void clickCancel(){
        clickButton(cancelButton);
    }
    public MainPage clickRemove(){
        clickButton(removeButton);
        return new MainPage();
    }
}