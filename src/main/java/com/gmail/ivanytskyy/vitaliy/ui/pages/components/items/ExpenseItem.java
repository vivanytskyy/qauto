package com.gmail.ivanytskyy.vitaliy.ui.pages.components.items;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.EditExpenseModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.RemoveExpenseConfirmationModalBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.ExpensesReportHeads;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 14/11/2023
 */
public class ExpenseItem extends BasePage {
    private final WebElement container;
    @FindBy(xpath = ".//td[1]")
    private WebElement date;
    @FindBy(xpath = ".//td[2]")
    private WebElement mileage;
    @FindBy(xpath = ".//td[3]")
    private WebElement litersUsed;
    @FindBy(xpath = ".//td[4]")
    private WebElement totalCost;
    @FindBy(xpath = ".//td[5]/button[1]")
    private WebElement deleteButton;
    @FindBy(xpath = ".//td[5]/button[2]")
    private WebElement editExpenseButton;
    private final By modalBoxLocator = By.cssSelector("div.modal-content>app-delete-expense-modal");

    public ExpenseItem(WebElement container) {
        this.container = container;
        PageFactory.initElements(container, this);
    }
    public String getReportItemValue(ExpensesReportHeads itemName){
        return switch (itemName){
            case DATE -> getDate();
            case MILEAGE -> getMileage();
            case LITERS_USED -> getLitersUsed();
            case TOTAL_COST -> getTotalCost();
        };
    }
    private String getDate(){
        return getText(date);
    }
    private String getMileage(){
        return getText(mileage);
    }
    private String getLitersUsed(){
        return getText(litersUsed);
    }
    private String getTotalCost(){
        return getText(totalCost);
    }
    public EditExpenseModalBox editExpense(){
        actions
                .moveToElement(editExpenseButton)
                .click()
                .perform();
        return new EditExpenseModalBox();
    }
    public RemoveExpenseConfirmationModalBox removeExpense(){
        actions
                .moveToElement(deleteButton)
                .click()
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(modalBoxLocator));
        return new RemoveExpenseConfirmationModalBox();
    }
}