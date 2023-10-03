package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GuestPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.SignInModalBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 03/10/2023
 */
public class VisitorHeader extends Header {
    @FindBy(css = "div.header_left>nav>a[routerLink='/']")
    private WebElement homeLink;
    @FindBy(css = "div.header_left>nav>button:nth-of-type(1)")
    private WebElement aboutButton;
    @FindBy(css = "div.header_left>nav>button:nth-of-type(2)")
    private WebElement contactsButton;
    @FindBy(css = "div.header_right>button:nth-of-type(1)")
    private WebElement guestPageButton;
    @FindBy(css = "div.header_right>button:nth-of-type(1)")
    private WebElement guestLoginButton;
    @FindBy(css = "div.header_right>button:nth-of-type(2)")
    private WebElement signInButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;

    public VisitorHeader() {
        PageFactory.initElements(webDriver, this);
    }
    public MainPage openHomePage(){
        clickLink(homeLink);
        return new MainPage();
    }
    public MainPage openAbout(){
        clickButton(aboutButton);
        return new MainPage();
    }
    public MainPage openContacts(){
        clickButton(contactsButton);
        return new MainPage();
    }
    public GuestPage openGuestPage(){
        clickButton(guestLoginButton);
        return new GuestPage();
    }
    public SignInModalBox openSingInBox(){
        clickButton(signInButton);
        return new SignInModalBox(modalContent);
    }
}