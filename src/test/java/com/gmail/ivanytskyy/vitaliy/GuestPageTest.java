package com.gmail.ivanytskyy.vitaliy;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 13/06/2023
 */
public class GuestPageTest extends BaseTest{
    private static final String EXPECTED_PAGE_TITLE = "Garage";
    private static final String EXPECTED_MODAL_BOX_TITLE = "Add a car";

    @Test(priority = 10)
    public void pageOpenTest(){
        String title = openApp().openGuestPage().getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(priority = 20)
    public void openAddCarModalBox(){
        String title = openApp().openGuestPage().openAddCarModalBox().getTitle();
        Assert.assertEquals(title, EXPECTED_MODAL_BOX_TITLE);
    }
}