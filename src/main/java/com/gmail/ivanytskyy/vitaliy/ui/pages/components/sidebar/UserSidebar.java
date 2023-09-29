package com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 29/09/2023
 */
public class UserSidebar extends Sidebar {
    @FindBy(xpath = ".//a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = ".//a[contains(@href, '/settings')]")
    private WebElement settingsLink;

    public UserSidebar(WebElement element) {
        super(element);
        PageFactory.initElements(element, this);
    }
    public ProfilePage openProfile(){
        clickLink(profileLink);
        return new ProfilePage();
    }
    public SettingsPage openSettings(){
        clickLink(settingsLink);
        return new SettingsPage();
    }
}