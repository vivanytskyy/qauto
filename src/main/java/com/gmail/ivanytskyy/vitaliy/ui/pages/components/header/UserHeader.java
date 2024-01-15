package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown.UserProfileDropdown;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserInstructionsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    @FindBy(css = "app-user-nav>div")
    private WebElement dropdown;

    public UserGaragePage openGarage(){
        clickElement(garageLink);
        return new UserGaragePage();
    }
    public UserProfileDropdown openUserProfileDropdown(){
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        clickElement(userProfileDropdownButton);
        waitForPartOfAttributeValueChanged(dropdown, "class", "show");
        return new UserProfileDropdown();
    }
    public UserInstructionsPage openInstructions(){
        clickElement(instructionsLink);
        return new UserInstructionsPage();
    }
    public UserExpensesPage openExpenses(){
        clickElement(fuelExpensesLink);
        return new UserExpensesPage();
    }
}