package com.gmail.ivanytskyy.vitaliy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
public class GuestPage extends BasePage{
    @FindBy(xpath = "//div[@class='panel-page_heading d-flex justify-content-between']/h1")
    private WebElement garageTitle;
    public GuestPage(){
        PageFactory.initElements(webDriver, this);
    }
    public String getPageTitle(){
        return garageTitle.getText();
    }
}
