package com.gmail.ivanytskyy.vitaliy.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 02/09/2023
 */
public class ProfilePage extends UserPage{
    @FindBy(xpath = "//app-profile/div/div/h1")
    private WebElement profileTitle;
    @FindBy(css = ".profile_name.display-4")
    private WebElement profileName;

    @Override
    public String getPageTitle(){
        return getText(profileTitle);
    }
    public String getProfileName(){
        return getText(profileName);
    }
}