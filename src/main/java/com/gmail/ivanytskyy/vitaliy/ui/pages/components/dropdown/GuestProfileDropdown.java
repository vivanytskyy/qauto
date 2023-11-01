package com.gmail.ivanytskyy.vitaliy.ui.pages.components.dropdown;

import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestInstructionsPage;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.03
 * @date 01/11/2023
 */
public class GuestProfileDropdown extends ProfileDropdown {

    public GuestGaragePage openGarage(){
        if(!garageLink.getAttribute("class").contains("disabled")){
            clickLink(garageLink);
        }
        return new GuestGaragePage();
    }
    public GuestInstructionsPage openInstructions(){
        clickLink(instructionsLink);
        return new GuestInstructionsPage();
    }
    public GuestExpensesPage openExpenses(){
        clickLink(fuelExpensesLink);
        return new GuestExpensesPage();
    }
}