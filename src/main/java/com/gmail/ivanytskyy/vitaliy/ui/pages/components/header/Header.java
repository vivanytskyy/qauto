package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 29/09/2023
 */
public abstract class Header extends BasePage {
    @FindBy(css = "div.header_left>a[routerLink='/']")
    private WebElement homeLinkByHeaderLogo;
    public Header(){
        PageFactory.initElements(webDriver, this);
    }
    public void openHomePageByHeaderLogo(){
        clickElement(homeLinkByHeaderLogo);
    }
}