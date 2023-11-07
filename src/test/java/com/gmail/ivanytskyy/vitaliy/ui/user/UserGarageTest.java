package com.gmail.ivanytskyy.vitaliy.ui.user;

import com.gmail.ivanytskyy.vitaliy.ui.BaseTest;
import com.gmail.ivanytskyy.vitaliy.ui.pages.user.UserGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.items.CarItem;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.EditCarModalBox;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Date;
import java.util.Random;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class UserGarageTest extends BaseTest {
    private static final String EXPECTED_PAGE_TITLE = "Garage";
    private static final String EXPECTED_BRAND_TITLE = "Brand";
    private static final String EXPECTED_MODEL_TITLE = "Model";
    private static final String EXPECTED_MILEAGE_TITLE = "Mileage";
    private static final String EXPECTED_CANCEL_BUTTON_NAME = "Cancel";
    private static final String EXPECTED_ADD_CAR_BUTTON_NAME = "Add";
    private static final String EXPECTED_ADD_CAR_MODAL_BOX_TITLE = "Add a car";
    private static final String EXPECTED_EDIT_CAR_MODAL_BOX_TITLE = "Edit a car";

    @Test(description = "Open garage page through sidebar. Positive case.", priority = 10)
    public void openPageThroughSidebarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToSidebar()
                .openGarage()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open garage page through navigation bar. Positive case.", priority = 20)
    public void openPageThroughNavigationBarTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openGarage()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Open garage page through dropdown. Positive case.", priority = 30)
    public void openPageThroughDropdownTest(){
        boolean rememberMe = false;
        String title = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .moveToHeader()
                .openUserProfileDropdown()
                .openGarage()
                .getPageTitle();
        Assert.assertEquals(title, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Check title of add car modal box", priority = 40)
    public void openAddCarTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_ADD_CAR_MODAL_BOX_TITLE);
    }
    @Test(description = "Close add car modal box", priority = 41)
    public void closeAddCarTest(){
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .closeModalBox();
        String actualTitle = userGaragePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Cancel add car modal box", priority = 42)
    public void cancelAddCarTest(){
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .clickCancelButton();
        String actualTitle = userGaragePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);
    }
    @Test(description = "Check title of brand select", priority = 50)
    public void brandTitleTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getBrandSelectTitle();
        Assert.assertEquals(actualTitle, EXPECTED_BRAND_TITLE);
    }
    @Test(description = "Check title of model select", priority = 60)
    public void modelTitleTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getModelSelectTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MODEL_TITLE);
    }
    @Test(description = "Check title of mileage input field", priority = 70)
    public void mileageTitleTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getMileageInputFieldTitle();
        Assert.assertEquals(actualTitle, EXPECTED_MILEAGE_TITLE);
    }
    @Test(description = "Check name of cancel button", priority = 80)
    public void cancelButtonNameTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getCancelButtonName();
        Assert.assertEquals(actualTitle, EXPECTED_CANCEL_BUTTON_NAME);
    }
    @Test(description = "Check name of add car button", priority = 90)
    public void addCarButtonNameTest(){
        boolean rememberMe = false;
        String actualTitle = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe)
                .addCar()
                .getSaveCarButtonName();
        Assert.assertEquals(actualTitle, EXPECTED_ADD_CAR_BUTTON_NAME);
    }
    @Test(description = "Add a car. Positive case.", priority = 100)
    public void addCarByIdTest(){
        int brandId = 0;
        int modelId = 0;
        int mileage = new Random().nextInt(1, 100);
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandId, modelId, mileage);
        CarItem carItem = userGaragePage.getCarItem(1);
        int actualMileage = carItem.getCurrentMileage();
        String carItemTitle = carItem.getItemTitle();
        String actualBrandName = carItemTitle.split(" ")[0];
        String actualModelName = carItemTitle.split(" ")[1];
        carItem.editCar().removeCar().confirmRemoving();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBrandName, "Audi", "Brand name is incorrect");
        softAssert.assertEquals(actualModelName, "TT", "Model name is incorrect");
        softAssert.assertEquals(actualMileage, mileage, "Mileage is incorrect");
        softAssert.assertAll();
    }
    @Test(description = "Add a car by brand and model names. Positive case.", priority = 101)
    public void addCarByBrandAndModelNamesTest(){
        String brandName = "Ford";
        String modelName = "Focus";
        int mileage = new Random().nextInt(1, 100);
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandName, modelName, mileage);
        CarItem carItem = userGaragePage.getCarItem(1);
        int actualMileage = carItem.getCurrentMileage();
        String carItemTitle = carItem.getItemTitle();
        String actualBrandName = carItemTitle.split(" ")[0];
        String actualModelName = carItemTitle.split(" ")[1];
        carItem.editCar().removeCar().confirmRemoving();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBrandName, brandName, "Brand name is incorrect");
        softAssert.assertEquals(actualModelName, modelName, "Model name is incorrect");
        softAssert.assertEquals(actualMileage, mileage, "Mileage is incorrect");
        softAssert.assertAll();
    }
    @Test(description = "Open edit car modal box", priority = 110)
    public void openEditCarTest(){
        int brandId = 0;
        int modelId = 0;
        int mileage = new Random().nextInt(1, 100);
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandId, modelId, mileage);
        EditCarModalBox editCarModalBox = userGaragePage
                .getCarItem(1)
                .editCar();
        String actualTitle = editCarModalBox.getTitle();
        Assert.assertEquals(actualTitle, EXPECTED_EDIT_CAR_MODAL_BOX_TITLE);
        editCarModalBox.removeCar().confirmRemoving();
    }
    @Test(description = "Close edit car modal box", priority = 111)
    public void closeEditCarTest(){
        int brandId = 0;
        int modelId = 0;
        int mileage = new Random().nextInt(1, 100);
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandId, modelId, mileage);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .closeModalBox();
        String actualTitle = userGaragePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .removeCar()
                .confirmRemoving();
    }
    @Test(description = "Cancel edit car modal box", priority = 112)
    public void cancelEditCarTest(){
        int brandId = 0;
        int modelId = 0;
        int mileage = new Random().nextInt(1, 100);
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(brandId, modelId, mileage);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .clickCancelButton();
        String actualTitle = userGaragePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_PAGE_TITLE);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .removeCar()
                .confirmRemoving();
    }
    @Test(description = "Add a car. Positive case.", priority = 120)
    public void editCarByIdTest(){
        int initialBrandId = 0;
        int initialModelId = 0;
        int initialMileage = new Random().nextInt(1, 100);
        int newBrandId = 1;
        int newModelId = 1;
        int newMileage = new Random().nextInt(initialMileage, 100 + initialMileage);
        Date newCreationDate = new Date();
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(initialBrandId, initialModelId, initialMileage);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .saveCarPositiveCase(newBrandId, newModelId, newMileage, newCreationDate);
        CarItem carItem = userGaragePage.getCarItem(1);
        String carItemTitle = carItem.getItemTitle();
        String actualBrandName = carItemTitle.split(" ")[0];
        String actualModelName = carItemTitle.split(" ")[1];
        int actualMileage = carItem.getCurrentMileage();
        carItem.editCar().removeCar().confirmRemoving();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBrandName, "BMW", "Brand name is incorrect");
        softAssert.assertEquals(actualModelName, "5", "Model name is incorrect");
        softAssert.assertEquals(actualMileage, newMileage, "Mileage is incorrect");
        softAssert.assertAll();
    }
    @Test(description = "Add a car by brand and model names. Positive case.", priority = 121)
    public void editCarByBrandAndModelNamesTest(){
        String initialBrandName = "Audi";
        String initialModelName = "TT";
        int initialMileage = new Random().nextInt(1, 100);
        String newBrandName = "BMW";
        String newModelName = "5";
        int newMileage = new Random().nextInt(initialMileage, 100 + initialMileage);
        Date newCreationDate = new Date();
        boolean rememberMe = false;
        UserGaragePage userGaragePage = openApp()
                .moveToVisitorHeader()
                .openSingInBox()
                .loginPositiveCase(getUserEmail(), getUserPassword(), rememberMe);
        userGaragePage
                .addCar()
                .addCarPositiveCase(initialBrandName, initialModelName, initialMileage);
        userGaragePage
                .getCarItem(1)
                .editCar()
                .saveCarPositiveCase(newBrandName, newModelName, newMileage, newCreationDate);
        CarItem carItem = userGaragePage.getCarItem(1);
        String carItemTitle = carItem.getItemTitle();
        String actualBrandName = carItemTitle.split(" ")[0];
        String actualModelName = carItemTitle.split(" ")[1];
        int actualMileage = carItem.getCurrentMileage();
        carItem.editCar().removeCar().confirmRemoving();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBrandName, newBrandName, "Brand name is incorrect");
        softAssert.assertEquals(actualModelName, newModelName, "Model name is incorrect");
        softAssert.assertEquals(actualMileage, newMileage, "Mileage is incorrect");
        softAssert.assertAll();
    }
}