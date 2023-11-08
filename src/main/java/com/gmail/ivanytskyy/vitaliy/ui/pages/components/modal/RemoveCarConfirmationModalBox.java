package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 01/11/2023
 */
public class RemoveCarConfirmationModalBox extends ModalBox {
    @FindBy(css = ".modal-footer .btn.btn-danger")
    private WebElement removeButton;
    @FindBy(css = ".modal-footer .btn.btn-secondary")
    private WebElement cancelButton;
    @FindBy(css = ".modal-body>p:nth-child(1)")
    private WebElement modalBodyText;
    @FindBy(css = ".modal-body>p:nth-child(2)")
    private WebElement modalDanderText;
    public void clickCancelButton(){
        clickButton(cancelButton);
    }
    public void confirmRemoving(){
        clickButton(removeButton);
    }
    public String getConfirmationText(){
        return getText(modalBodyText);
    }
    public String getDangerText(){
        return getText(modalDanderText);
    }
}