package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown.UserProfileDropdown;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserInstructionsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public class UserHeader extends Header {
    @FindBy(css = "div.header_left a[routerLink='/panel/garage']")
    private WebElement garageLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/expenses']")
    private WebElement fuelExpensesLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/instructions']")
    private WebElement instructionsLink;
    @FindBy(css = "button[id='userNavDropdown']")
    private WebElement userProfileDropdownButton;

    public UserGaragePage openGarage(){
        clickLink(garageLink);
        return new UserGaragePage();
    }
    public UserProfileDropdown openUserProfileDropdown(){
        clickButton(userProfileDropdownButton);
        return new UserProfileDropdown();
    }
    public UserInstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new UserInstructionsPage();
    }
    public UserExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new UserExpensesPage();
    }
}