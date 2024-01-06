package com.gmail.ivanytskyy.vitaliy.ui.tests;

import com.gmail.ivanytskyy.vitaliy.ui.dataproviders.SignUpDataProviders;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.06
 * @date 06/01/2024
 */
public class SignUpTest extends BaseTest{
    private static final String EXPECTED_MODAL_BOX_TITLE = "Registration";
    private static final String EXPECTED_GARAGE_PAGE_TITLE = "Garage";
    private static final String EXPECTED_PROFILE_PAGE_TITLE = "Profile";
    private static final String USER_ALREADY_EXISTS_ERROR_MESSAGE = "User already exists";
    private static final String EXPECTED_MAIN_PAGE_TITLE = "Do more!";


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
    }
    @Test(description = "Sign up is success ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "registrationDataProviderPositiveCase",
            priority = 21)
    public void signUpPositiveTest(String name, String lastName, String email, String password){
        UserProfilePage userProfilePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(name, lastName, email, password)
                .moveToHeader()
                .openUserProfileDropdown()
                .openProfile();
        String actualTitle = userProfilePage.getPageTitle();
        String displayedUserName = userProfilePage.getProfileName();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, EXPECTED_PROFILE_PAGE_TITLE, "Title is incorrect");
        softAssert.assertTrue(displayedUserName.contains(name), "User first name is incorrect");
        softAssert.assertTrue(displayedUserName.contains(lastName), "User last name is incorrect");
        softAssert.assertAll();
    }
    @Test(description = "Sign up is unsuccessful (user already exists). Negative case ", priority = 30)
    public void signUpNegativeTest(){
        userPreRegistrationByUI(tempUser);
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword(),
                        tempUser.getPassword())
                .getFormErrorMessage();
        Assert.assertEquals(errorMessage, USER_ALREADY_EXISTS_ERROR_MESSAGE);
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Sign up isn't success. First name is incorrect ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "firstNameProviderNegativeCase",
            priority = 41)
    public void signUpIncorrectFirstNameNegativeTest(String name, String lastName, String email,
                                                     String password, String expectedErrorMessage){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(name, lastName, email, password, password)
                .getInvalidNameMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message is incorrect");
    }
    @Test(description = "Sign up isn't success. Last name is incorrect ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "lastNameProviderNegativeCase",
            priority = 42)
    public void signUpIncorrectLastNameNegativeTest(String name, String lastName, String email,
                                                     String password, String expectedErrorMessage){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(name, lastName, email, password, password)
                .getInvalidLastNameMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message is incorrect");
    }
    @Test(description = "Sign up isn't success. Email is incorrect ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "emailProviderNegativeCase",
            priority = 43)
    public void signUpIncorrectEmailNegativeTest(String name, String lastName, String email,
                                                    String password, String expectedErrorMessage){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(name, lastName, email, password, password)
                .getInvalidEmailMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message is incorrect");
    }
    @Test(description = "Sign up isn't success. Password is incorrect ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "passwordProviderNegativeCase",
            priority = 44)
    public void signUpIncorrectPasswordNegativeTest(String name, String lastName, String email,
                                                 String password, String expectedErrorMessage){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(name, lastName, email, password, password)
                .getInvalidPasswordMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message is incorrect");
    }
    @Test(description = "Sign up isn't success. Re-enter password is incorrect ",
            dataProviderClass = SignUpDataProviders.class, dataProvider = "rePasswordProviderNegativeCase",
            priority = 45)
    public void signUpIncorrectRePasswordNegativeTest(String name, String lastName, String email,
                                                    String password, String rePassword, String expectedErrorMessage){
        String errorMessage = openApp()
                .openSingUpBox()
                .registerNegativeCase(name, lastName, email, password, rePassword)
                .getInvalidRepeatPasswordMessage();
        Assert.assertEquals(errorMessage, expectedErrorMessage, "Error message is incorrect");
    }
}