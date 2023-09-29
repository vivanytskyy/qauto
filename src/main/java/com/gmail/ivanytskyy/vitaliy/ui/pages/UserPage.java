package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.Footer;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.UserHeader;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.UserSidebar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 29/09/2023
 */
public abstract class UserPage extends BasePage{
    @FindBy(css = "app-user-nav")
    private WebElement userProfileDropdown;
    @FindBy(id = "userNavDropdown")
    private WebElement userProfileDropdownButton;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]")
    private WebElement userSidebar;
    @FindBy(css = "app-header")
    private WebElement userHeader;
    public UserPage() {
        PageFactory.initElements(webDriver, this);
    }
    public abstract String getPageTitle();
    public UserSidebar moveToUserSidebar(){
        return new UserSidebar(userSidebar);
    }
    public UserHeader moveToUserHeader(){
        return new UserHeader();
    }
    public Footer moveToFooter(){
        return new Footer();
    }
}