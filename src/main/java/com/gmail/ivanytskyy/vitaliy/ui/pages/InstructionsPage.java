package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.InstructionItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.time.Duration;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 19/10/2023
 */
public class InstructionsPage extends UserPage{
    @FindBy(xpath = "//app-instructions/div/div/h1")
    private WebElement pageTitle;
    @FindBy(css = "li.instruction-list_item")
    private List<WebElement> instructionItems;
    @FindBy(css = "button[id='brandSelectDropdown']")
    private WebElement brandSelectButton;
    @FindBy(css = "button[id='modelSelectDropdown']")
    private WebElement modelSelectButton;
    @FindBy(css = ".brand-select-dropdown_menu>.dropdown-item")
    private List<WebElement> brandItems;
    @FindBy(css = ".model-select-dropdown_menu>.dropdown-item")
    private List<WebElement> modelItems;
    @FindBy(css = ".instructions-search-controls>button")
    private WebElement searchButton;
    @FindBy(css = "div.instructions_content")
    private WebElement changedInstructionContentMarker;
    @FindBy(css = ".pagination>li")
    private List<WebElement> paginationItems;
    private By instructionItemsLocator = By.cssSelector("li.instruction-list_item");
    private final String contentMarkerAttributeName = "class";
    private final String contentMarkerAttributeValue = "instructions_content";
    private final String selectButtonAttributeName = "aria-expanded";
    private final String selectButtonAttributeValue = "false";
    private final String brandSelectButtonDefaultValue = "Choose brand";
    private final String modelSelectButtonDefaultValue = "Choose model";

    @Override
    public String getPageTitle() {
        return getText(pageTitle);
    }
    public String getInstructionTitle(int instructionNumber){
        if(instructionItems.size() == 0 || instructionNumber <= 0)
            throw new IllegalArgumentException("Instruction was not found");
        int index = instructionNumber - 1;
        int paginationNumber = paginationItems.size();
        if(index >= 8 && paginationNumber != 0){
            for (int i = 2; i < paginationNumber - 1; i++){
                index -= 8;
                paginationItems.get(i).click();
                waitForInstructionContentChanged();
                if (index < 8){
                    return new InstructionItem(webDriver.findElements(instructionItemsLocator)
                            .get(index)).getInstructionTitle();
                }
            }
        }
        return new InstructionItem(instructionItems.get(index)).getInstructionTitle();
    }
    public int getNumberOfInstructions(){
        int numberOfInstructions = instructionItems.size();
        if (paginationItems.size() != 0){
            for (int i = 2; i < paginationItems.size() - 1; i++){
                paginationItems.get(i).click();
                waitForInstructionContentChanged();
                numberOfInstructions += webDriver.findElements(instructionItemsLocator).size();
            }
        }
        return numberOfInstructions;
    }
    public InstructionsPage setBrand(String brandName){
        waitForOldTextChanged(brandSelectButton, brandSelectButtonDefaultValue);
        if(!brandSelectButton.getText().equals(brandName)){
            clickButton(brandSelectButton);
            actions
                    .moveToElement(brandItems
                            .stream()
                            .filter(brand -> brand.getText().equals(brandName))
                            .findFirst()
                            .orElseThrow())
                    .click()
                    .pause(Duration.ofMillis(500))
                    .perform();
            waitForAttributeValueChanged(brandSelectButton, selectButtonAttributeName, selectButtonAttributeValue);
        }
        return this;
    }
    public InstructionsPage setModel(String modelName){
        waitForOldTextChanged(modelSelectButton, modelSelectButtonDefaultValue);
        if (!modelSelectButton.getText().equals(modelName)){
            clickButton(modelSelectButton);
            actions
                    .moveToElement(modelItems
                            .stream()
                            .filter(brand -> brand.getText().equals(modelName))
                            .findFirst()
                            .orElseThrow())
                    .click()
                    .perform();
            waitForAttributeValueChanged(brandSelectButton, selectButtonAttributeName, selectButtonAttributeValue);
        }
        return this;
    }
    public InstructionsPage clickSearchButton(){
        clickButton(searchButton);
        waitForInstructionContentChanged();
        return new InstructionsPage();
    }
    public InstructionsPage searchInstructions(String brandName, String modelName){
        return setBrand(brandName)
                .setModel(modelName)
                .clickSearchButton();
    }
    private void waitForInstructionContentChanged(){
        waitForAttributeValueChanged(changedInstructionContentMarker, contentMarkerAttributeName,
                contentMarkerAttributeValue);
    }
}