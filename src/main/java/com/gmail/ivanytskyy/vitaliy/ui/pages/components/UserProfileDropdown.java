package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 04/09/2023
 */
public class UserProfileDropdown extends BasePage {
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
    public GaragePage openGarage(){
        try {
            clickLink(garageLink);
        }catch (ElementClickInterceptedException e){
            e.printStackTrace();
        }
        return new GaragePage();
    }
    public InstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new InstructionsPage();
    }
    public ExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new ExpensesPage();
    }
    public MainPage logout(){
        clickLink(logoutButton);
        return new MainPage();
    }
}