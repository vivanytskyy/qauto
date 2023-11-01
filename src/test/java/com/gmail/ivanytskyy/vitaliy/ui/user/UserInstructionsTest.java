package com.gmail.ivanytskyy.vitaliy.ui.user;

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
    @Test(description = "Search an instruction. Positive case", priority = 40)
    public void searchInstructionTest(){
        boolean rememberMe = false;
        String brandName = "BMW";
        String modelName = "X5";
        int instructionIndex = 3;
        String expectedInstructionTitle = "Front coil springs on BMW X5";
        String actualInstructionTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionIndex);
        Assert.assertEquals(actualInstructionTitle, expectedInstructionTitle);
    }
    @Test(description = "Search an instruction with pagination. Positive case", priority = 41)
    public void searchInstructionWithPaginationTest(){
        boolean rememberMe = false;
        String brandName = "Ford";
        String modelName = "Fiesta";
        int instructionNumber = 12;
        String expectedInstructionTitle = "Rear suspension strut on Ford Fiesta";
        String actualInstructionTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionNumber);
        Assert.assertEquals(actualInstructionTitle, expectedInstructionTitle);
    }
    @Test(description = "Searching an instruction when it doesn't exist. Negative case",
            expectedExceptions = IllegalArgumentException.class, priority = 42)
    public void searchInstructionWhenItDoesNotExistTest(){
        boolean rememberMe = false;
        String brandName = "BMW";
        String modelName = "Z3";
        int instructionNumber = 1;
        openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getInstructionTitle(instructionNumber);
    }
    @Test(description = "Get number of instructions. Positive case", priority = 50)
    public void getNumberOfInstructionsTest(){
        boolean rememberMe = false;
        String brandName = "Fiat";
        String modelName = "Scudo";
        int expectedNumberOfInstructions = 6;
        int actualNumberOfInstructions = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getNumberOfInstructions();
        Assert.assertEquals(actualNumberOfInstructions, expectedNumberOfInstructions);
    }
    @Test(description = "Get number of instructions with pagination. Positive case", priority = 51)
    public void getNumberOfInstructionsWithPaginationTest(){
        boolean rememberMe = false;
        String brandName = "Audi";
        String modelName = "A6";
        int expectedNumberOfInstructions = 13;
        int actualNumberOfInstructions = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getNumberOfInstructions();
        Assert.assertEquals(actualNumberOfInstructions, expectedNumberOfInstructions);
    }
    @Test(description = "Get number of instructions when they don't exist. Positive case", priority = 52)
    public void getNumberOfInstructionsWhenTheyDoNotExistTest(){
        boolean rememberMe = false;
        String brandName = "BMW";
        String modelName = "Z3";
        int expectedNumberOfInstructions = 0;
        int actualNumberOfInstructions = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openInstructions()
                .searchInstructions(brandName, modelName)
                .getNumberOfInstructions();
        Assert.assertEquals(actualNumberOfInstructions, expectedNumberOfInstructions);
    }
}