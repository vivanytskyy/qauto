package com.gmail.ivanytskyy.vitaliy.ui.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/11/2023
 */
public class TestDataHandlingService {
    public static Date getDateAsObject(String date){
        int day = Integer.parseInt(date.split("\\.")[0]);
        int month = Integer.parseInt(date.split("\\.")[1]);
        int year = Integer.parseInt(date.split("\\.")[2]);
        Calendar gregorianCalendar = new GregorianCalendar(year, month - 1, day);
        return gregorianCalendar.getTime();
    }
}