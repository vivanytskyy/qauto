package com.gmail.ivanytskyy.vitaliy.ui;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.ui.pages.SettingsPage;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.Currencies;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.DistanceUnits;
import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 17/10/2023
 */
public class SettingsTest extends BaseTest{
    private static final String EXPECTED_ALERT_MESSAGE = "Wrong email or password";
    private static final String EXPECTED_PAGE_TITLE = "Settings";
    private static final String EXPECTED_CURRENCY_SETTINGS_TITLE = "Currency";
    private static final String EXPECTED_DISTANCE_UNIT_SETTINGS_TITLE = "Units of distance";
    private static final String EXPECTED_CHANGE_EMAIL_SETTINGS_TITLE = "Change email";
    private static final String EXPECTED_CHANGE_PASSWORD_SETTINGS_TITLE = "Change password";

    @Test(description = "Open settings page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
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
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToUserHeader()
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
                .moveToVisitorHeader()
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
                .moveToVisitorHeader()
                .openSingInBox()
                .setEmail(tempUser.getEmail())
                .setPassword(tempUser.getPassword())
                .clickLoginButtonNegativeCase()
                .getFormErrorMessage();
        Assert.assertEquals(actualAlertMessage, EXPECTED_ALERT_MESSAGE);
    }
    @Test(description = "Check currency settings title", priority = 50)
    public void checkCurrencySettingsTitleTest(){
        boolean needRemember = false;
        String currencyTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getCurrencyTitle();
        Assert.assertEquals(currencyTitle, EXPECTED_CURRENCY_SETTINGS_TITLE);
    }
    @Test(description = "Check distance unit settings title", priority = 51)
    public void checkDistanceUnitSettingsTitleTest(){
        boolean needRemember = false;
        String distanceUnitTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getDistanceUnitTitle();
        Assert.assertEquals(distanceUnitTitle, EXPECTED_DISTANCE_UNIT_SETTINGS_TITLE);
    }
    @Test(description = "Check change email settings title", priority = 52)
    public void checkChangeEmailSettingsTitleTest(){
        boolean needRemember = false;
        String changeEmailTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getChangeEmailTitle();
        Assert.assertEquals(changeEmailTitle, EXPECTED_CHANGE_EMAIL_SETTINGS_TITLE);
    }
    @Test(description = "Check change password settings title")
    public void checkChangePasswordSettingsTitleTest(){
        boolean needRemember = false;
        String changePasswordTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getChangePasswordTitle();
        Assert.assertEquals(changePasswordTitle, EXPECTED_CHANGE_PASSWORD_SETTINGS_TITLE);
    }
    @Test(description = "Check current currency item", priority = 60)
    public void checkCurrentCurrencyTest(){
        boolean needRemember = false;
        Currencies currentCurrency = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getCurrentCurrency();
        Assert.assertEquals(currentCurrency, Currencies.USD);
    }
    @Test(description = "Check current distance unit item", priority = 61)
    public void checkCurrentDistanceUnitTest(){
        boolean needRemember = false;
        DistanceUnits currentDistanceUnit = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .getCurrentDistanceUnit();
        Assert.assertEquals(currentDistanceUnit, DistanceUnits.KM);
    }
    @Test(description = "Set currency. Positive case", priority = 70)
    public void setCurrencyTest(){
        boolean needRemember = false;
        Currencies expectedCurrency = Currencies.EUR;
        SettingsPage settingsPage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .setCurrency(expectedCurrency);
        Currencies currentCurrency = settingsPage.getCurrentCurrency();
        Assert.assertEquals(currentCurrency, expectedCurrency);
    }
    @Test(description = "Set distance unit. Positive case", priority = 71)
    public void setDistanceUnitTest(){
        boolean needRemember = false;
        DistanceUnits expectedUnit = DistanceUnits.ML;
        SettingsPage settingsPage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), needRemember)
                .moveToUserSidebar()
                .openSettings()
                .setDistanceUnit(expectedUnit);
        DistanceUnits currentUnit = settingsPage.getCurrentDistanceUnit();
        Assert.assertEquals(currentUnit, expectedUnit);
    }
    @Test(description = "Change email. Positive case.", priority = 80)
    public void changeEmailTest(){
        Faker faker = new Faker();
        String newEmail = faker.internet().emailAddress();
        boolean rememberMe = false;
        openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword())
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openSettings()
                .changeEmailPositiveCase(newEmail, tempUser.getPassword())
                .moveToUserSidebar()
                .logout();
        webDriver.manage().deleteAllCookies();

        SettingsPage settingsPage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(newEmail, tempUser.getPassword(), rememberMe)
                .moveToUserSidebar()
                .openSettings();
        Assert.assertEquals(settingsPage.getPageTitle(), EXPECTED_PAGE_TITLE);
        settingsPage.moveToUserSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(newEmail, tempUser.getPassword());
    }
    @Test(description = "Change password. Positive case.", priority = 81)
    public void changePasswordTest(){
        String newPassword = new PasswordGenerateService.Builder()
                .useDigits(true)
                .useUpperCaseLetters(true)
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(8, 15);
        boolean rememberMe = false;
        openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword())
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openSettings()
                .changePasswordPositiveCase(tempUser.getPassword(), newPassword)
                .moveToUserSidebar()
                .logout();
        webDriver.manage().deleteAllCookies();

        SettingsPage settingsPage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(tempUser.getEmail(), newPassword, rememberMe)
                .moveToUserSidebar()
                .openSettings();
        Assert.assertEquals(settingsPage.getPageTitle(), EXPECTED_PAGE_TITLE);
        settingsPage.moveToUserSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), newPassword);
    }
}