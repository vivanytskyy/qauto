package com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar;

import com.gmail.ivanytskyy.vitaliy.ui.pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 17/10/2023
 */
public abstract class Sidebar extends BasePage {
    @FindBy(xpath = ".//a[contains(@href, '/garage')]")
    private WebElement garageLink;
    @FindBy(xpath = ".//a[contains(@href, '/expenses')]")
    private WebElement fuelExpensesLink;
    @FindBy(xpath = ".//a[contains(@href, '/instructions')]")
    private WebElement instructionsLink;
    @FindBy(css = ".btn.btn-link.text-danger.btn-sidebar.sidebar_btn")
    private WebElement logoutLink;

    public Sidebar(WebElement element) {
        PageFactory.initElements(element, this);
    }
    public GaragePage openGarage(){
        clickLink(garageLink);
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
        clickLink(logoutLink);
        wait.until(ExpectedConditions.invisibilityOf(garageLink));
        return new MainPage();
    }
}
