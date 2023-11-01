package com.gmail.ivanytskyy.vitaliy.ui.pages.user;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class UserExpensesPage extends UserPage {
    @FindBy(xpath = "//app-fuel-expenses/div/div/h1")
    private WebElement pageTitle;

    @Override
    public String getPageTitle() {
        return getText(pageTitle);
    }
}