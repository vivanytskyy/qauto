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
 * @version 1.01
 * @date 16/11/2023
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
    private final By editModalBoxLocator = By.cssSelector("div.modal-content>app-edit-expense-modal");
    private final By removeModalBoxLocator = By.cssSelector("div.modal-content>app-delete-expense-modal");
    private final String editedExpenseAlert = "Fuel expense edited";

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
        wait.until(driver -> {
            if(driver.findElements(alertExistLocator).size() != 0){
                return !driver.findElement(alertExistLocator).getText().contains(editedExpenseAlert);
            }
            return true;
        });
        actions
                .moveToElement(editExpenseButton)
                .click()
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(editModalBoxLocator));
        return new EditExpenseModalBox();
    }
    public RemoveExpenseConfirmationModalBox removeExpense(){
        actions
                .moveToElement(deleteButton)
                .click()
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(removeModalBoxLocator));
        return new RemoveExpenseConfirmationModalBox();
    }
}