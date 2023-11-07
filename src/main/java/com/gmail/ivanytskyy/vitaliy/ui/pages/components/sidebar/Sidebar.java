package com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 07/11/2023
 */
public class Sidebar extends BasePage {
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@href, '/garage')]")
    protected WebElement garageLink;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@href, '/expenses')]")
    protected WebElement fuelExpensesLink;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@href, '/instructions')]")
    protected WebElement instructionsLink;
    @FindBy(xpath = "//nav[contains(@class, 'sidebar')]//a[contains(@class, 'text-danger')]")
    private WebElement logoutLink;
    private final By sidebarLocator = By.cssSelector("nav.sidebar");

    public Sidebar() {
        PageFactory.initElements(webDriver, this);
    }
    public MainPage logout(){
        clickLink(logoutLink);
        wait.until(driver -> driver.findElements(sidebarLocator).size() == 0);
        return new MainPage();
    }
}