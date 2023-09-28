package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.Footer;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.SignUpModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.VisitorHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 28/09/2023
 */
public class MainPage extends BasePage{
    @FindBy(css = "button.header-link.-guest")
    private WebElement guestLoginButton;
    @FindBy(css = "button.btn.btn-outline-white.header_signin")
    private WebElement signInButton;
    @FindBy(css = "button.hero-descriptor_btn.btn.btn-primary")
    private WebElement signUpButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    @FindBy(css = "div>h1")
    private WebElement title;
    @FindBy(css = "app-header")
    private WebElement visitorHeader;
    @FindBy(css = "footer.footer")
    private WebElement footer;

    public MainPage(){
        PageFactory.initElements(webDriver, this);
    }
    public GuestPage openGuestPage(){
        clickButton(guestLoginButton);
        return new GuestPage();
    }
    public SignUpModalBox openSingUpBox(){
        clickButton(signUpButton);
        return new SignUpModalBox(modalContent);
    }
    public String getPageTitle(){
        return getText(title);
    }
    public VisitorHeader moveToVisitorHeader(){
        return new VisitorHeader();
    }
    public Footer moveToFooter(){
        return new Footer();
    }
}