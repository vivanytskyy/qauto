package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 04/09/2023
 */
public class UserSidebar extends BasePage {
    @FindBy(xpath = ".//a[contains(@href, '/garage')]")
    private WebElement garageLink;
    @FindBy(xpath = ".//a[contains(@href, '/expenses')]")
    private WebElement fuelExpensesLink;
    @FindBy(xpath = ".//a[contains(@href, '/instructions')]")
    private WebElement instructionsLink;
    @FindBy(xpath = ".//a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = ".//a[contains(@href, '/settings')]")
    private WebElement settingsLink;
    @FindBy(css = ".btn.btn-link.text-danger.btn-sidebar.sidebar_btn")
    private WebElement logoutLink;

    public UserSidebar(WebElement element) {
        PageFactory.initElements(element, this);
    }
    public GaragePage openGarage(){
        clickLink(garageLink);
        return new GaragePage();
    }
    public ProfilePage openProfile(){
        clickLink(profileLink);
        return new ProfilePage();
    }
    public SettingsPage openSettings(){
        clickLink(settingsLink);
        return new SettingsPage();
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
        clickLink(logoutLink);
        return new MainPage();
    }
}