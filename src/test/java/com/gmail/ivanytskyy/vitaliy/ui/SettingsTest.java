package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 11/07/2023
 */
public class SettingsTest extends BaseTest{
    private static final String EXPECTED_ALERT_MESSAGE = "Wrong email or password";
    private static final String USER_FIRST_NAME;
    private static final String USER_LAST_NAME;
    private static final String USER_EMAIL;
    private static final String USER_PASSWORD;
    static {
        USER_FIRST_NAME = TestPropertiesSupplier.getInstance().getProperty("new_user_first_name");
        USER_LAST_NAME = TestPropertiesSupplier.getInstance().getProperty("new_user_last_name");
        USER_EMAIL = TestPropertiesSupplier.getInstance().getProperty("new_user_email");
        USER_PASSWORD = TestPropertiesSupplier.getInstance().getProperty("new_user_password");
    }

    @Test(description = "Delete user account (positive case) trough user profile dropdown ", priority = 10)
    public void deleteAccountThroughUserProfileDropdownTest(){
        createUser();
        deleteUserThroughDropdown();
        String actualAlertMessage = openApp()
                .openSingInBox()
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .clickLoginButtonNegativeCase()
                .getAlertMessage();
        Assert.assertEquals(actualAlertMessage, EXPECTED_ALERT_MESSAGE);
    }
    @Test(description = "Delete user account (positive case) trough user sidebar", priority = 20)
    public void deleteAccountThroughUserSidebarTest(){
        createUser();
        deleteUserThroughSidebar();
        String actualAlertMessage = openApp()
                .openSingInBox()
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .clickLoginButtonNegativeCase()
                .getAlertMessage();
        Assert.assertEquals(actualAlertMessage, EXPECTED_ALERT_MESSAGE);
    }
    private void createUser(){
        openApp()
                .openSingUpBox()
                .setName(USER_FIRST_NAME)
                .setLastName(USER_LAST_NAME)
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .setReEnterPassword(USER_PASSWORD)
                .clickRegisterButtonPositiveCase()
                .moveToUserSidebar()
                .logout();
    }
    private void deleteUserThroughSidebar(){
        openApp()
                .openSingInBox()
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .clickLoginButtonPositiveCase()
                .moveToUserSidebar()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
    private void deleteUserThroughDropdown(){
        openApp()
                .openSingInBox()
                .setEmail(USER_EMAIL)
                .setPassword(USER_PASSWORD)
                .clickLoginButtonPositiveCase()
                .openUserProfileDropdown()
                .openSettings()
                .removeAccount()
                .clickRemove();
        webDriver.manage().deleteAllCookies();
    }
}