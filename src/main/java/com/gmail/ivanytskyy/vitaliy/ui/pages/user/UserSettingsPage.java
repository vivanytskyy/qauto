package com.gmail.ivanytskyy.vitaliy.ui.pages.user;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.RemoveAccountModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.Currencies;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.DistanceUnits;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 07/11/2023
 */
public class UserSettingsPage extends UserPage {
    @FindBy(xpath = "//app-settings/div/div/h1")
    private WebElement pageTitle;
    @FindBy(css = "div.user-settings_item:nth-child(1)>h2")
    private WebElement currencyTitle;
    @FindBy(css = "div.user-settings_item:nth-child(2)>h2")
    private WebElement distanceUnitTitle;
    @FindBy(css = "div.user-settings_item:nth-child(3) h2")
    private WebElement changeEmailTitle;
    @FindBy(css = "[for='emailChangeEmail']")
    private WebElement emailLabelOfChangeEmailForm;
    @FindBy(css = "[for='emailChangePassword']")
    private WebElement passwordLabelOfChangeEmailForm;
    @FindBy(css = "#emailChangeEmail")
    private WebElement emailInputOfChangeEmailForm;
    @FindBy(css = "#emailChangePassword")
    private WebElement passwordInputOfChangeEmailForm;
    @FindBy(css = "div.user-settings_item:nth-child(4) h2")
    private WebElement changePasswordTitle;
    @FindBy(css = "[for='passwordChangeOldPassword']")
    private WebElement oldPasswordLabelOfChangePasswordForm;
    @FindBy(css = "[for='passwordChangePassword']")
    private WebElement newPasswordLabelOfChangePasswordForm;
    @FindBy(css = "[for='passwordChangeRepeatPassword']")
    private WebElement repeatNewPasswordLabelOfChangePasswordForm;
    @FindBy(css = "#passwordChangeOldPassword")
    private WebElement oldPasswordInputOfChangePasswordForm;
    @FindBy(css = "#passwordChangePassword")
    private WebElement newPasswordInputOfChangePasswordForm;
    @FindBy(css = "#passwordChangeRepeatPassword")
    private WebElement repeatNewPasswordInputOfChangePasswordForm;
    @FindBy(css = "div.user-settings_item:nth-child(3) button")
    private WebElement changeEmailButton;
    @FindBy(css = "div.user-settings_item:nth-child(4) button")
    private WebElement changePasswordButton;
    @FindBy(css = "div.user-settings_item:nth-child(1)>div>button")
    private List<WebElement> currencyItems;
    @FindBy(css = "div.user-settings_item:nth-child(2)>div>button")
    private List<WebElement> distanceUnitItems;
    @FindBy(css = ".btn.btn-danger-bg")
    private WebElement removeAccountButton;
    @FindBy(css = "ngb-modal-window[role='dialog']")
    private WebElement removeDialogModalWindow;
    @FindBy(css = "app-change-email-form>form")
    private WebElement changeEmailForm;

    @Override
    public String getPageTitle() {
        return getText(pageTitle);
    }
    public RemoveAccountModalBox removeAccount(){
        clickElement(removeAccountButton);
        return new RemoveAccountModalBox();
    }
    public String getCurrencyTitle(){
        return getText(currencyTitle);
    }
    public String getDistanceUnitTitle(){
        return getText(distanceUnitTitle);
    }
    public String getChangeEmailTitle(){
        return getText(changeEmailTitle);
    }
    public String getChangePasswordTitle(){
        return getText(changePasswordTitle);
    }
    public Currencies getCurrentCurrency(){
        return Currencies.valueOf(currencyItems
                .stream()
                .filter(button -> button.getAttribute("class").contains("-active"))
                .findFirst()
                .orElseThrow()
                .getText());
    }
    public DistanceUnits getCurrentDistanceUnit(){
        return DistanceUnits.valueOf(distanceUnitItems
                .stream()
                .filter(button -> button.getAttribute("class").contains("-active"))
                .findFirst()
                .orElseThrow()
                .getText()
                .toUpperCase()
        );
    }
    public UserSettingsPage setCurrency(Currencies currency){
        WebElement necessaryButton = currencyItems
                .stream()
                .filter(button -> button.getText().equals(currency.toString()))
                .findFirst()
                .orElseThrow();
        if(!necessaryButton.getAttribute("class").contains("-active")){
            clickElement(necessaryButton);
            waitForPartOfAttributeValueChanged(necessaryButton, "class", "-active");
        }
        return new UserSettingsPage();
    }
    public UserSettingsPage setDistanceUnit(DistanceUnits unit){
        WebElement necessaryButton  = distanceUnitItems
                .stream()
                .filter(button -> button.getText().equals(unit.getValue()))
                .findFirst()
                .orElseThrow();
        if(!necessaryButton.getAttribute("class").contains("-active")){
            clickElement(necessaryButton);
            waitForPartOfAttributeValueChanged(necessaryButton, "class", "-active");
        }
        return new UserSettingsPage();
    }
    public UserSettingsPage setEmailInChangeEmailForm(String newEmail){
        setTextFieldValue(emailInputOfChangeEmailForm, newEmail);
        return this;
    }
    public UserSettingsPage setPasswordInChangeEmailForm(String password){
        setTextFieldValue(passwordInputOfChangeEmailForm, password);
        return this;
    }
    public UserSettingsPage clickChangeEmailButtonPositiveCase(){
        clickElement(changeEmailButton);
        waitForPartOfAttributeValueChanged(changeEmailForm, "class", "ng-invalid");
        return this;
    }
    public UserSettingsPage changeEmailPositiveCase(String newEmail, String actualPassword){
        setEmailInChangeEmailForm(newEmail)
                .setPasswordInChangeEmailForm(actualPassword)
                .clickChangeEmailButtonPositiveCase();
        return this;
    }
    public UserSettingsPage setOldPasswordInChangePasswordForm(String oldPassword){
        setTextFieldValue(oldPasswordInputOfChangePasswordForm, oldPassword);
        return this;
    }
    public UserSettingsPage setNewPasswordInChangePasswordForm(String newPassword){
        setTextFieldValue(newPasswordInputOfChangePasswordForm, newPassword);
        return this;
    }
    public UserSettingsPage setRepeatNewPasswordInChangePasswordForm(String newPassword){
        setTextFieldValue(repeatNewPasswordInputOfChangePasswordForm, newPassword);
        return this;
    }
    public UserSettingsPage clickChangePasswordButtonPositiveCase(){
        clickElement(changePasswordButton);
        return this;
    }
    public UserSettingsPage changePasswordPositiveCase(String oldPassword, String newPassword){
        setOldPasswordInChangePasswordForm(oldPassword)
                .setNewPasswordInChangePasswordForm(newPassword)
                .setRepeatNewPasswordInChangePasswordForm(newPassword)
                .clickChangePasswordButtonPositiveCase();
        return this;
    }
    public String getEmailInputFieldLabelOfChangeEmailForm(){
        return getText(emailLabelOfChangeEmailForm);
    }
    public String getPasswordInputFieldLabelOfChangeEmailForm(){
        return getText(passwordLabelOfChangeEmailForm);
    }
    public String getOldPasswordInputFieldLabelOfChangePasswordForm(){
        return getText(oldPasswordLabelOfChangePasswordForm);
    }
    public String getNewPasswordInputFieldLabelOfChangePasswordForm(){
        return getText(newPasswordLabelOfChangePasswordForm);
    }
    public String getRepeatNewPasswordInputFieldLabelOfChangePasswordForm(){
        return getText(repeatNewPasswordLabelOfChangePasswordForm);
    }
}