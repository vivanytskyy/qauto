package com.gmail.ivanytskyy.vitaliy.api;

import com.github.javafaker.Faker;
import com.gmail.ivanytskyy.vitaliy.api.antities.User;
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
        User user = authController.signIn(credentials.getAuthorizationPermit());
        Assert.assertEquals(user.getStatus(), "ok", "Authorization failed");
        Assert.assertNotNull(user.getData(), "Authorization failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        Assert.assertEquals(usersController.deleteUser(), 200, "User wasn't deleted");
    }
    @Test(description = "Logout test. Positive case", priority = 20)
    public void logoutTest(){
        AuthController authController = new AuthController();
        System.out.println("pass: " + credentials.getPassword());
        authController.signUp(credentials.getRegistrationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        Assert.assertEquals(authController.logout(cookie), 200, "Logout failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        Assert.assertEquals(usersController.deleteUser(), 200, "User wasn't deleted");
    }
    @Test(description = "Password reset test. Positive case", priority = 30)
    public void resetPasswordTest(){
        AuthController authController = new AuthController();
        System.out.println("pass: " + credentials.getPassword());
        authController.signUp(credentials.getRegistrationPermit());
        String cookie = authController.getCookie(credentials.getAuthorizationPermit());
        String newEmail = Faker.instance().internet().emailAddress();
        Assert.assertEquals(
                authController.resetPassword(newEmail, cookie), 200, "Password reset failed");

        UsersController usersController =
                new UsersController(authController.getCookie(credentials.getAuthorizationPermit()));
        Assert.assertEquals(usersController.deleteUser(), 200, "User wasn't deleted");
    }
}