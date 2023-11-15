package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 15/11/2023
 */
public class RemoveExpenseConfirmationModalBox extends ModalBox{
    @FindBy(css = ".modal-footer .btn.btn-danger")
    protected WebElement removeButton;
    @FindBy(css = ".modal-footer .btn.btn-secondary")
    private WebElement cancelButton;
    @FindBy(css = ".modal-body>p:nth-child(1)")
    private WebElement modalBodyText;

    public void clickCancelButton(){
        clickButton(cancelButton);
    }
    public void confirmRemoving(){
        clickButton(removeButton);
        wait.until(driver -> driver.findElements(alertExistLocator).size() == 0);
    }
    public String getConfirmationText(){
        return getText(modalBodyText);
    }
}