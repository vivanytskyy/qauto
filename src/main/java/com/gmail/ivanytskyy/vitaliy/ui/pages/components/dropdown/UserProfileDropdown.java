package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 29/09/2023
 */
public class UserProfileDropdown extends ProfileDropdown {
    @FindBy(xpath = ".//div/a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = ".//div/a[contains(@href, '/settings')]")
    private WebElement profileSettingsLink;
    public UserProfileDropdown(WebElement element) {
        super(element);
        PageFactory.initElements(element, this);
    }
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