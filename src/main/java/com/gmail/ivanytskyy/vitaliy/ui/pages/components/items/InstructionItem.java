package com.gmail.ivanytskyy.vitaliy.ui.pages.components.items;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 18/10/2023
 */
public class InstructionItem extends BasePage {
    @FindBy(xpath = ".//a")
    private WebElement linkToInstruction;
    @FindBy(xpath = ".//a/svg")
    private WebElement svgImage;
    @FindBy(xpath = ".//a/p")
    private WebElement instructionTitle;

    public InstructionItem(WebElement container){
        PageFactory.initElements(container, this);
    }
    public String getInstructionTitle(){
        return getText(instructionTitle);
    }
}