package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 07/09/2023
 */
public class SignUpTest extends BaseTest{
    private static final String EXPECTED_MODAL_BOX_TITLE = "Registration";
    private static final String EXPECTED_NAME_TITLE = "Name";
    private static final String EXPECTED_LAST_NAME_TITLE = "Last name";
    private static final String EXPECTED_EMAIL_TITLE = "Email";
    private static final String EXPECTED_PASSWORD_TITLE = "Password";
    private static final String EXPECTED_RE_ENTER_PASSWORD_TITLE = "Re-enter password";
    private static final String EXPECTED_REGISTER_BUTTON_NAME = "Register";
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
        GaragePage garagePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword());
        String actualTitle = garagePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_GARAGE_PAGE_TITLE);
        ProfilePage profilePage = garagePage
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openProfile();
        actualTitle = profilePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE);
        String displayedUserName = profilePage.getProfileName();
        Assert.assertTrue(displayedUserName.contains(tempUser.getFirstName()));
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-34)
    @Test(description = "Check title of name input field", priority = 30)
    public void nameTitleTest(){
        Assert.fail("Bug added to Jira (Edit Issue : U1QM241022-34)");
        String actualTitle = openApp().openSingUpBox().getNameInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_NAME_TITLE);
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-34)
    @Test(description = "Check title of last name input field", priority = 40)
    public void lastNameTitleTest(){
        Assert.fail("Bug added to Jira (Edit Issue : U1QM241022-34)");
        String actualTitle = openApp().openSingUpBox().getLastNameInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_LAST_NAME_TITLE);
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-34)
    @Test(description = "Check title of email input field", priority = 50)
    public void emailTitleTest(){
        String actualTitle = openApp().openSingUpBox().getEmailInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_EMAIL_TITLE, "Bug added to Jira (Edit Issue : U1QM241022-34)");
    }
    @Test(description = "Check title of password input field", priority = 60)
    public void passwordTitleTest(){
        String actualTitle = openApp().openSingUpBox().getPasswordInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PASSWORD_TITLE);
    }
    @Test(description = "Check title of re-enter password input field", priority = 70)
    public void reEnterPasswordTitleTest(){
        String actualTitle = openApp().openSingUpBox().getReEnterPasswordInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_RE_ENTER_PASSWORD_TITLE);
    }
    @Test(description = "Check name of register button", priority = 80)
    public void registerButtonNameTest(){
        String actualName = openApp().openSingUpBox().getRegisterButtonName();
        Assert.assertEquals(actualName, EXPECTED_REGISTER_BUTTON_NAME);
    }
    @Test(description = "Sign up is unsuccessful. Negative case ", priority = 90)
    public void signUpNegativeTest(){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(getUserFirstName(), getUserLastName(), getUserEmail(), getUserPassword(), getUserPassword())
                .getFormErrorMessage();
        Assert.assertEquals(errorMessage, USER_ALREADY_EXISTS_ERROR_MESSAGE);
    }
    @Test(description = "Empty name. Negative case.", priority = 100)
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
    @Test(description = "Empty last name. Negative case.", priority = 101)
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
    @Test(description = "Empty email. Negative case.", priority = 102)
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
    @Test(description = "Empty password. Negative case.", priority = 103)
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
    @Test(description = "Empty re-enter password. Negative case.", priority = 104)
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