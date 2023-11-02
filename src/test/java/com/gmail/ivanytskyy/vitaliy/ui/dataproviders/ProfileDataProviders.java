package com.gmail.ivanytskyy.vitaliy.ui.dataproviders;

import org.testng.annotations.DataProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static com.gmail.ivanytskyy.vitaliy.ui.utils.StringConstants.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 02/11/2023
 */
public class ProfileDataProviders {

    @DataProvider
    public static Object[][] nameProviderPositiveCase(){
        return new Object[][]{
                {"Al",                   "Smith", "Canada", "15.06.2000"},
                {"don",                  "Smith", "Canada", "15.06.2000"},
                {"NineteenSymbolsLeng",  "Smith", "Canada", "15.06.2000"},
                {"TwentySymbolsLengthN", "Smith", "Canada", "15.06.2000"}
        };
    }
    @DataProvider
    public static Object[][] lastNameProviderPositiveCase(){
        return new Object[][]{
                {"Simon", "QU",                   "Germany", "17.11.1995"},
                {"Simon", "zen",                  "Germany", "17.11.1995"},
                {"Simon", "NineteenSymbolsLeng",  "Germany", "17.11.1995"},
                {"Simon", "TwentySymbolsLengthN", "Germany", "17.11.1995"}
        };
    }
    @DataProvider
    public static Object[][] countryNameProviderPositiveCase(){
        return new Object[][]{
                {"Paul", "Atreides", "GB",                   "02.03.2020"},
                {"Paul", "Atreides", "USA",                  "02.03.2020"},
                {"Paul", "Atreides", "TrinidadAndTobagoTM",  "02.03.2020"},
                {"Paul", "Atreides", "UnitedArabEmiratesTM", "02.03.2020"}
        };
    }
    @DataProvider
    public static Object[][] birthdayProviderPositiveCase(){
        return new Object[][]{
                {"Paul", "Atreides", "Poland", "01.01.1900"},
                {"Paul", "Atreides", "Poland", "01.01.1901"},
                {"Paul", "Atreides", "Poland", getYesterdayDateAsString()},
                {"Paul", "Atreides", "Poland", getTodayDateAsString()}
        };
    }
    @DataProvider
    public static Object[][] editProfileProviderPositiveCase(){
        List<Object[]> allValues = new ArrayList<>();
        allValues.addAll(new ArrayList<>(Arrays.asList(nameProviderPositiveCase())));
        allValues.addAll(new ArrayList<>(Arrays.asList(lastNameProviderPositiveCase())));
        allValues.addAll(new ArrayList<>(Arrays.asList(countryNameProviderPositiveCase())));
        allValues.addAll(new ArrayList<>(Arrays.asList(birthdayProviderPositiveCase())));
        return allValues.stream().map(object -> Arrays.stream(object).toArray()).toArray(Object[][]::new);
    }

    private static String getYesterdayDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT.getValue());
        return dateFormat.format(someDay(-1));
    }
    private static String getTodayDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT.getValue());
        return dateFormat.format(someDay(0));
    }
    private static Date someDay(int shift) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, shift);
        return cal.getTime();
    }
}