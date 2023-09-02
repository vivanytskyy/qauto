package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.UserProfileDropdown;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.UserSidebar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/09/2023
 */
public abstract class UserPage extends BasePage{
    @FindBy(tagName = "app-user-nav")
    private WebElement userProfileDropdown;
    @FindBy(id = "userNavDropdown")
    private WebElement userProfileDropdownButton;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]")
    private WebElement userSidebar;
    public UserPage() {
        PageFactory.initElements(webDriver, this);
    }
    public abstract String getPageTitle();
    public UserProfileDropdown openUserProfileDropdown(){
        clickButton(userProfileDropdownButton);
        return new UserProfileDropdown(userProfileDropdown);
    }
    public UserSidebar moveToUserSidebar(){
        return new UserSidebar(userSidebar);
    }
}
