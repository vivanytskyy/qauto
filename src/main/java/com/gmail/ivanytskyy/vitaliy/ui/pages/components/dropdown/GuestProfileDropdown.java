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
            clickElement(garageLink);
        }
        return new GuestGaragePage();
    }
    public GuestInstructionsPage openInstructions(){
        clickElement(instructionsLink);
        return new GuestInstructionsPage();
    }
    public GuestExpensesPage openExpenses(){
        clickElement(fuelExpensesLink);
        return new GuestExpensesPage();
    }
}