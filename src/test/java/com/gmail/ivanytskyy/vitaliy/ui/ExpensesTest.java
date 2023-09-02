package com.gmail.ivanytskyy.vitaliy.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/09/2023
 */
public class ExpensesTest extends BaseTest{
    private static final String EXPECTED_PAGE_TITLE = "Fuel expenses";

    @Test(description = "Open fuel expenses page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserSidebar()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open fuel expenses page through dropdown. Positive case.", priority = 20)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .openUserProfileDropdown()
                .openExpenses()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
}