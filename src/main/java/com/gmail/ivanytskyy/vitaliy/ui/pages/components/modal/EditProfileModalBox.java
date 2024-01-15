package com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.units.StringConstants.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 02/11/2023
 */
public class EditProfileModalBox extends ModalBox{
    @FindBy(css = "[for='editProfileName']")
    private WebElement nameInputFieldTitle;
    @FindBy(css = "[for='editProfileLastName']")
    private WebElement lastNameInputFieldTitle;
    @FindBy(css = "[for='editProfileCountry']")
    private WebElement countryInputFieldTitle;
    @FindBy(css = "[for='editProfileDateBirth']")
    private WebElement birthdayInputFieldTitle;
    @FindBy(css = ".form-group_label")
    private WebElement photoInputFieldTitle;
    @FindBy(css = "[for='editProfilePhoto']")
    private WebElement photoNameLabel;
    @FindBy(css = "#editProfileName")
    private WebElement nameInput;
    @FindBy(css = "#editProfileLastName")
    private WebElement lastNameInput;
    @FindBy(css = "#editProfileCountry")
    private WebElement countryInput;
    @FindBy(css = "#editProfileDateBirth")
    private WebElement birthdayInput;
    @FindBy(css = "input#editProfilePhoto")
    private WebElement photoInput;
    @FindBy(css = ".modal-footer .btn.btn-primary")
    private WebElement saveButton;
    @FindBy(css = "app-photo-input")
    private WebElement appPhotoInput;
    private final By photoInputLocator = By.cssSelector("input#editProfilePhoto");
    private final String appPhotoInputAttributeName = "class";
    private final String appPhotoInputAttributePartOfValue = "ng-dirty";

    public EditProfileModalBox setName(String name){
        setTextFieldValue(nameInput, name);
        return this;
    }
    public EditProfileModalBox setLastName(String lastName){
        setTextFieldValue(lastNameInput, lastName);
        return this;
    }
    public EditProfileModalBox setCountry(String countryName){
        setTextFieldValue(countryInput, countryName);
        return this;
    }
    public EditProfileModalBox setBirthday(Date birthday){
        String dateAsString = new SimpleDateFormat(DATE_FORMAT.getValue()).format(birthday);
        setTextFieldValue(birthdayInput, dateAsString);
        return this;
    }
    public EditProfileModalBox selectPhoto(File photo){
        setTextFieldValue(photoInputLocator, photoInput, photo.getAbsolutePath());
        waitForPartOfAttributeValueChanged(appPhotoInput, appPhotoInputAttributeName,
                appPhotoInputAttributePartOfValue);
        return this;
    }
    public void clickSaveProfileButtonPositiveCase(){
        clickElement(saveButton);
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
    }
    public EditProfileModalBox clickSaveProfileButtonNegativeCase(){
        clickElement(saveButton);
        return this;
    }
    public void saveProfilePositiveCase(String name, String lastName, String countryName, Date birthday,
                                                   File photo){
        setName(name)
                .setLastName(lastName)
                .setCountry(countryName)
                .setBirthday(birthday)
                .selectPhoto(photo)
                .clickSaveProfileButtonPositiveCase();
    }
    public String getNameInputFieldTitle(){
        return getText(nameInputFieldTitle);
    }
    public String getLastNameInputFieldTitle(){
        return getText(lastNameInputFieldTitle);
    }
    public String getCountryInputFieldTitle(){
        return getText(countryInputFieldTitle);
    }
    public String getBirthdayInputFieldTitle(){
        return getText(birthdayInputFieldTitle);
    }
    public String getPhotoInputFieldTitle(){
        return getText(photoInputFieldTitle);
    }
}