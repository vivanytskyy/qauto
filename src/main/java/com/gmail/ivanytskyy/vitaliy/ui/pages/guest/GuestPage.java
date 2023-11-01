package com.gmail.ivanytskyy.vitaliy.ui.pages.guest;

import com.gmail.ivanytskyy.vitaliy.ui.pages.BasePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.header.GuestHeader;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.footer.Footer;
import com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar.GuestSidebar;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public abstract class GuestPage extends BasePage {
    public GuestPage() {
        PageFactory.initElements(webDriver, this);
    }
    public abstract String getPageTitle();
    public GuestSidebar moveToSidebar(){
        return new GuestSidebar();
    }
    public GuestHeader moveToHeader(){
        return new GuestHeader();
    }
    public Footer moveToFooter(){
        return new Footer();
    }
}