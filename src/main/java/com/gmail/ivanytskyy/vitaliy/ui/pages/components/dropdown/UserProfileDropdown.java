package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 28/10/2023
 */
public class UserProfileDropdown extends ProfileDropdown {
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/settings')]")
    private WebElement profileSettingsLink;

    public ProfilePage openProfile(){
        moveToElement(profileLink);
        profileLink.click();
        return new ProfilePage();
    }
    public SettingsPage openSettings(){
        moveToElement(profileSettingsLink);
        profileSettingsLink.click();
        return new SettingsPage();
    }
}