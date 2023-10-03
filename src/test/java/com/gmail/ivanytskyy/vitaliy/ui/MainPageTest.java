package com.gmail.ivanytskyy.vitaliy.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/10/2023
 */
public class MainPageTest extends BaseTest{

    @Test(description = "Check the link to About section. Positive case", priority = 10)
    public void moveToAboutSectionTest(){
        boolean isAboutSectionDisplayed = openApp()
                .moveToVisitorHeader()
                .openAbout()
                .isAboutSectionDisplayed();
        Assert.assertTrue(isAboutSectionDisplayed);
    }
    @Test(description = "Check the link to Contacts section. Positive case", priority = 20)
    public void moveToContactsSectionTest(){
        boolean isContactsSectionDisplayed = openApp()
                .moveToVisitorHeader()
                .openContacts()
                .isContactsSectionDisplayed();
        Assert.assertTrue(isContactsSectionDisplayed);
    }
}