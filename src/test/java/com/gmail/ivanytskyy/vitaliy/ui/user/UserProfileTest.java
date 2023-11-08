package com.gmail.ivanytskyy.vitaliy.ui.user;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import com.gmail.ivanytskyy.vitaliy.ui.dataproviders.ProfileDataProviders;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.StringConstants.*;
import static com.gmail.ivanytskyy.vitaliy.utils.TestDataHandlingService.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class UserProfileTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Profile";
    private static final String PATH_TO_PHOTO_IMAGE;
    static {
        PATH_TO_PHOTO_IMAGE = "src" + File.separator
                + "test" + File.separator
                + "resources" + File.separator + "bulldog.jpg";
    }

    @Test(description = "Open profile page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openProfile()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open profile page through dropdown. Positive case.", priority = 20)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openUserProfileDropdown()
                .openProfile()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Edit profile. Positive case.", priority = 30)
    public void editProfileTest(){
        UserProfilePage userProfilePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword())
                .moveToHeader()
                .openUserProfileDropdown()
                .openProfile();
        Faker faker = new Faker();
        String newName = faker.name().firstName().replace("'", "");
        String newLastName = faker.name().lastName().replace("'", "");
        String country = "Canada";
        Date newBirthday = faker.date().birthday();
        File newPhoto = new File(PATH_TO_PHOTO_IMAGE);

        userProfilePage
                .editProfile()
                .saveProfilePositiveCase(newName, newLastName, country, newBirthday, newPhoto);
        String expectedProfileName = newName + " " + newLastName;
        String expectedBirthday = new SimpleDateFormat(DATE_FORMAT.getValue()).format(newBirthday);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userProfilePage.getProfileName(), expectedProfileName,
                "Profile name is incorrect");
        softAssert.assertEquals(userProfilePage.getCountryName(), country,
                "Country is incorrect");
        softAssert.assertEquals(userProfilePage.getBirthday(), expectedBirthday,
                "Birthday is incorrect");
        softAssert.assertFalse(userProfilePage.getPhoto().contains("default-user.png"),
                "File name is incorrect");
        softAssert.assertAll();

        userProfilePage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
    @Test(description = "Edit profile. Positive case.",
            dataProviderClass = ProfileDataProviders.class, dataProvider = "editProfileProviderPositiveCase",
            priority = 30)
    public void editProfileTest(String newName, String newLastName, String country, String birthday){
        UserProfilePage userProfilePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword())
                .moveToHeader()
                .openUserProfileDropdown()
                .openProfile();
        File newPhoto = new File(PATH_TO_PHOTO_IMAGE);

        userProfilePage
                .editProfile()
                .saveProfilePositiveCase(newName, newLastName, country, getDateAsObject(birthday), newPhoto);
        String expectedProfileName = newName + " " + newLastName;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userProfilePage.getProfileName(), expectedProfileName,
                "Profile name is incorrect");
        softAssert.assertEquals(userProfilePage.getCountryName(), country,
                "Country is incorrect");
        softAssert.assertEquals(userProfilePage.getBirthday(), birthday,
                "Birthday is incorrect");
        softAssert.assertFalse(userProfilePage.getPhoto().contains("default-user.png"),
                "File name is incorrect");
        softAssert.assertAll();

        userProfilePage.moveToSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
}