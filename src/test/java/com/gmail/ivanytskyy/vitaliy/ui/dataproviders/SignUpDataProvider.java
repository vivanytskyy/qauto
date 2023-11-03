package com.gmail.ivanytskyy.vitaliy.ui.dataproviders;

import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 03/11/2023
 */
public class SignUpDataProvider {

    @DataProvider
    public static Object[][] firstNameProviderPositiveCase(){
        String lastName = "Morales";
        String email = "morales@com.ua";
        String password = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {"Lu",                      lastName, email, password},
                {"ben",                     lastName, email, password},
                {"NineteenSymbolsLeng",     lastName, email, password},
                {"TwentySymbolsLengthN",    lastName, email, password}
        };
    }
    @DataProvider
    public static Object[][] lastNameProviderPositiveCase(){
        String firstName = "Chani";
        String email = "CHAni@dot.com";
        String password = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {firstName, "go",                    email, password},
                {firstName, "One",                   email, password},
                {firstName, "NineteenSymbolsLeng",   email, password},
                {firstName, "TwentySymbolsLengthN",  email, password}
        };
    }
    @DataProvider
    public static Object[][] emailProviderPositiveCase(){
        String firstName = "Duncan";
        String lastName = "Idaho";
        String password = "vQ8pqjFYCgZUTM";
        String localPart64 = "1k".repeat(32);
        //"Bug added to Jira (Edit Issue : U1QM241022-107)
        String domainPart255 = "nt2Lb".repeat(50) + ".info";
        return new Object[][]{
                {firstName, lastName, "123456789@ukr.net",                   password},
                {firstName, lastName, "SEMEN@ukr.net",                    password},
                {firstName, lastName, "№%-=/`'@ukr.net",                  password},
                {firstName, lastName, "DUNcan@dot.com",                   password},
                {firstName, lastName, localPart64 + "@incorporation.com", password},
                {firstName, lastName, "some1Email@" + domainPart255,      password}
        };
    }
    @DataProvider
    public static Object[][] passwordProviderPositiveCase(){
        String firstName = "Leto";
        String lastName = "Atreides";
        String email = "duna@fremen.com";
        return new Object[][]{
                {firstName, lastName, email, "8AbcdАБ+"},
                {firstName, lastName, email, "9INFrЦп-/"},
                {firstName, lastName, email, "15AbcАб+)345678"},
                {firstName, lastName, email, "14AbcАб+)34567"},
                {firstName, lastName, email, "ONLY_1_number"},
                {firstName, lastName, email, "1_SMALL_s_23"},
                {firstName, lastName, email, "1_capital_F_56"},
        };
    }
    @DataProvider
    public static Object[][] registrationDataProviderPositiveCase(){
        List<Object[]> data = new ArrayList<>();
        data.addAll(new ArrayList<>(Arrays.asList(firstNameProviderPositiveCase())));
        data.addAll(new ArrayList<>(Arrays.asList(lastNameProviderPositiveCase())));
        data.addAll(new ArrayList<>(Arrays.asList(emailProviderPositiveCase())));
        data.addAll(new ArrayList<>(Arrays.asList(passwordProviderPositiveCase())));
        return data.toArray(new Object[data.size()][]);
    }
}