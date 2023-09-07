package com.gmail.ivanytskyy.vitaliy.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 07/09/2023
 */
public class SettingsTest extends BaseTest{
    private static final String EXPECTED_ALERT_MESSAGE = "Wrong email or password";
    private static final String EXPECTED_PAGE_TITLE = "Settings";

    @Test(description = "Open settings page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserSidebar()
                .openSettings()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open settings page through dropdown. Positive case.", priority = 20)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserNavigationBar()
                .openUserProfileDropdown()
                .openSettings()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Delete user account (positive case) trough user profile dropdown ", priority = 30)
    public void deleteAccountThroughUserProfileDropdownTest(){
        createUser(tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        deleteUserThroughDropdown(tempUser.getEmail(), tempUser.getPassword());
        String actualAlertMessage = openApp()
                .openSingInBox()
                .setEmail(tempUser.getEmail())
                .setPassword(tempUser.getPassword())
                .clickLoginButtonNegativeCase()
                .getFormErrorMessage();
        Assert.assertEquals(actualAlertMessage, EXPECTED_ALERT_MESSAGE);
    }
    @Test(description = "Delete user account (positive case) trough user sidebar", priority = 40)
    public void deleteAccountThroughUserSidebarTest(){
        createUser(tempUser.getFirstName(), tempUser.getLastName(), tempUser.getEmail(), tempUser.getPassword());
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
        String actualAlertMessage = openApp()
                .openSingInBox()
                .setEmail(tempUser.getEmail())
                .setPassword(tempUser.getPassword())
                .clickLoginButtonNegativeCase()
                .getFormErrorMessage();
        Assert.assertEquals(actualAlertMessage, EXPECTED_ALERT_MESSAGE);
    }
}