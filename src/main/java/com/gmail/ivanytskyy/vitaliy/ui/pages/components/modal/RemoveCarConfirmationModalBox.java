package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 16/09/2023
 */
public class RemoveCarConfirmationModalBox extends ModalBox{
    @FindBy(css = ".modal-footer .btn.btn-danger")
    private WebElement removeCarButton;
    @FindBy(css = ".modal-footer .btn.btn-secondary")
    private WebElement cancelButton;

    public RemoveCarConfirmationModalBox(WebElement container) {
        super(container);
    }
    public GaragePage clickCancelButton(){
        clickButton(cancelButton);
        return new GaragePage();
    }
    public GaragePage confirmRemoving(){
        clickButton(removeCarButton);
        return new GaragePage();
    }
}