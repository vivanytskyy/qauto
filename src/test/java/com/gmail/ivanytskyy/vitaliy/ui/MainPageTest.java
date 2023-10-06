package com.gmail.ivanytskyy.vitaliy.ui;

import com.gmail.ivanytskyy.vitaliy.ui.utils.Contacts;
import com.gmail.ivanytskyy.vitaliy.ui.utils.ContactsPropertiesSupplier;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 06/10/2023
 */
public class MainPageTest extends BaseTest{
    private static final String COPYRIGHT_TEXT = "© 2021 Hillel IT school";
    private static final String POSTER_TEXT = "Hillel auto developed in Hillel IT school " +
            "for educational purposes of QA courses.";

    @Test(description = "Check the link to About section. Positive case.", priority = 10)
    public void moveToAboutSectionTest(){
        boolean isAboutSectionDisplayed = openApp()
                .moveToVisitorHeader()
                .openAbout()
                .isAboutSectionDisplayed();
        Assert.assertTrue(isAboutSectionDisplayed);
    }
    @Test(description = "Check the link to Contacts section. Positive case.", priority = 20)
    public void moveToContactsSectionTest(){
        boolean isContactsSectionDisplayed = openApp()
                .moveToVisitorHeader()
                .openContacts()
                .isContactsSectionDisplayed();
        Assert.assertTrue(isContactsSectionDisplayed);
    }
    @Test(description = "Check contacts links. Positive case.", priority = 30)
    public void getContactTest(){
        Contacts[] contacts = Contacts.values();
        for (Contacts contact : contacts){
            String expectedLink = ContactsPropertiesSupplier.getInstance().getProperty(contact.toString().toLowerCase());
            String actualLink = openApp()
                    .moveToVisitorHeader()
                    .openContacts()
                    .getContact(contact);
            Assert.assertEquals(actualLink, expectedLink, "Link to " + contact + " is broken");
        }
    }
    @Test(description = "Check copyright text. Positive case.", priority = 40)
    public void copyrightTextTest(){
        String actualText = openApp()
                .moveToFooter()
                .getCopyrightText();
        Assert.assertEquals(actualText, COPYRIGHT_TEXT);
    }
    @Test(description = "Check poster text. Positive case.", priority = 50)
    public void posterTextTest(){
        String actualText = openApp()
                .moveToFooter()
                .getPosterText();
        Assert.assertEquals(actualText, POSTER_TEXT);
    }
}