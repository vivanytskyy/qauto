package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.user.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 01/11/2023
 */
public class UserProfileDropdown extends ProfileDropdown {
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/settings')]")
    private WebElement profileSettingsLink;

    public UserGaragePage openGarage(){
        if(!garageLink.getAttribute("class").contains("disabled")){
            clickLink(garageLink);
        }
        return new UserGaragePage();
    }
    public UserInstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new UserInstructionsPage();
    }
    public UserExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new UserExpensesPage();
    }
    public UserProfilePage openProfile(){
        moveToElement(profileLink);
        profileLink.click();
        return new UserProfilePage();
    }
    public UserSettingsPage openSettings(){
        moveToElement(profileSettingsLink);
        profileSettingsLink.click();
        return new UserSettingsPage();
    }
}