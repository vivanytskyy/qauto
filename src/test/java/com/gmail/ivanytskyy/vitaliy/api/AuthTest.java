package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.antities.request.ResetPassword;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.ResponseStatusSuccess;
import com.gmail.ivanytskyy.vitaliy.api.antities.response.UserData;
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
        System.out.println("pass: " + credentials.getPassword());
        authController.signUp(credentials.getRegistrationPermit());
        UserData user = authController.signIn(credentials.getAuthorizationPermit());
        Assert.assertNotNull(user, "User is null");
        Assert.assertNotNull(user.getStatus(), "Status is null");
        Assert.assertNotNull(user.getData(), "Data is null");
        Assert.assertNotNull(user.getData().getUserId(), "User is is null");
        Assert.assertNotNull(user.getData().getCurrency(), "Currency is null");
        Assert.assertNotNull(user.getData().getDistanceUnits(), "Distance units is null");
        Assert.assertEquals(user.getStatus(), "ok", "Status isn't ok");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        ResponseStatusSuccess response = usersController.deleteUser();
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "User wasn't deleted");
    }
    @Test(description = "Logout test. Positive case", priority = 20)
    public void logoutTest(){
        AuthController authController = new AuthController();
        authController.signUp(credentials.getRegistrationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        ResponseStatusSuccess response = authController.logout(cookie);
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
        ResetPassword resetPassword = new ResetPassword(newEmail);
        ResponseStatusSuccess response = authController.resetPassword(resetPassword, cookie);
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "Password reset failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        response = usersController.deleteUser();
        Assert.assertNotNull(response.getStatus(), "Status is null");
        Assert.assertEquals(response.getStatus(), "ok", "User wasn't deleted");
    }
}