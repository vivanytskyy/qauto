package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 28/10/2023
 */
public abstract class ProfileDropdown extends BasePage {
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/garage')]")
    private WebElement garageLink;
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/expenses')]")
    private WebElement fuelExpensesLink;
    @FindBy(xpath = "//app-user-nav//a[contains(@href, '/instructions')]")
    private WebElement instructionsLink;
    @FindBy(xpath = "//app-user-nav//nav/button")
    private WebElement logoutButton;

    public ProfileDropdown() {
        PageFactory.initElements(webDriver, this);
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