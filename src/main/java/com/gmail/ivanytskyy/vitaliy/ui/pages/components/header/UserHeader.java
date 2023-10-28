package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown.UserProfileDropdown;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 28/10/2023
 */
public class UserHeader extends Header {
    @FindBy(css = "div.header_left a[routerLink='/panel/garage']")
    private WebElement garageLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/expenses']")
    private WebElement fuelExpensesLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/instructions']")
    private WebElement instructionsLink;
    @FindBy(css = "app-user-nav")
    private WebElement dropdownContainer;
    @FindBy(css = "button[id='userNavDropdown']")
    private WebElement userProfileDropdownButton;

    public UserHeader() {
        PageFactory.initElements(webDriver, this);
    }
    public GaragePage openGarage(){
        clickLink(garageLink);
        return new GaragePage();
    }
    public UserProfileDropdown openUserProfileDropdown(){
        clickButton(userProfileDropdownButton);
        return new UserProfileDropdown();
    }
    public InstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new InstructionsPage();
    }
    public ExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new ExpensesPage();
    }
}