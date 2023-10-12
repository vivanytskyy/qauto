package com.gmail.ivanytskyy.vitaliy.ui;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.ui.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 12/10/2023
 */
public class ProfileTest extends BaseTest{
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
                .moveToUserSidebar()
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
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openProfile()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Edit profile. Positive case.", priority = 30)
    public void editProfileTest(){
        ProfilePage profilePage = openApp()
                .openSingUpBox()
                .registerPositiveCase(
                        tempUser.getFirstName(),
                        tempUser.getLastName(),
                        tempUser.getEmail(),
                        tempUser.getPassword())
                .moveToUserHeader()
                .openUserProfileDropdown()
                .openProfile();
        Faker faker = new Faker();
        String newName = faker.name().firstName();
        String newLastName = faker.name().lastName();
        String country = "Canada";
        Date newBirthday = faker.date().birthday();
        File newPhoto = new File(PATH_TO_PHOTO_IMAGE);

        ProfilePage newProfile = profilePage
                .editProfile()
                .saveProfilePositiveCase(newName, newLastName, country, newBirthday, newPhoto);
        String expectedProfileName = newName + " " + newLastName;
        String dateFormat = "dd.MM.yyyy";
        String expectedBirthday = new SimpleDateFormat(dateFormat).format(newBirthday);

        Assert.assertEquals(newProfile.getProfileName(), expectedProfileName);
        Assert.assertEquals(newProfile.getCountryName(), country);
        Assert.assertEquals(newProfile.getBirthday(), expectedBirthday);
        Assert.assertFalse(newProfile.getPhoto().contains("default-user.png"));

        newProfile.moveToUserSidebar().logout();
        webDriver.manage().deleteAllCookies();
        deleteUserThroughSidebar(tempUser.getEmail(), tempUser.getPassword());
    }
}