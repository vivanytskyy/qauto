package com.gmail.ivanytskyy.vitaliy.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 07/09/2023
 */
public class InstructionsTest extends BaseTest{
    private static final String EXPECTED_PAGE_TITLE = "Instructions";

    @Test(description = "Open instructions page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserSidebar()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserNavigationBar()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserNavigationBar()
                .openUserProfileDropdown()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
}