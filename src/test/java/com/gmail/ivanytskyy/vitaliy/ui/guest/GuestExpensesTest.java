package com.gmail.ivanytskyy.vitaliy.ui.guest;

import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.ExpenseItem;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.AddExpenseModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestGaragePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.StringConstants.DATE_FORMAT;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.ExpensesReportHeads.*;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.ExpensesReportHeads.TOTAL_COST;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 16/11/2023
 */
public class GuestExpensesTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Fuel expenses";
    private static final String EXPECTED_ADD_EXPENSE_MODAL_BOX_TITLE = "Add an expense";
    private static final String EMPTY_PAGE_MESSAGE_IF_CAR_EXIST = "You don’t have any fuel expenses filed in";

    @Test(description = "Open fuel expenses page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToSidebar()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open fuel expenses page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToHeader()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open fuel expenses page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToHeader()
                .openUserProfileDropdown()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Check title of add an expense modal box", priority = 40)
    public void openAddExpenseTest(){
        String brandName = "Ford";
        String modelName = "Focus";
        int mileage = new Random().nextInt(1, 100);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        AddExpenseModalBox addExpenseModalBox = guestExpensesPage
                .addExpense();
        String actualTitle = addExpenseModalBox
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_ADD_EXPENSE_MODAL_BOX_TITLE);

        addExpenseModalBox
                .closeModalBox();
        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Close add expense modal box", priority = 41)
    public void closeAddExpenseTest(){
        String brandName = "BMW";
        String modelName = "X5";
        int mileage = new Random().nextInt(1, 100);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .closeModalBox();
        String actualTitle = guestExpensesPage
                .getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Cancel add expense modal box", priority = 42)
    public void cancelAddExpenseTest(){
        String brandName = "Audi";
        String modelName = "TT";
        int mileage = new Random().nextInt(1, 100);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .clickCancelButton();
        String actualTitle = guestExpensesPage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);
        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Add an expense at the Expense page test. Positive case", priority = 50)
    public void addExpenseAtExpensePagePositiveTest(){
        String brandName = "Ford";
        String modelName = "Mondeo";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 0.1f;
        float expenseTotalCost = 0.11f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDate = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(expenseMileage), "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(expenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.valueOf(expenseTotalCost),
                "Total cost value is incorrect");
        softAssert.assertAll();

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Add an expense by number of car. Positive case", priority = 51)
    public void addExpenseByNumberOfCarPositiveTest(){
        int numberOfCar = 1;
        String brandName = "Fiat";
        String modelName = "Palio";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 1000.99f;
        float expenseTotalCost = 12.01f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        numberOfCar, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDate = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(expenseMileage), "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(expenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.format(Locale.US, "%.2f", expenseTotalCost),
                "Total cost value is incorrect");
        softAssert.assertAll();

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Add an expense at the Garage page test. Positive case", priority = 52)
    public void addExpenseAtGaragePagePositiveTest(){
        String brandName = "BMW";
        String modelName = "Z3";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 10.99f;
        float expenseTotalCost = 10.99f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        guestGaragePage
                .getCarItem(1)
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate,expenseMileage, expenseLitersUsed, expenseTotalCost);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        String actualDate = guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(expenseMileage), "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(expenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.format(Locale.US, "%.2f", expenseTotalCost),
                "Total cost value is incorrect");
        softAssert.assertAll();
        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Add an expense at the Garage page test (several cars in garage). Positive case",
            priority = 53)
    public void addExpenseAtGaragePageSeveralCarsCasePositiveTest(){
        String brandName = "Porsche";
        String modelName = "911";
        Random rg = new Random();
        int expenseMileage = rg.nextInt(101, 150);
        float expenseLitersUsed = 13.01f;
        float expenseTotalCost = 10.99f;

        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        Map<String, String> cars = new LinkedHashMap<>();
        cars.put("Fiat", "Palio");
        cars.put(brandName, modelName);
        cars.put("Ford", "Sierra");
        cars.put("Audi", "A8");
        List<Float> expenseLitersUsedList = List.of(1.99f, expenseLitersUsed, 19.06f, 15.00f);
        List<Float> expenseTotalCostList = List.of(0.01f, expenseTotalCost, 10.01f, 9999.99f);
        Date reportDate = new Date();
        int counter = 0;
        GuestExpensesPage guestExpensesPage = new GuestExpensesPage();
        for (var entry : cars.entrySet()){
            int mileage = rg.nextInt(1, 100);
            guestGaragePage
                    .addCar()
                    .addCarPositiveCase(entry.getKey(), entry.getValue(), mileage);
            if(counter == 1) {
                mileage = expenseMileage;
            }else {
                mileage = rg.nextInt(mileage + 1, 150);
            }
            guestGaragePage
                    .getCarItem(1)
                    .addExpense()
                    .addExpensePositiveCase(
                            entry.getKey(), entry.getValue(), reportDate, mileage,
                            expenseLitersUsedList.get(counter), expenseTotalCostList.get(counter));
            guestExpensesPage
                    .moveToSidebar()
                    .openGarage();
            counter++;
        }
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        ExpenseItem expenseItem = guestGaragePage
                .moveToSidebar()
                .openExpenses()
                .setCarByOrder(2)
                .getExpense(1);
        String actualDate = expenseItem
                .getReportItemValue(DATE);
        String actualMileage = expenseItem
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = expenseItem
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = expenseItem
                .getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(expenseMileage), "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(expenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.format(Locale.US, "%.2f", expenseTotalCost),
                "Total cost value is incorrect");
        softAssert.assertAll();

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Get heads of expenses report test. Positive case", priority = 60)
    public void getHeadOfExpensesReportPositiveTest(){
        String expectedDateHeadName = "Date";
        String expectedMileageHeadName = "Mileage";
        String expectedLitersUsedHeadName = "Liters used";
        String expectedTotalCostHeadName = "Total cost";
        String brandName = "BMW";
        String modelName = "5";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 12.44f;
        float expenseTotalCost = 19.01f;
        Date reportDate = new Date();
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDateHeadName = guestExpensesPage.getReportItemHead(DATE);
        String actualMileageHeadName = guestExpensesPage.getReportItemHead(MILEAGE);
        String actualLitersUsedHeadName = guestExpensesPage.getReportItemHead(LITERS_USED);
        String actualTotalCostHeadName = guestExpensesPage.getReportItemHead(TOTAL_COST);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateHeadName, expectedDateHeadName, "Date head name is incorrect");
        softAssert.assertEquals(actualMileageHeadName, expectedMileageHeadName,
                "Mileage Date head name is incorrect");
        softAssert.assertEquals(actualLitersUsedHeadName, expectedLitersUsedHeadName,
                "Liters used head name is incorrect");
        softAssert.assertEquals(actualTotalCostHeadName, expectedTotalCostHeadName,
                "Total cost head name is incorrect");
        softAssert.assertAll();

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Remove an expense test. Positive case", priority = 70)
    public void removeExpensePositiveTest(){
        String brandName = "Porsche";
        String modelName = "Panamera";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 11.5f;
        float expenseTotalCost = 123.24f;
        Date reportDate = new Date();
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        GuestExpensesPage guestExpensesPage = guestGaragePage
                .moveToSidebar()
                .openExpenses();
        guestExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        guestExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .removeExpense()
                .confirmRemoving();
        String emptyPageMessage = guestExpensesPage.getEmptyPageMessage();
        Assert.assertEquals(emptyPageMessage, EMPTY_PAGE_MESSAGE_IF_CAR_EXIST);

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
    @Test(description = "Edit an expense test. Positive case", priority = 80)
    public void editExpensePositiveTest(){
        String brandName = "Fiat";
        String modelName = "Panda";
        Random rg = new Random();
        int startCarMileage = rg.nextInt(1, 100);
        int initialExpenseMileage = rg.nextInt(startCarMileage + 1, 150);
        float initialExpenseLitersUsed = 774.12f;
        float initialExpenseTotalCost = 15.01f;
        int changedExpenseMileage = rg.nextInt(initialExpenseMileage + 1, 200);
        float changedExpenseLitersUsed = 774.13f;
        float changedExpenseTotalCost = 22.15f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        GuestGaragePage guestGaragePage = openApp()
                .moveToVisitorHeader()
                .openGuestPage();
        guestGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, startCarMileage);
        guestGaragePage
                .getCarItem(1)
                .addExpense()
                .addExpensePositiveCase(brandName, modelName, reportDate,
                        initialExpenseMileage, initialExpenseLitersUsed, initialExpenseTotalCost);
        GuestExpensesPage guestExpensesPage = new GuestExpensesPage();
        guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .editExpense()
                .saveExpensePositiveCase(brandName, modelName, reportDate, changedExpenseMileage,
                        changedExpenseLitersUsed, changedExpenseTotalCost);
        ExpenseItem expense = guestExpensesPage
                .setCarByOrder(1)
                .getExpense(1);
        String actualDate = expense.getReportItemValue(DATE);
        String actualMileage = expense.getReportItemValue(MILEAGE);
        String actualLitersUsed = expense.getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = expense.getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(changedExpenseMileage),
                "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(changedExpenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.format(Locale.US, "%.2f", changedExpenseTotalCost),
                "Total cost value is incorrect");
        softAssert.assertAll();

        guestExpensesPage
                .moveToSidebar()
                .logout();
    }
}