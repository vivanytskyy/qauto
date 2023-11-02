package com.gmail.ivanytskyy.vitaliy.ui.user;

import com.gmail.ivanytskyy.vitaliy.ui.dataproviders.InstructionsDataProviders;
import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class UserInstructionsTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Instructions";

    @Test(description = "Open instructions page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openInstructions()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open instructions page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
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
        boolean rememberMe = false;
        String actualInstructionTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionIndex);
        Assert.assertEquals(actualInstructionTitle, expectedTitle, "Instruction title is incorrect");
    }
    @Test(description = "Get number of instructions. Positive case",
            dataProviderClass = InstructionsDataProviders.class, dataProvider = "numberOfInstructionsProviderPositiveCase",
            priority = 50)
    public void getNumberOfInstructionsTest(String brandName, String modelName, int expectedNumberOfInstructions){
        boolean rememberMe = false;
        int actualNumberOfInstructions = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getNumberOfInstructions();
        Assert.assertEquals(actualNumberOfInstructions, expectedNumberOfInstructions,
                "Number of instructions is incorrect");
    }
    @Test(description = "Searching an instruction when it doesn't exist. Negative case",
            dataProviderClass = InstructionsDataProviders.class, dataProvider = "invalidInstructionNumbersProviderNegativeCase",
            expectedExceptions = IllegalArgumentException.class, priority = 60)
    public void searchInstructionWhenItDoesNotExistTest(String brandName, String modelName, int instructionNumber){
        boolean rememberMe = false;
        openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionNumber);
    }
}