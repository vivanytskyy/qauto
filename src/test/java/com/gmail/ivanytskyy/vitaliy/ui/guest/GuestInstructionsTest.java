package com.gmail.ivanytskyy.vitaliy.ui.guest;

import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import com.gmail.ivanytskyy.vitaliy.ui.dataproviders.InstructionsDataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class GuestInstructionsTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Instructions";

    @Test(description = "Open instructions page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToSidebar()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToHeader()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        String title = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToHeader()
                .openUserProfileDropdown()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Search an instruction. Positive case",
            dataProviderClass = InstructionsDataProviders.class, dataProvider = "nameOfInstructionsProviderPositiveCase",
            priority = 40)
    public void searchInstructionTest(String brandName, String modelName, int instructionIndex, String expectedTitle){
        String actualInstructionTitle = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionIndex);
        Assert.assertEquals(actualInstructionTitle, expectedTitle);
    }
    @Test(description = "Get number of instructions. Positive case",
            dataProviderClass = InstructionsDataProviders.class, dataProvider = "numberOfInstructionsProviderPositiveCase",
            priority = 50)
    public void getNumberOfInstructionsTest(String brandName, String modelName, int expectedNumberOfInstructions){
        int actualNumberOfInstructions = openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getNumberOfInstructions();
        Assert.assertEquals(actualNumberOfInstructions, expectedNumberOfInstructions);
    }
    @Test(description = "Searching an instruction when it doesn't exist. Negative case",
            dataProviderClass = InstructionsDataProviders.class, dataProvider = "invalidInstructionNumbersProviderNegativeCase",
            expectedExceptions = IllegalArgumentException.class, priority = 60)
    public void searchInstructionWhenItDoesNotExistTest(String brandName, String modelName, int instructionNumber){
        openApp()
                .moveToVisitorHeader()
                .openGuestPage()
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionNumber);
    }
}