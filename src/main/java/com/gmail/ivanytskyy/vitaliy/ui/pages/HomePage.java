package com.gmail.ivanytskyy.vitaliy.ui.pages;

import com.gmail.ivanytskyy.vitaliy.ui.pages.components.footer.Footer;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.modal.SignUpModalBox;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.header.VisitorHeader;
import com.gmail.ivanytskyy.vitaliy.ui.utils.Contacts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 28/10/2023
 */
public class HomePage extends BasePage{
    @FindBy(css = "button.btn.btn-outline-white.header_signin")
    private WebElement signInButton;
    @FindBy(css = "button.hero-descriptor_btn.btn.btn-primary")
    private WebElement signUpButton;
    @FindBy(css = "div.modal-content")
    private WebElement modalContent;
    @FindBy(css = "div>h1")
    private WebElement title;
    @FindBy(css = "app-header")
    private WebElement visitorHeader;
    @FindBy(css = "footer.footer")
    private WebElement footer;
    @FindBy(css = "#aboutSection")
    private WebElement aboutSection;
    @FindBy(css = "#contactsSection")
    private WebElement contactsSection;
    @FindBy(css = "a.socials_link[href*= 'facebook']")
    private WebElement facebookLink;
    @FindBy(css = "a.socials_link[href*= 't.me']")
    private WebElement telegramLink;
    @FindBy(css = "a.socials_link[href*= 'youtube']")
    private WebElement youtubeLink;
    @FindBy(css = "a.socials_link[href*= 'instagram']")
    private WebElement instagramLink;
    @FindBy(css = "a.socials_link[href*= 'linkedin']")
    private WebElement linkedinLink;
    @FindBy(css = "a.contacts_link:nth-child(1)")
    private WebElement websiteLink;
    @FindBy(css = "a.contacts_link[href^='mailto']")
    private WebElement emailLink;

    public HomePage(){
        PageFactory.initElements(webDriver, this);
    }
    public SignUpModalBox openSingUpBox(){
        clickButton(signUpButton);
        return new SignUpModalBox();
    }
    public String getPageTitle(){
        return getText(title);
    }
    public VisitorHeader moveToVisitorHeader(){
        return new VisitorHeader();
    }
    public Footer moveToFooter(){
        return new Footer();
    }
    public Boolean isAboutSectionDisplayed(){
        return isElementDisplayed(aboutSection);
    }
    public Boolean isContactsSectionDisplayed(){
        return isElementDisplayed(contactsSection);
    }
    public String getContact(Contacts contact){
        return switch (contact){
            case FACEBOOK -> getAttribute(facebookLink, "href");
            case TELEGRAM -> getAttribute(telegramLink, "href");
            case YOUTUBE -> getAttribute(youtubeLink, "href");
            case INSTAGRAM -> getAttribute(instagramLink, "href");
            case LINKEDIN -> getAttribute(linkedinLink, "href");
            case WEBSITE -> getAttribute(websiteLink, "href");
            case EMAIL -> getAttribute(emailLink, "href").substring(7);
        };
    }
}