package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.HomePage;
import com.gmail.ivanytskyy.vitaliy.ui.utils.UICookieHolder;
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
    @FindBy(css = ".modal-body>p:nth-child(1)")
    private WebElement modalBodyText;
    @FindBy(css = ".modal-body>p:nth-child(2)")
    private WebElement modalDanderText;

    public String getConfirmationText(){
        return getText(modalBodyText);
    }
    public String getDangerText(){
        return getText(modalDanderText);
    }
    public void clickCancel(){
        clickButton(cancelButton);
    }
    public HomePage clickRemove(){
        clickButton(removeButton);
        UICookieHolder.setCookie(null);
        return new HomePage();
    }
}