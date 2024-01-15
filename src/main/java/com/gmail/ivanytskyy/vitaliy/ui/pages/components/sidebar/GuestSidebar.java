package com.gmail.ivanytskyy.vitaliy.ui.pages.components.sidebar;

import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestExpensesPage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestGaragePage;
import com.gmail.ivanytskyy.vitaliy.ui.pages.guest.GuestInstructionsPage;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 01/11/2023
 */
public class GuestSidebar extends Sidebar {

    public GuestGaragePage openGarage(){
        clickElement(garageLink);
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