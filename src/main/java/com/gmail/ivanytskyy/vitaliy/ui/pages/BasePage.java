package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
    protected Actions actions;
    public BasePage(){
        this.webDriver = WebDriverHolder.getWebDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.actions = new Actions(webDriver);
    }
    protected void clickButton(WebElement button){
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }
    protected void clickLink(WebElement link){
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();
    }
    protected String getText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    protected void setTextFieldValue(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
    protected void moveToElement(WebElement element){
        actions.moveToElement(element).perform();
    }
}