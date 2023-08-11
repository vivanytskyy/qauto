package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.ProfilePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.SettingsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 07/07/2023
 */
public class UserProfileDropdown extends BasePage {
    private final WebElement element;
    @FindBy(xpath = ".//a[contains(@href, '/garage')]")
    private WebElement garageLink;
    @FindBy(xpath = ".//a[contains(@href, '/expenses')]")
    private WebElement fuelExpensesLink;
    @FindBy(xpath = ".//a[contains(@href, '/instructions')]")
    private WebElement instructionsLink;
    @FindBy(xpath = ".//div/a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = ".//div/a[contains(@href, '/settings')]")
    private WebElement profileSettingsLink;
    @FindBy(xpath = ".button[text()='logout')]")
    private WebElement logoutButton;
    public UserProfileDropdown(WebElement element) {
        this.element = element;
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