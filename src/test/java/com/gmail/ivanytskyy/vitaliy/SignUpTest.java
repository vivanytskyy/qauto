package com.gmail.ivanytskyy.vitaliy;

import com.gmail.ivanytskyy.vitaliy.pages.GaragePage;
import com.gmail.ivanytskyy.vitaliy.pages.ProfilePage;
import com.gmail.ivanytskyy.vitaliy.utils.TestProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
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

    @Test(description = "Opening SignUp modal box", priority = 10)
    public void openSignUpTest(){
        String actualTitle = openApp().openSingUpBox().getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MODAL_BOX_TITLE);
    }
    @Test(description = "Sign up is success ", priority = 20)
    public void signUpSuccessTest(){
        String userFirstName = TestProperties.getInstance().getProperty("user_first_name");
        String userLastName = TestProperties.getInstance().getProperty("user_last_name");
        String userEmail = TestProperties.getInstance().getProperty("user_email");
        String userPassword = TestProperties.getInstance().getProperty("user_password");
        GaragePage garagePage = openApp()
                .openSingUpBox()
                .setName(userFirstName)
                .setLastName(userLastName)
                .setEmail(userEmail)
                .setPassword(userPassword)
                .setReEnterPassword(userPassword)
                .clickRegisterButtonPositiveCase();
        String actualTitle = garagePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_GARAGE_PAGE_TITLE);
        ProfilePage profilePage = garagePage.openUserProfileDropdown().openProfile();
        actualTitle = profilePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE);
        String displayedUserName = profilePage.getProfileName();
        Assert.assertTrue(displayedUserName.contains(userFirstName));
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-34)
    @Test(description = "Check title of name input field", priority = 30)
    public void nameTitleTest(){
        Assert.assertTrue(false, "Bug added to Jira (Edit Issue : U1QM241022-34)");
        String actualTitle = openApp().openSingUpBox().getNameInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_NAME_TITLE);
    }
    //"Bug added to Jira (Edit Issue : U1QM241022-34)
    @Test(description = "Check title of last name input field", priority = 40)
    public void lastNameTitleTest(){
        Assert.assertTrue(false, "Bug added to Jira (Edit Issue : U1QM241022-34)");
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
}