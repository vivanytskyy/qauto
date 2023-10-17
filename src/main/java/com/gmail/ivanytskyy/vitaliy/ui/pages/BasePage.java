package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 15/10/2023
 */
public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor javascriptExecutor;
    public BasePage(){
        this.webDriver = WebDriverHolder.getWebDriver();
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(7));
        this.actions = new Actions(webDriver);
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
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
    protected boolean isElementDisplayed(WebElement element){
        return wait.until(elementDisplayInViewport(element));
    }
    protected boolean isAttributeValueChanged(WebElement element, String attribute,
                                              String expectedPartOfAttributeValue){
        return wait.until(attributeValueChanged(element, attribute, expectedPartOfAttributeValue));
    }
    private ExpectedCondition<Boolean> elementDisplayInViewport(WebElement element) {
        final String jsScript = """
                         var elem = arguments[0], 
                         box = elem.getBoundingClientRect(), 
                         cx = box.left + box.width / 2, 
                         cy = box.top + box.height / 2, 
                         e = document.elementFromPoint(cx, cy);
                         for (; e; e = e.parentElement) {
                            if (e === elem) return true;
                         }
                         return false;
                      """;
        return driver -> (Boolean) javascriptExecutor.executeScript(jsScript, element);
    }
    private ExpectedCondition<Boolean> attributeValueChanged(WebElement element, String attribute,
                                                               String expectedPartOfAttributeValue){
        return driver -> element.getAttribute(attribute).contains(expectedPartOfAttributeValue);
    }
}