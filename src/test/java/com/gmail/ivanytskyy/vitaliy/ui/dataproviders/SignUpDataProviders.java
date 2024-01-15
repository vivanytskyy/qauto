package com.gmail.ivanytskyy.vitaliy.ui.dataproviders;

import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 06/01/2024
 */
public class SignUpDataProviders {

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
        return new Object[][]{
                {firstName, lastName, "9876543210@ukr.net",               password},
                {firstName, lastName, "SEMEN@ukr.net",                    password},
                {firstName, lastName, "№%-=/`'@ukr.net",                  password},
                {firstName, lastName, "DUNcan@dot.com",                   password},
                {firstName, lastName, localPart64 + "@incorporation.com", password},
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
    @DataProvider
    public static Object[][] firstNameProviderNegativeCase(){
        String lastName = "Morales";
        String email = "morales@com.ua";
        String password = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {"",                        lastName, email, password, "Name required"},
                // TODO: 06.01.2024 Add bug to Jira (according to spec must be used trim function)
                //{"  ",                      lastName, email, password, "Name required"},
                {"a",                       lastName, email, password, "Name has to be from 2 to 20 characters long"},
                {"TwentyOneSymbolsLengz",   lastName, email, password, "Name has to be from 2 to 20 characters long"},
                {"a1bc",                    lastName, email, password, "Name is invalid"},
                {"Нэйм",                    lastName, email, password, "Name is invalid"},
                {"NameWith+",               lastName, email, password, "Name is invalid"},
                {"NameWith-",               lastName, email, password, "Name is invalid"},
                {"NameWith.",               lastName, email, password, "Name is invalid"}
        };
    }
    @DataProvider
    public static Object[][] lastNameProviderNegativeCase(){
        String firstName = "Chani";
        String email = "CHAni@dot.com";
        String password = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {firstName, "",                         email, password, "Last name required"},
                // TODO: 06.01.2024 Add bug to Jira (according to spec must be used trim function)
                //{firstName, "  ",                       email, password, "Last name required"},
                {firstName, "z",                        email, password, "Last name has to be from 2 to 20 characters long"},
                {firstName, "TwentyOneSymbolsLengz",    email, password, "Last name has to be from 2 to 20 characters long"},
                {firstName, "xyz2",                     email, password, "Last name is invalid"},
                {firstName, "ЛастНэйм",                 email, password, "Last name is invalid"},
                {firstName, "Last-Name",                email, password, "Last name is invalid"},
                {firstName, "Last+Name",                email, password, "Last name is invalid"},
                {firstName, ".LastName",                email, password, "Last name is invalid"}
        };
    }
    @DataProvider
    public static Object[][] emailProviderNegativeCase(){
        String firstName = "Duncan";
        String lastName = "Idaho";
        String password = "vQ8pqjFYCgZUTM";
        String localPart65 = ("1k".repeat(32)) + "a";
        String domainPart256 = ("ab".repeat(126)) + ".com";
        return new Object[][]{
                {firstName, lastName, "",                                   password, "Email required"},
                {firstName, lastName, "ivan ivanov@ukr.net",                password, "Email is incorrect"},
                {firstName, lastName, "ivan\\ivanov@ukr.net",               password, "Email is incorrect"},
                {firstName, lastName, "ivan@ivanov@ukr.net",                password, "Email is incorrect"},
                {firstName, lastName, "ivan..ivanov@ukr.net",               password, "Email is incorrect"},
                {firstName, lastName, ".ivanov@ukr.net",                    password, "Email is incorrect"},
                {firstName, lastName, "ivanov.@ukr.net",                    password, "Email is incorrect"},
                {firstName, lastName, "ivan\"ivanov@ukr.net",               password, "Email is incorrect"},
                {firstName, lastName, "ivan,ivanov@ukr.net",                password, "Email is incorrect"},
                {firstName, lastName, "ivan[ivanov@ukr.net",                password, "Email is incorrect"},
                {firstName, lastName, "ivan]ivanov@ukr.net",                password, "Email is incorrect"},
                {firstName, lastName, "ivanukr.net",                        password, "Email is incorrect"},
                {firstName, lastName, "ivan@укр.нет",                       password, "Email is incorrect"},
                {firstName, lastName, "ivan@ukr net",                       password, "Email is incorrect"},
                {firstName, lastName, "ivan@.ukr.net",                      password, "Email is incorrect"},
                {firstName, lastName, "ivan@ukr.net.",                      password, "Email is incorrect"},
                {firstName, lastName, "ivan@ukr..net",                      password, "Email is incorrect"},
                {firstName, lastName, "ivan@ukr-net",                       password, "Email is incorrect"},
                {firstName, lastName, "ivan@net",                           password, "Email is incorrect"},
                // TODO: 06.01.2024 Add bug to Jira (RFC 5321: The maximum total length of a user name or other
                //  local-part is 64 octets).
                //{firstName, lastName, localPart65 + "@incorporation.com",   password, "Email is incorrect"},
                // TODO: 06.01.2024 Add bug to Jira (RFC 5321: The maximum total length of a domain name or number
                //  is 255 octets).
                //{firstName, lastName, "local_part@" + domainPart256,   password, "Email is incorrect"},
        };
    }
    @DataProvider
    public static Object[][] passwordProviderNegativeCase(){
        String firstName = "Leto";
        String lastName = "Atreides";
        String email = "duna@fremen.com";
        String errorMessage = "Password has to be from 8 to 15 characters long and contain at least one integer, " +
                "one capital, and one small letter";
        return new Object[][]{
                {firstName, lastName, email, "",                    "Password required"},
                // length < 8
                {firstName, lastName, email, "Abc12+)",             errorMessage},
                // length > 15
                {firstName, lastName, email, "Abc12Аб+)3456789",    errorMessage},
                // digit doesn't exist
                {firstName, lastName, email, "AbcdeАб+)",           errorMessage},
                // capital letter doesn't exist
                {firstName, lastName, email, "abc12аб+)",           errorMessage},
                // small letter doesn't exist
                {firstName, lastName, email, "ABC12АБ+)",           errorMessage},
                {firstName, lastName, email, "-+=_;)-",             errorMessage},
                {firstName, lastName, email, "        ",            errorMessage}
        };
    }
    @DataProvider
    public static Object[][] rePasswordProviderNegativeCase(){
        String firstName = "Leto";
        String lastName = "Atreides";
        String email = "duna@fremen.com";
        String password = "vQ8pqjFYCgZUTM";
        return new Object[][]{
                {firstName, lastName, email, password, "",
                        "Re-enter password required"},
                {firstName, lastName, email, password, password + "1",
                        "Passwords do not match"},
                {firstName, lastName, email, password, password.substring(0, password.length() - 1),
                        "Passwords do not match"}
        };
    }
}