package com.gmail.ivanytskyy.vitaliy.ui.pages.components.header;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown.GuestProfileDropdown;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestInstructionsPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 01/11/2023
 */
public class GuestHeader extends Header {
    @FindBy(css = "div.header_left a[routerLink='/panel/garage']")
    private WebElement garageLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/expenses']")
    private WebElement fuelExpensesLink;
    @FindBy(css = "div.header_left a[routerLink='/panel/instructions']")
    private WebElement instructionsLink;
    @FindBy(css = "button[id='userNavDropdown']")
    private WebElement guestProfileDropdownButton;

    public GuestGaragePage openGarage(){
        clickLink(garageLink);
        return new GuestGaragePage();
    }
    public GuestProfileDropdown openUserProfileDropdown(){
        clickButton(guestProfileDropdownButton);
        return new GuestProfileDropdown();
    }
    public GuestInstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new GuestInstructionsPage();
    }
    public GuestExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new GuestExpensesPage();
    }
}