package com.gmail.ivanytskyy.vitaliy.pages;

import com.gmail.ivanytskyy.vitaliy.pages.components.UserProfileDropdown;
import com.gmail.ivanytskyy.vitaliy.pages.components.UserSidebar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
 */
public class GaragePage extends BasePage{
    @FindBy(xpath = "//app-garage/div/div/h1")
    private WebElement garageTitle;
    @FindBy(tagName = "app-user-nav")
    private WebElement userProfileDropdown;
    @FindBy(id = "userNavDropdown")
    private WebElement userProfileDropdownButton;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]")
    private WebElement userSidebar;

    public GaragePage() {
        PageFactory.initElements(webDriver, this);
    }
    public String getPageTitle(){
        return getText(garageTitle);
    }
    public UserProfileDropdown openUserProfileDropdown(){
        clickButton(userProfileDropdownButton);
        return new UserProfileDropdown(userProfileDropdown);
    }
    public UserSidebar moveToUserSidebar(){
        return new UserSidebar(userSidebar);
    }
}