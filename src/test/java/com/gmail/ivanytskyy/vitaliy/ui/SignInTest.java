package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 27/09/2023
 */
public class SignInTest extends BaseTest {
    private static final String EXPECTED_MODAL_BOX_TITLE = "Log in";
    private static final String EXPECTED_EMAIL_TITLE = "Email";
    private static final String EXPECTED_PASSWORD_TITLE = "Password";
    private static final String EXPECTED_CHECKBOX_TITLE = "Remember me";
    private static final String EXPECTED_LOGIN_BUTTON_NAME = "Login";
    private static final String EXPECTED_GARAGE_PAGE_TITLE = "Garage";
    private static final String EXPECTED_PROFILE_PAGE_TITLE = "Profile";
    private static final String WRONG_EMAIL_OR_PASSWORD_MESSAGE = "Wrong email or password";
    private static final String EXPECTED_REGISTRATION_TITLE = "Registration";
    private static final String EXPECTED_RESTORE_ACCESS_TITLE = "Restore access";
    private static final String EXPECTED_MAIN_PAGE_TITLE = "Do more!";

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
        GaragePage garagePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(
                        getUserEmail(),
                        getUserPassword(),
                        rememberMe);
        String actualTitle = garagePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_GARAGE_PAGE_TITLE);
        ProfilePage profilePage = garagePage
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openProfile();
        actualTitle = profilePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE);
        String displayedUserName = profilePage.getProfileName();
        Assert.assertTrue(displayedUserName.contains(getUserFirstName()));
    }
    @Test(description = "Check title of email input field", priority = 30)
    public void nameTitleTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .getEmailInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_EMAIL_TITLE);
    }
    @Test(description = "Check title of password input field", priority = 40)
    public void passwordTitleTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .getPasswordInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PASSWORD_TITLE);
    }
    @Test(description = "Check title of checkbox", priority = 50)
    public void checkboxTitleTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .getCheckboxTitle();
        Assert.assertEquals(actualTitle, EXPECTED_CHECKBOX_TITLE);
    }
    @Test(description = "Check name of login button", priority = 60)
    public void loginButtonNameTest(){
        String actualName = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .getLoginButtonName();
        Assert.assertEquals(actualName, EXPECTED_LOGIN_BUTTON_NAME);
    }
    @Test(description = "Login is unsuccessful. Negative case ", priority = 70)
    public void signUpNegativeTest(){
        boolean rememberMe = false;
        String errorMessage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginNegativeCase(tempUser.getEmail(), tempUser.getPassword(), rememberMe)
                .getFormErrorMessage();
        Assert.assertEquals(errorMessage, WRONG_EMAIL_OR_PASSWORD_MESSAGE);
    }
    @Test(description = "Opening SignUp modal box", priority = 80)
    public void openRegistrationTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .toRegistration()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_REGISTRATION_TITLE);
    }
    @Test(description = "Opening RestoreAccess modal box", priority = 90)
    public void openRestoreAccessTest(){
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .toRestoreAccess()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_RESTORE_ACCESS_TITLE);
    }
}