package com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar;

import com.gmail.ivanytskyy.vitaliy.ui.pages.user.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 01/11/2023
 */
public class UserSidebar extends Sidebar {
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@href, '/settings')]")
    private WebElement settingsLink;

    public UserGaragePage openGarage(){
        clickElement(garageLink);
        return new UserGaragePage();
    }
    public UserInstructionsPage openInstructions(){
        clickElement(instructionsLink);
        return new UserInstructionsPage();
    }
    public UserExpensesPage openExpenses(){
        clickElement(fuelExpensesLink);
        return new UserExpensesPage();
    }
    public UserProfilePage openProfile(){
        clickElement(profileLink);
        return new UserProfilePage();
    }
    public UserSettingsPage openSettings(){
        clickElement(settingsLink);
        return new UserSettingsPage();
    }
}