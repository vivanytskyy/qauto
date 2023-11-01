package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.06
 * @date 01/11/2023
 */
public class SignInTest extends BaseTest {
    private static final String EXPECTED_MODAL_BOX_TITLE = "Log in";
    private static final String EXPECTED_GARAGE_PAGE_TITLE = "Garage";
    private static final String EXPECTED_PROFILE_PAGE_TITLE = "Profile";
    private static final String WRONG_EMAIL_OR_PASSWORD_MESSAGE = "Wrong email or password";
    private static final String EXPECTED_REGISTRATION_TITLE = "Registration";
    private static final String EXPECTED_RESTORE_ACCESS_TITLE = "Restore access";
    private static final String EXPECTED_MAIN_PAGE_TITLE = "Do more!";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "Email required";
    private static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Password required";

    @Test(description = "Opening SignIn modal box", priority = 10)
    public void openSignInTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MODAL_BOX_TITLE);
    }
    @Test(description = "Close SignIn modal box", priority = 11)
    public void closeSignInTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .closeModalBox()
                .getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MAIN_PAGE_TITLE);
    }
    @Test(description = "Login is success ", priority = 20)
    public void signInPositiveTest(){
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(
                        getUserEmail(),
                        getUserPassword(),
                        rememberMe);
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
        softAssert.assertTrue(displayedUserName.contains(getUserFirstName()), "User name is incorrect");
        softAssert.assertAll();
    }
    @Test(description = "Login is unsuccessful. Negative case ", priority = 30)
    public void signUpNegativeTest(){
        boolean rememberMe = false;
        String errorMessage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginNegativeCase(tempUser.getEmail(), tempUser.getPassword(), rememberMe)
                .getFormErrorMessage();
        Assert.assertEquals(errorMessage, WRONG_EMAIL_OR_PASSWORD_MESSAGE);
    }
    @Test(description = "Opening SignUp modal box", priority = 40)
    public void openRegistrationTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .toRegistration()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_REGISTRATION_TITLE);
    }
    @Test(description = "Opening RestoreAccess modal box", priority = 50)
    public void openRestoreAccessTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .toRestoreAccess()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_RESTORE_ACCESS_TITLE);
    }
    @Test(description = "Empty email. Negative case.", priority = 60)
    public void emailIsEmptyTest(){
        String email = "";
        boolean rememberMe = false;
        String emptyEmailMessage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginNegativeCase(email, tempUser.getPassword(), rememberMe)
                .getInvalidEmailMessage();
        Assert.assertEquals(emptyEmailMessage, EMPTY_EMAIL_ERROR_MESSAGE);
    }
    @Test(description = "Empty password. Negative case.", priority = 61)
    public void passwordIsEmptyTest(){
        String password = "";
        boolean rememberMe = false;
        String emptyPasswordMessage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginNegativeCase(tempUser.getEmail(), password, rememberMe)
                .getInvalidPasswordMessage();
        Assert.assertEquals(emptyPasswordMessage, EMPTY_PASSWORD_ERROR_MESSAGE);
    }
}