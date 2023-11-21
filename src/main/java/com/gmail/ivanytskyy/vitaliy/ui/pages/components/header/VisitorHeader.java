package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.HomePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.SignInModalBox;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
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

    public HomePage openHomePage(){
        clickLink(homeLink);
        return new HomePage();
    }
    public HomePage openAbout(){
        clickButton(aboutButton);
        return new HomePage();
    }
    public HomePage openContacts(){
        clickButton(contactsButton);
        return new HomePage();
    }
    public GuestGaragePage openGuestPage(){
        clickButton(guestLoginButton);
        return new GuestGaragePage();
    }
    public SignInModalBox openSingInBox(){
        clickButton(signInButton);
        return new SignInModalBox();
    }
}