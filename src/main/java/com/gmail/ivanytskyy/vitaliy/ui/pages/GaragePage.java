package com.gmail.ivanytskyy.vitaliy.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 02/09/2023
 */
public class GaragePage extends UserPage{
    @FindBy(xpath = "//app-garage/div/div/h1")
    private WebElement pageTitle;

    @Override
    public String getPageTitle(){
        return getText(pageTitle);
    }
}