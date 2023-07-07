package com.gmail.ivanytskyy.vitaliy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 06/07/2023
 */
public class SignInTest extends BaseTest {
    private static final String EXPECTED_TITLE = "Log in";
    @Test(description = "Opening SignIn modal box", priority = 10)
    public void openSignInTest(){
        String actualTitle = openApp().openSingInBox().getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_TITLE);
    }
}