package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.pojos.request.ResetPasswordRequest;
import com.gmail.ivanytskyy.vitaliy.api.pojos.response.UserData;
import com.gmail.ivanytskyy.vitaliy.api.pojos.response.StatusResponseSuccess;
import com.gmail.ivanytskyy.vitaliy.api.pojos.response.UserDataResponse;
import com.gmail.ivanytskyy.vitaliy.api.controllers.AuthController;
import com.gmail.ivanytskyy.vitaliy.api.controllers.UsersController;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 09/08/2023
 */
public class AuthTest extends BaseTest{

    @Test(description = "Authorization test. Positive case.", priority = 10)
    public void authorizationTest(){
        AuthController authController = new AuthController();
        authController.signUp(credentials.getRegistrationPermit());
        UserDataResponse user = authController.signIn(credentials.getAuthorizationPermit());
        Assert.assertNotNull(user, "User is null");
        Assert.assertNotNull(user.getStatus(), "Status is null");
        Assert.assertEquals(user.getStatus(), "ok", "Status isn't ok");

        UserData data = user.getData();
        Assert.assertNotNull(data, "Data is null");
        Assert.assertNotNull(data.getUserId(), "User is is null");
        Assert.assertNotNull(data.getCurrency(), "Currency is null");
        Assert.assertNotNull(data.getDistanceUnits(), "Distance units is null");
        Assert.assertNotNull(data.getPhotoFilename(), "PhotoFilename units is null");


        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        StatusResponseSuccess response = usersController.deleteUser();
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "User wasn't deleted");
    }
    @Test(description = "Logout test. Positive case", priority = 20)
    public void logoutTest(){
        AuthController authController = new AuthController();
        authController.signUp(credentials.getRegistrationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        StatusResponseSuccess response = authController.logout(cookie);
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "Logout failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        response = usersController.deleteUser();
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "User wasn't deleted");
    }
    @Test(description = "Password reset test. Positive case", priority = 30)
    public void resetPasswordTest(){
        AuthController authController = new AuthController();
        authController.signUp(credentials.getRegistrationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        String newEmail = Faker.instance().internet().emailAddress();
        ResetPasswordRequest resetPassword = new ResetPasswordRequest(newEmail);
        StatusResponseSuccess response = authController.resetPassword(resetPassword, cookie);
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "Password reset failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        response = usersController.deleteUser();
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "User wasn't deleted");
    }
}