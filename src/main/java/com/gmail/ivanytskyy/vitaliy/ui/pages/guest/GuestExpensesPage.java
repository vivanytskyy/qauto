package com.gmail.ivanytskyy.vitaliy.ui.pages.guest;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.ExpenseItem;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.AddExpenseModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.ExpensesReportHeads;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 15/11/2023
 */
public class GuestExpensesPage extends GuestPage {
    @FindBy(xpath = "//app-fuel-expenses/div/div/h1")
    private WebElement pageTitle;
    @FindBy(css = ".h3.panel-empty_message")
    private WebElement emptyPageMessage;
    @FindBy(css = ".panel-page .btn.btn-primary")
    private WebElement addExpenseButton;
    @FindBy(css = "#carSelectDropdown")
    private WebElement carSelectButton;
    @FindBy(xpath = "//button[@id='carSelectDropdown']/parent::div")
    private WebElement carDropdown;
    @FindBy(css = ".car-select-dropdown_menu>.dropdown-item")
    private List<WebElement> carItems;
    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> expenses;
    @FindBy(xpath = "//thead/tr/th")
    private List<WebElement> tableHeadTitles;
    private final By expensesTableLocator = By.xpath("//table");
    private final String selectButtonAttributeName = "aria-expanded";

    public AddExpenseModalBox addExpense(){
        clickButton(addExpenseButton);
        return new AddExpenseModalBox();
    }
    public GuestExpensesPage setFirstCarByName(String brand, String model){
        final String car = brand + " " + model;
        if (!carSelectButton.getText().equals(car)){
            clickButton(carSelectButton);
            waitForAttributeValueChanged(carSelectButton, selectButtonAttributeName, "true");
            actions
                    .moveToElement(carItems
                            .stream()
                            .filter(carItem -> carItem.getText().equals(car))
                            .findFirst()
                            .orElseThrow())
                    .click()
                    .perform();
            waitForAttributeValueChanged(carSelectButton, selectButtonAttributeName, "false");
        }
        return this;
    }
    public GuestExpensesPage setCarByOrder(int carOrder){
        int index = carOrder - 1;
        if (index != 0){
            clickButton(carSelectButton);
            waitForAttributeValueChanged(carSelectButton, selectButtonAttributeName, "true");
            actions
                    .moveToElement(carItems.get(index))
                    .click()
                    .perform();
            waitForAttributeValueChanged(carDropdown, "class", "car-select-dropdown dropdown");
        }
        return this;
    }
    public String getReportItemHead(ExpensesReportHeads headName){
        wait.until(ExpectedConditions.presenceOfElementLocated(expensesTableLocator));
        return switch (headName){
            case DATE -> getDateHead();
            case MILEAGE -> getMileageHead();
            case LITERS_USED -> getLitersUsedHead();
            case TOTAL_COST -> getTotalCostHead();
        };
    }
    public ExpenseItem getExpense(int expenseIndex){
        wait.until(driver -> driver.findElement(expensesTableLocator).isDisplayed());
        return new ExpenseItem(expenses.get(expenseIndex - 1));
    }
    @Override
    public String getPageTitle() {
        return getText(pageTitle);
    }
    public String getEmptyPageMessage(){
        return getText(emptyPageMessage);
    }
    private String getDateHead(){
        return getText(tableHeadTitles.get(0));
    }
    private String getMileageHead(){
        return getText(tableHeadTitles.get(1));
    }
    private String getLitersUsedHead(){
        return getText(tableHeadTitles.get(2));
    }
    private String getTotalCostHead(){
        return getText(tableHeadTitles.get(3));
    }
}