package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 18/09/2023
 */
public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected Actions actions;
    public BasePage(){
        this.webDriver = WebDriverHolder.getWebDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(7));
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
    protected void selectCheckbox(WebElement checkbox, boolean selectCheckbox){
        boolean initialCondition = wait.until(ExpectedConditions.visibilityOf(checkbox)).isSelected();
        if(initialCondition != selectCheckbox) {
            wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        }
    }
    protected void selectByIndex(WebElement element, int index){
        Select select = new Select(element);
        clickWebElement(element);
        select.selectByIndex(index);
    }
    protected void selectByText(WebElement element, String text){
        Select select = new Select(element);
        clickWebElement(element);
        select.selectByVisibleText(text);
    }
    protected String getAttribute(WebElement element, String attributeName){
        return wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(attributeName);
    }
    protected void clickWebElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}