package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.dataproviders.SignUpDataProvider;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.04
 * @date 03/11/2023
 */
public class SignUpTest extends BaseTest{
    private static final String EXPECTED_MODAL_BOX_TITLE = "Registration";
    private static final String EXPECTED_GARAGE_PAGE_TITLE = "Garage";
    private static final String EXPECTED_PROFILE_PAGE_TITLE = "Profile";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User already exists";
    private static final String EXPECTED_MAIN_PAGE_TITLE = "Do more!";
    private static final String EMPTY_NAME_ERROR_MESSAGE = "Name required";
    private static final String EMPTY_LAST_NAME_ERROR_MESSAGE = "Last name required";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "Email required";
    private static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Password required";
    private static final String EMPTY_REENTER_PASSWORD_ERROR_MESSAGE = "Re-enter password required";


    @Test(description = "Opening SignUp modal box", priority = 10)
    public void openSignUpTest(){
        String actualTitle = openApp().openSingUpBox().getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MODAL_BOX_TITLE);
    }
    @Test(description = "Close SignUp modal box", priority = 11)
    public void closeSignUpTest(){
        String actualTitle = openApp().openSingUpBox().closeModalBox().getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MAIN_PAGE_TITLE);
    }
    @Test(description = "Sign up is success ", priority = 20)
    public void signUpPositiveTest(){
        UserGaragePage userGaragePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword());
        String actualTitle = userGaragePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_GARAGE_PAGE_TITLE);
        UserProfilePage userProfilePage = userGaragePage
                .moveToHeader()
                .openUserProfileDropdown()
                .openProfile();
        actualTitle = userProfilePage.getPageTitle();
        String displayedUserName = userProfilePage.getProfileName();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE, "Title is incorrect");
        softAssert.assertTrue(displayedUserName.contains(tempUser.getFirstName()), "User name is incorrect");
        softAssert.assertAll();

        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Sign up is success ",
            dataProviderClass = SignUpDataProvider.class, dataProvider = "registrationDataProviderPositiveCase",
            priority = 21)
    public void signUpPositiveTest(String name, String lastName, String email, String password){
        UserProfilePage userProfilePage;
        String actualTitle;
        String displayedUserName;
        try{
            userProfilePage = openApp()
                    .openSingUpBox()
                    .registerPositiveCase(name, lastName, email, password)
                    .moveToHeader()
                    .openUserProfileDropdown()
                    .openProfile();
            actualTitle = userProfilePage.getPageTitle();
            displayedUserName = userProfilePage.getProfileName();
        }catch (RuntimeException e){
            e.printStackTrace();
            userProfilePage= openApp()
                    .moveToVisitorHeader()
                    .openSingInBox()
                    .loginPositiveCase(email, password, false)
                    .moveToSidebar()
                    .openSettings()
                    .removeAccount()
                    .clickRemove()
                    .openSingUpBox()
                    .registerPositiveCase(name, lastName, email, password)
                    .moveToHeader()
                    .openUserProfileDropdown()
                    .openProfile();
            actualTitle = userProfilePage.getPageTitle();
            displayedUserName = userProfilePage.getProfileName();
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE, "Title is incorrect");
        softAssert.assertTrue(displayedUserName.contains(name), "User first name is incorrect");
        softAssert.assertTrue(displayedUserName.contains(lastName), "User last name is incorrect");
        softAssert.assertAll();

        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(email, password);
    }
    @Test(description = "Sign up is unsuccessful. Negative case ", priority = 30)
    public void signUpNegativeTest(){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(getUserFirstName(), getUserLastName(), getUserEmail(), getUserPassword(), getUserPassword())
                .getFormErrorMessage();
        Assert.assertEquals(errorMessage, USER_ALREADY_EXISTS_ERROR_MESSAGE);
    }
    @Test(description = "Empty name. Negative case.", priority = 40)
    public void nameIsEmptyTest(){
        String emptyNameMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(
                        "",
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword(),
                        tempUser.getPassword())
                .getInvalidNameMessage();
        Assert.assertEquals(emptyNameMessage, EMPTY_NAME_ERROR_MESSAGE);
    }
    @Test(description = "Empty last name. Negative case.", priority = 41)
    public void lastNameIsEmptyTest(){
        String emptyLastNameMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(
                        tempUser.getFirstName(),
                        "",
                        tempUser.getEmail(),
                        tempUser.getPassword(),
                        tempUser.getPassword())
                .getInvalidLastNameMessage();
        Assert.assertEquals(emptyLastNameMessage, EMPTY_LAST_NAME_ERROR_MESSAGE);
    }
    @Test(description = "Empty email. Negative case.", priority = 42)
    public void emailIsEmptyTest(){
        String emptyEmailMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        "",
                        tempUser.getPassword(),
                        tempUser.getPassword())
                .getInvalidEmailMessage();
        Assert.assertEquals(emptyEmailMessage, EMPTY_EMAIL_ERROR_MESSAGE);
    }
    @Test(description = "Empty password. Negative case.", priority = 43)
    public void passwordIsEmptyTest(){
        String emptyPasswordMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        "",
                        tempUser.getPassword())
                .getInvalidPasswordMessage();
        Assert.assertEquals(emptyPasswordMessage, EMPTY_PASSWORD_ERROR_MESSAGE);
    }
    @Test(description = "Empty re-enter password. Negative case.", priority = 44)
    public void reEnterPasswordIsEmptyTest(){
        String emptyReEnterPasswordMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword(),
                        "")
                .getInvalidRepeatPasswordMessage();
        Assert.assertEquals(emptyReEnterPasswordMessage, EMPTY_REENTER_PASSWORD_ERROR_MESSAGE);
    }
}