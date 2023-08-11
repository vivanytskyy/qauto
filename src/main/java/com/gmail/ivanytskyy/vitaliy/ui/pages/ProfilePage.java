package com.gmail.ivanytskyy.vitaliy.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 07/07/2023
 */
public class ProfilePage extends BasePage{
    @FindBy(xpath = "//app-profile/div/div/h1")
    private WebElement profileTitle;
    @FindBy(css = ".profile_name.display-4")
    private WebElement profileName;

    public ProfilePage() {
        PageFactory.initElements(webDriver, this);
    }
    public String getPageTitle(){
        return getText(profileTitle);
    }
    public String getProfileName(){
        return getText(profileName);
    }
}