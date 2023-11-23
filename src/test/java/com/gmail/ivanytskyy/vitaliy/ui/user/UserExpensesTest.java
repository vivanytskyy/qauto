package com.gmail.ivanytskyy.vitaliy.ui.user;

import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.ExpenseItem;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.AddExpenseModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.StringConstants.DATE_FORMAT;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.ExpensesReportHeads.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 12/11/2023
 */
public class UserExpensesTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Fuel expenses";
    private static final String EXPECTED_ADD_EXPENSE_MODAL_BOX_TITLE = "Add an expense";
    private static final String EMPTY_PAGE_MESSAGE_IF_CAR_EXIST = "You don’t have any fuel expenses filed in";

    @Test(description = "Open fuel expenses page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open fuel expenses page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open fuel expenses page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openUserProfileDropdown()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Check title of add an expense modal box", priority = 40)
    public void openAddExpenseTest(){
        String brandName = "BMW";
        String modelName = "5";
        int mileage = new Random().nextInt(1, 100);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        AddExpenseModalBox addExpenseModalBox = userExpensesPage
                .addExpense();
        String actualTitle = addExpenseModalBox
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_ADD_EXPENSE_MODAL_BOX_TITLE);

        addExpenseModalBox.closeModalBox();
        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Close add expense modal box", priority = 41)
    public void closeAddExpenseTest(){
        String brandName = "Ford";
        String modelName = "Fusion";
        int mileage = new Random().nextInt(1, 100);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .closeModalBox();
        String actualTitle = userExpensesPage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Cancel add expense modal box", priority = 42)
    public void cancelAddExpenseTest(){
        String brandName = "Porsche";
        String modelName = "911";
        int mileage = new Random().nextInt(1, 100);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .clickCancelButton();
        String actualTitle = userExpensesPage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Add an expense at the Expense page test. Positive case", priority = 50)
    public void addExpenseAtExpensePagePositiveTest(){
        String brandName = "Audi";
        String modelName = "Q7";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 11.5f;
        float expenseTotalCost = 123.24f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDate = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(TOTAL_COST).split(" ")[0];

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDate, expectedReportDate, "Report date is incorrect");
        softAssert.assertEquals(actualMileage, String.valueOf(expenseMileage), "Mileage value is incorrect");
        softAssert.assertEquals(actualLitersUsed, String.valueOf(expenseLitersUsed),
                "Liters used value is incorrect");
        softAssert.assertEquals(actualTotalCost, String.valueOf(expenseTotalCost), "Total cost value is incorrect");
        softAssert.assertAll();

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Add an expense by number of car. Positive case", priority = 51)
    public void addExpenseByNumberOfCarPositiveTest(){
        int numberOfCar = 1;
        String brandName = "BMW";
        String modelName = "5";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 125.77f;
        float expenseTotalCost = 15.40f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        numberOfCar, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDate = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = userExpensesPage
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

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Add an expense at the Garage page test. Positive case", priority = 52)
    public void addExpenseAtGaragePagePositiveTest(){
        String brandName = "Ford";
        String modelName = "Sierra";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 0.5f;
        float expenseTotalCost = 10.00f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        userGaragePage
                .getCarItem(1)
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate,expenseMileage, expenseLitersUsed, expenseTotalCost);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        String actualDate = userExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(DATE);
        String actualMileage = userExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(MILEAGE);
        String actualLitersUsed = userExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .getReportItemValue(LITERS_USED).split("L")[0];
        String actualTotalCost = userExpensesPage
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

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Add an expense at the Garage page test (several cars in garage). Positive case",
            priority = 53)
    public void addExpenseAtGaragePageSeveralCarsCasePositiveTest(){
        String brandName = "Fiat";
        String modelName = "Panda";
        Random rg = new Random();
        int expenseMileage = rg.nextInt(101, 150);
        float expenseLitersUsed = 12.61f;
        float expenseTotalCost = 44.01f;

        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        Map<String, String> cars = new LinkedHashMap<>();
        cars.put("Audi", "TT");
        cars.put("Ford", "Focus");
        cars.put(brandName, modelName);
        cars.put("BMW", "Z3");
        List<Float> expenseLitersUsedList = List.of(0.99f, 16.55f, expenseLitersUsed, 33.05f);
        List<Float> expenseTotalCostList = List.of(77.33f, 0.44f, expenseTotalCost, 2000.01f);
        Date reportDate = new Date();
        int counter = 0;
        UserExpensesPage userExpensesPage = new UserExpensesPage();
        for (var entry : cars.entrySet()){
            int mileage = rg.nextInt(1, 100);
            userGaragePage
                    .addCar()
                    .addCarPositiveCase(entry.getKey(), entry.getValue(), mileage);
            if(counter == 2) {
                mileage = expenseMileage;
            }else {
                mileage = rg.nextInt(mileage + 1, 150);
            }
            userGaragePage
                    .getCarItem(1)
                    .addExpense()
                    .addExpensePositiveCase(
                            entry.getKey(), entry.getValue(), reportDate, mileage,
                            expenseLitersUsedList.get(counter), expenseTotalCostList.get(counter));
            userExpensesPage
                    .moveToSidebar()
                    .openGarage();
            counter++;
        }
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        ExpenseItem expenseItem = userGaragePage
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

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Get heads of expenses report test. Positive case", priority = 60)
    public void getHeadOfExpensesReportPositiveTest(){
        String expectedDateHeadName = "Date";
        String expectedMileageHeadName = "Mileage";
        String expectedLitersUsedHeadName = "Liters used";
        String expectedTotalCostHeadName = "Total cost";
        String brandName = "Audi";
        String modelName = "Q7";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 6.3f;
        float expenseTotalCost = 22.05f;
        Date reportDate = new Date();
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        String actualDateHeadName = userExpensesPage.getReportItemHead(DATE);
        String actualMileageHeadName = userExpensesPage.getReportItemHead(MILEAGE);
        String actualLitersUsedHeadName = userExpensesPage.getReportItemHead(LITERS_USED);
        String actualTotalCostHeadName = userExpensesPage.getReportItemHead(TOTAL_COST);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualDateHeadName, expectedDateHeadName, "Date head name is incorrect");
        softAssert.assertEquals(actualMileageHeadName, expectedMileageHeadName,
                "Mileage Date head name is incorrect");
        softAssert.assertEquals(actualLitersUsedHeadName, expectedLitersUsedHeadName,
                "Liters used head name is incorrect");
        softAssert.assertEquals(actualTotalCostHeadName, expectedTotalCostHeadName,
                "Total cost head name is incorrect");
        softAssert.assertAll();

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Remove an expense test. Positive case", priority = 70)
    public void removeExpensePositiveTest(){
        String brandName = "Fiat";
        String modelName = "Scudo";
        Random rg = new Random();
        int initialMileage = rg.nextInt(1, 100);
        int expenseMileage = rg.nextInt(initialMileage + 1, 150);
        float expenseLitersUsed = 11.5f;
        float expenseTotalCost = 123.24f;
        Date reportDate = new Date();
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, initialMileage);
        UserExpensesPage userExpensesPage = userGaragePage
                .moveToSidebar()
                .openExpenses();
        userExpensesPage
                .addExpense()
                .addExpensePositiveCase(
                        brandName, modelName, reportDate, expenseMileage, expenseLitersUsed, expenseTotalCost);
        userExpensesPage
                .setFirstCarByName(brandName, modelName)
                .getExpense(1)
                .removeExpense()
                .confirmRemoving();
        String emptyPageMessage = userExpensesPage.getEmptyPageMessage();
        Assert.assertEquals(emptyPageMessage, EMPTY_PAGE_MESSAGE_IF_CAR_EXIST);

        userExpensesPage
                .moveToSidebar()
                .logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Edit an expense test. Positive case", priority = 80)
    public void editExpensePositiveTest(){
        String brandName = "Porsche";
        String modelName = "Cayenne";
        Random rg = new Random();
        int startCarMileage = rg.nextInt(1, 100);
        int initialExpenseMileage = rg.nextInt(startCarMileage + 1, 150);
        float initialExpenseLitersUsed = 17.61f;
        float initialExpenseTotalCost = 120.35f;
        int changedExpenseMileage = rg.nextInt(initialExpenseMileage + 1, 200);
        float changedExpenseLitersUsed = 23.11f;
        float changedExpenseTotalCost = 135.01f;
        Date reportDate = new Date();
        String expectedReportDate = new SimpleDateFormat(DATE_FORMAT.getValue()).format(reportDate);
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox().registerPositiveCase(
                        tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, startCarMileage);
        userGaragePage
                .getCarItem(1)
                .addExpense()
                .addExpensePositiveCase(brandName, modelName, reportDate,
                        initialExpenseMileage, initialExpenseLitersUsed, initialExpenseTotalCost);
        UserExpensesPage userExpensesPage = new UserExpensesPage();
        userExpensesPage
                .setCarByOrder(1)
                .getExpense(1)
                .editExpense()
                .saveExpensePositiveCase(brandName, modelName, reportDate, changedExpenseMileage,
                        changedExpenseLitersUsed, changedExpenseTotalCost);
        ExpenseItem expense = userExpensesPage
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

        userExpensesPage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
}