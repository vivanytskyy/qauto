package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 01/11/2023
 */
public abstract class ProfileDropdown extends BasePage {
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/garage')]")
    protected WebElement garageLink;
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/expenses')]")
    protected WebElement fuelExpensesLink;
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/instructions')]")
    protected WebElement instructionsLink;
    @FindBy(xpath = "//app-user-nav//nav/button")
    private WebElement logoutButton;
    public ProfileDropdown() {
        PageFactory.initElements(webDriver, this);
    }
    public HomePage logout(){
        clickLink(logoutButton);
        return new HomePage();
    }
}