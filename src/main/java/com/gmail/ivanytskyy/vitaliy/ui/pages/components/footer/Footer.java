package com.gmail.ivanytskyy.vitaliy.ui.pages.components.footer;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 28/09/2023
 */
public class Footer extends BasePage {
    @FindBy(css = "a.footer_logo")
    private WebElement homeLinkByFooterLogo;
    @FindBy(css = "footer p:nth-child(1)")
    private WebElement copyrightText;
    @FindBy(css = "footer p:nth-child(2)")
    private WebElement posterText;

    public Footer() {
        PageFactory.initElements(webDriver, this);
    }
    public MainPage openHomePageByFooterLogo(){
        clickLink(homeLinkByFooterLogo);
        return new MainPage();
    }
    public String getCopyrightText(){
        return getText(copyrightText);
    }
    public String getPosterText(){
        return getText(posterText);
    }
}