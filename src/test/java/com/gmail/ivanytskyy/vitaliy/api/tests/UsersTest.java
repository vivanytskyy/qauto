package com.gmail.ivanytskyy.vitaliy.api.tests;

import com.gmail.ivanytskyy.vitaliy.api.antities.constants.Currency;
import com.gmail.ivanytskyy.vitaliy.api.antities.constants.DistanceUnits;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.ChangeEmailRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.UserSettingsRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.UserProfileRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.request.users.ChangePasswordRequest;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.common.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.pojos.response.users.*;
import com.gmail.ivanytskyy.vitaliy.api.controllers.AuthController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.UsersController;
import com.gmail.ivanytskyy.vitaliy.utils.TestPropertiesSupplier;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
public class UsersTest extends BaseTest{

    @Test(description = "Get data of current user. Positive case.", priority = 10)
    public void getCurrentUserDataTest(){
        UsersController controller = new UsersController(cookie);
        UserDataResponse user = controller.getCurrentUserData();
        Assert.assertNotNull(user, "User is null");
        Assert.assertNotNull(user.getStatus(), "Status is null");
        Assert.assertEquals(user.getStatus(), "ok", "Status isn't ok");

        UserData data = user.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "User is is null");
        Assert.assertNotNull(data.getCurrency(), "Currency is null");
        Assert.assertNotNull(data.getDistanceUnits(), "Distance units is null");
        Assert.assertNotNull(data.getPhotoFilename(), "PhotoFilename units is null");
    }
    @Test(description = "Get profile of current user. Positive case.", priority = 20)
    public void getUserProfileTest(){
        UsersController controller = new UsersController(cookie);

        UserProfileResponse profile = controller.getUserProfileData();
        Assert.assertNotNull(profile, "User is null");
        Assert.assertNotNull(profile.getStatus(), "Status is null");
        Assert.assertEquals(profile.getStatus(), "ok", "Status isn't ok");

        ProfileData data = profile.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "User is is null");
        Assert.assertNotNull(data.getPhotoFilename(), "PhotoFilename units is null");
        Assert.assertNotNull(data.getName(), "User name is null");
        Assert.assertNotNull(data.getLastName(), "User lastname is null");
        Assert.assertEquals(data.getName(), getDefaultFirstName(), "User name is incorrect");
        Assert.assertEquals(data.getLastName(), getDefaultLastName(), "User lastname is incorrect");
    }
    @Test(description = "Update profile of current user. Positive case.", priority = 30)
    public void editUserProfileTest(){
        UsersController controller = new UsersController(cookie);
        String newName = "newTestName";
        String newLastName = "newTestLastName";
        String newCountry = "newTestCountry";
        String newDataBirth = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
        UserProfileRequest profileChanges = UserProfileRequest
                .builder()
                .name(newName)
                .lastName(newLastName)
                .dateBirth(newDataBirth)
                .country(newCountry)
                .build();

        UserProfileResponse profile = controller.editUserProfile(profileChanges);
        Assert.assertNotNull(profile, "User is null");
        Assert.assertNotNull(profile.getStatus(), "Status is null");
        Assert.assertEquals(profile.getStatus(), "ok", "Status isn't ok");

        ProfileData data = profile.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "User is is null");
        Assert.assertNotNull(data.getPhotoFilename(), "PhotoFilename units is null");
        Assert.assertNotNull(data.getName(), "User name is null");
        Assert.assertNotNull(data.getLastName(), "User lastname is null");
        Assert.assertNotNull(data.getCountry(), "Country name is null");
        Assert.assertNotNull(data.getDateBirth(), "Data of birth is null");

        Assert.assertEquals(data.getName(), newName, "User name is incorrect");
        Assert.assertEquals(data.getLastName(), newLastName, "User lastname is incorrect");
        Assert.assertEquals(data.getCountry(), newCountry, "Country name is incorrect");
        Assert.assertTrue(data.getDateBirth().contains(newDataBirth.substring(0, 10)), "Data of birth is incorrect");
    }
    @Test(description = "Get settings of current user. Positive case.", priority = 40)
    public void getUserSettingsTest(){
        UsersController controller = new UsersController(cookie);

        UserSettingsResponse settings = controller.getUserSettings();
        Assert.assertNotNull(settings, "User is null");
        Assert.assertNotNull(settings.getStatus(), "Status is null");
        Assert.assertEquals(settings.getStatus(), "ok", "Status isn't ok");

        final SettingsData data = settings.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getCurrency(), "Currency is null");
        Assert.assertNotNull(data.getDistanceUnits(), "Distance units is null");
        Assert.assertTrue(Arrays
                        .stream(Currency.values())
                        .map(value -> value.toString().toLowerCase())
                        .anyMatch(currency -> currency.equals(data.getCurrency())));
        Assert.assertTrue(Arrays
                .stream(DistanceUnits.values())
                .map(value -> value.toString().toLowerCase())
                .anyMatch(unit -> unit.equals(data.getDistanceUnits())));
    }
    @Test(description = "Update settings of current user. Positive case.", priority = 50)
    public void editUserSettingsTest(){
        UsersController controller = new UsersController(cookie);
        String newCurrency = Currency.EUR.getValue();
        String newDistanceUnit = DistanceUnits.ML.getValue();
        UserSettingsRequest settingsChanges = UserSettingsRequest
                .builder()
                .currency(newCurrency)
                .distanceUnits(newDistanceUnit)
                .build();

        UserSettingsResponse settings = controller.editUserSettings(settingsChanges);
        Assert.assertNotNull(settings, "User is null");
        Assert.assertNotNull(settings.getStatus(), "Status is null");
        Assert.assertEquals(settings.getStatus(), "ok", "Status isn't ok");

        SettingsData data = settings.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getCurrency(), "Currency is null");
        Assert.assertNotNull(data.getDistanceUnits(), "Distance units is null");

        Assert.assertEquals(data.getCurrency(), newCurrency, "Currency is incorrect");
        Assert.assertEquals(data.getDistanceUnits(), newDistanceUnit, "Distance units is incorrect");
    }
    @Test(description = "Change email of current user. Positive case.", priority = 60)
    public void changeUserEmailTest(){
        UsersController controller = new UsersController(cookie);
        String newEmail = credentials.getEmail();
        String password = TestPropertiesSupplier.getInstance().getProperty("user_password");
        ChangeEmailRequest emailChanges = ChangeEmailRequest
                .builder()
                .email(newEmail)
                .password(password)
                .build();

        ChangeEmailResponse response = controller.changeUserEmail(emailChanges);
        Assert.assertNotNull(response, "Response is null");
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "Status isn't ok");

        ChangeEmailData data = response.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "userId is null");
    }
    @Test(description = "Change password of current user. Positive case.", priority = 70)
    public void changeUserPasswordTest(){
        AuthController authController = new AuthController();
        authController.signUp(credentials.getRegistrationPermit());
        UserDataResponse user = authController.signIn(credentials.getAuthorizationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        String oldPassword = credentials.getPassword();
        String newPassword = TestPropertiesSupplier.getInstance().getProperty("user_password");

        UsersController controller = new UsersController(cookie);
        ChangePasswordRequest passwordChanges = ChangePasswordRequest
                .builder()
                .oldPassword(oldPassword)
                .password(newPassword)
                .repeatPassword(newPassword)
                .build();

        ChangePasswordResponse response = controller.changeUserPassword(passwordChanges);
        Assert.assertNotNull(response, "Response is null");
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "Status isn't ok");

        ChangePasswordData data = response.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "userId is null");
        Assert.assertEquals(data.getUserId(), user.getData().getUserId(), "userIds are different");

        UsersController usersController = new UsersController(cookie);
        StatusResponseSuccess responseSuccess = usersController.deleteUser();
        Assert.assertNotNull(responseSuccess.getStatus(), "Status is null");
        Assert.assertEquals(responseSuccess.getStatus(), "ok", "User wasn't deleted");
    }
}