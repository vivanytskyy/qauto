package com.gmail.ivanytskyy.vitaliy.ui.guest;

import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class GuestExpensesTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Fuel expenses";

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
}