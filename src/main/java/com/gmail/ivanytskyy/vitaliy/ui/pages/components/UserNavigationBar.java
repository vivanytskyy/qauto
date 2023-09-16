package com.gmail.ivanytskyy.vitaliy.ui.pages.components;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 07/09/2023
 */
public class UserNavigationBar extends BasePage {
    private final WebElement container;
    @FindBy(css = "div.header_left>a[routerLink='/']")
    private WebElement toMainPageLink;
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

    public UserNavigationBar(WebElement container) {
        this.container = container;
        PageFactory.initElements(container, this);
    }
    public GaragePage openGarage(){
        clickLink(garageLink);
        return new GaragePage();
    }
    public UserProfileDropdown openUserProfileDropdown(){
        clickButton(userProfileDropdownButton);
        return new UserProfileDropdown(dropdownContainer);
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