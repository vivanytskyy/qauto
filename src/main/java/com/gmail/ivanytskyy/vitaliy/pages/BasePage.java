package com.gmail.ivanytskyy.vitaliy.pages;

import com.gmail.ivanytskyy.vitaliy.utils.WebDriverHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 30/06/2023
 */
public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    public BasePage(){
        this.webDriver = WebDriverHolder.getWebDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));
    }
    protected void clickButton(WebElement button){
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
}