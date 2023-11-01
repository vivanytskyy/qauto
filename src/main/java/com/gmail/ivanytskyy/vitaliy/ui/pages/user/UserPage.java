package com.gmail.ivanytskyy.vitaliy.ui.pages.user;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.footer.Footer;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.header.UserHeader;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar.UserSidebar;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public abstract class UserPage extends BasePage {
    public UserPage() {
        PageFactory.initElements(webDriver, this);
    }
    public abstract String getPageTitle();
    public UserSidebar moveToSidebar(){
        return new UserSidebar();
    }
    public UserHeader moveToHeader(){
        return new UserHeader();
    }
    public Footer moveToFooter(){
        return new Footer();
    }
}