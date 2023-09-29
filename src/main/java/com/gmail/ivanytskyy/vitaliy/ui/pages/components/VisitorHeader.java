package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 27/09/2023
 */
public class VisitorHeader extends Header {
    @FindBy(css = "div.header_left>nav>a[routerLink='/']")
    private WebElement homeLink;
    @FindBy(css = "div.header_left>nav>button:nth-of-type(1)")
    private WebElement aboutLink;
    @FindBy(css = "div.header_left>nav>button:nth-of-type(2)")
    private WebElement contactsLink;
    @FindBy(css = "div.header_right>button:nth-of-type(1)")
    private WebElement guestPageButton;
    @FindBy(css = "div.header_right>button:nth-of-type(2)")
    private WebElement signInButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;

    public VisitorHeader() {
        PageFactory.initElements(webDriver, this);
    }
    public void openHomePage(){
        clickLink(homeLink);
    }
    public void openAbout(){
        clickLink(aboutLink);
    }
    public void openContacts(){
        clickLink(contactsLink);
    }
    public SignInModalBox openSingInBox(){
        clickButton(signInButton);
        return new SignInModalBox(modalContent);
    }
}