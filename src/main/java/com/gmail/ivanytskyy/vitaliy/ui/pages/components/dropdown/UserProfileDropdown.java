package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.user.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.05
 * @date 03/11/2023
 */
public class UserProfileDropdown extends ProfileDropdown {
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/profile')]")
    private WebElement profileLink;
    @FindBy(xpath = "//app-user-nav//div/a[contains(@href, '/settings')]")
    private WebElement profileSettingsLink;
    private final By garageLinkLocator = By.xpath("//app-user-nav//nav/a[contains(@href, '/garage')]");
    private final By profileLinkLocator = By.xpath("//app-user-nav//div/a[contains(@href, '/profile')]");

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
        waitForPartOfAttributeValueChanged(
                webDriver.findElement(garageLinkLocator), "class", "disabled");
        profileLink.click();
        waitForPartOfAttributeValueChanged(
                webDriver.findElement(profileLinkLocator), "class", "disabled");
        return new UserProfilePage();
    }
    public UserSettingsPage openSettings(){
        moveToElement(profileSettingsLink);
        profileSettingsLink.click();
        return new UserSettingsPage();
    }
}