package com.gmail.ivanytskyy.vitaliy.ui.pages.user;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.EditProfileModalBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 03/11/2023
 */
public class UserProfilePage extends UserPage {
    @FindBy(xpath = "//app-profile/div/div/h1")
    private WebElement profileTitle;
    @FindBy(css = ".profile_name.display-4")
    private WebElement profileName;
    @FindBy(css = "ul>li:nth-child(1)>span:nth-child(2)")
    private WebElement birthday;
    @FindBy(css = "ul>li:nth-child(2)>span:nth-child(2)")
    private WebElement countryName;
    @FindBy(css = "div.profile>img")
    private WebElement photoImg;
    @FindBy(css = ".panel-page .btn.btn-primary")
    private WebElement editProfileButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    private final By bodyLocator = By.xpath("//body");

    public EditProfileModalBox editProfile(){
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOf(profileTitle),
                ExpectedConditions.visibilityOf(profileName)
        ));
        clickButton(editProfileButton);
        waitForPartOfAttributeValueChanged(webDriver.findElement(bodyLocator),
                "class", "modal-open");
        return new EditProfileModalBox();
    }
    @Override
    public String getPageTitle(){
        return getText(profileTitle);
    }
    public String getProfileName(){
        return getText(profileName);
    }
    public String getBirthday(){
        return getText(birthday);
    }
    public String getCountryName(){
        return getText(countryName);
    }
    public String getPhoto(){
        return getAttribute(photoImg, "src");
    }
}