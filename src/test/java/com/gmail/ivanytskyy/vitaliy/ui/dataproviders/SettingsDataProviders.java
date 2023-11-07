package com.gmail.ivanytskyy.vitaliy.ui.dataproviders;

import com.gmail.ivanytskyy.vitaliy.ui.utils.units.Currencies;
import com.gmail.ivanytskyy.vitaliy.ui.utils.units.DistanceUnits;
import org.testng.annotations.DataProvider;
import java.util.Arrays;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 07/11/2023
 */
public class SettingsDataProviders {
    @DataProvider
    public static Object[][] currencyProviderPositiveCase(){
        return Arrays
                .stream(Currencies.values())
                .map(currency -> new Object[]{currency})
                .toArray(Object[][]::new);
    }
    @DataProvider
    public static Object[][] distanceUnitProviderPositiveCase(){
        Object[][] data = new Object[DistanceUnits.values().length][1];
        for(int i = 0; i < DistanceUnits.values().length; i++){
            data[i][0] = DistanceUnits.values()[i];
        }
        return data;
    }
    @DataProvider
    public static Object[][] emailProviderPositiveCase(){
        String password = "vQ8pqjFYCgZUTM";
        String localPart64 = "Tl".repeat(32);
        return new Object[][]{
                {"01234567890@ukr.net",             password},
                {"CAPITAL@letters.pl",              password},
                {"CAMEtoQA@dot.com",                password},
                {"small@letters.email",             password},
                {"!#$%&'*+-/.=?^_`{|}~@some.com",   password},
                {"\"(),:;<>@[]\"@strange.email",    password},
                {"\" \"@example.org",               password},
                {localPart64 + "@just.net",         password},
                {"jsmith@[192.168.2.1]",            password},
                {"x@example.com",                   password},
                {"example@s.one",                   password}
        };
    }
    @DataProvider
    public static Object[][] passwordProviderPositiveCase(){
        String initialPassword = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {initialPassword, "8AbcdАБ-"},
                {initialPassword, "9INFrЦп-]"},
                {initialPassword, "15AbcАб@)345678"},
                {initialPassword, "14AbcАб+>34567"},
                {initialPassword, "ONLY_1_number"},
                {initialPassword, "1_SMALL_s_09"},
                {initialPassword, "1_capital_F_33"}
        };
    }
}