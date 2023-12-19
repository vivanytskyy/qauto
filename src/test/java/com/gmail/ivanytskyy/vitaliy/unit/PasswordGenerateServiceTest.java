package com.gmail.ivanytskyy.vitaliy.unit;

import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService.AvailableSymbols.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.02
 * @date 18/12/2023
 */
public class PasswordGenerateServiceTest {
    private PasswordGenerateService.Builder passwordBuilder;

    @BeforeMethod
    public void setUp(){
        this.passwordBuilder = new PasswordGenerateService.Builder();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        passwordBuilder = null;
    }
    @Test(description = "Generate a fixed-length password. Test the password content. Positive case",
            dataProvider = "validFixedLengthPassword",
            priority = 11)
    public void generateFixedLengthPasswordContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsAtLeastOneExpectedSymbol(password, LOWERCASE_LETTERS);
        assertContainsAtLeastOneExpectedSymbol(password, UPPERCASE_LETTERS);
        assertContainsAtLeastOneExpectedSymbol(password, DIGITS);
        assertContainsAtLeastOneExpectedSymbol(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a fixed-length password. Test the password length. Positive case",
            dataProvider = "validFixedLengthPassword",
            priority = 12)
    public void generateFixedLengthPasswordLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 21)
    public void generateFixedLengthPasswordOnlyLowercaseLettersExplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, LOWERCASE_LETTERS);
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 22)
    public void generateFixedLengthPasswordOnlyUppercaseLettersExplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, UPPERCASE_LETTERS);
    }
    @Test(description = "Generate a fixed-length password (only digits, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 23)
    public void generateFixedLengthPasswordOnlyDigitsExplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, DIGITS);
    }
    @Test(description = "Generate a fixed-length password (only special symbols, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 24)
    public void generateFixedLengthPasswordOnlyPunctuationSymbolsExplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 31)
    public void generateFixedLengthPasswordOnlyLowercaseLettersExplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 32)
    public void generateFixedLengthPasswordOnlyUppercaseLettersExplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only digits, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 33)
    public void generateFixedLengthPasswordOnlyDigitsExplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only special symbols, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 34)
    public void generateFixedLengthPasswordOnlyPunctuationSymbolsExplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 41)
    public void generateFixedLengthPasswordOnlyLowercaseLettersImplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, LOWERCASE_LETTERS);
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 42)
    public void generateFixedLengthPasswordOnlyUppercaseLettersImplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, UPPERCASE_LETTERS);
    }
    @Test(description = "Generate a fixed-length password (only digits, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 43)
    public void generateFixedLengthPasswordOnlyDigitsImplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, DIGITS);
    }
    @Test(description = "Generate a fixed-length password (only special symbols, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 44)
    public void generateFixedLengthPasswordOnlyPunctuationSymbolsImplicitSetUpContentTest(int passwordLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertContainsOnlyExpectedSymbols(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 51)
    public void generateFixedLengthPasswordOnlyLowercaseLettersImplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 52)
    public void generateFixedLengthPasswordOnlyUppercaseLettersImplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only digits, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 53)
    public void generateFixedLengthPasswordOnlyDigitsImplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a fixed-length password (only special symbols, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validFixedLengthPassword",
            priority = 54)
    public void generateFixedLengthPasswordOnlyPunctuationSymbolsImplicitSetUpLengthTest(int passwordLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        assertEqualsExpectedLength(password, passwordLength);
    }
    @Test(description = "Generate a variable-length password. Test the password content. Positive case",
            dataProvider = "validVariableLengthPassword",
            priority = 61)
    public void generateVariableLengthPasswordContentTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsAtLeastOneExpectedSymbol(password, LOWERCASE_LETTERS);
        assertContainsAtLeastOneExpectedSymbol(password, UPPERCASE_LETTERS);
        assertContainsAtLeastOneExpectedSymbol(password, DIGITS);
        assertContainsAtLeastOneExpectedSymbol(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a variable-length password. Test the password length. Positive case",
            dataProvider = "validVariableLengthPassword",
            priority = 62)
    public void generateVariableLengthPasswordLengthTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 71)
    public void generateVariableLengthPasswordOnlyLowercaseLettersExplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, LOWERCASE_LETTERS);
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 72)
    public void generateVariableLengthPasswordOnlyUppercaseLettersExplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, UPPERCASE_LETTERS);
    }
    @Test(description = "Generate a variable-length password (only digits, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 73)
    public void generateVariableLengthPasswordOnlyDigitsExplicitSetUpContentTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, DIGITS);
    }
    @Test(description = "Generate a variable-length password (only special symbols, explicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 74)
    public void generateVariableLengthPasswordOnlyPunctuationSymbolsExplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 81)
    public void generateVariableLengthPasswordOnlyLowercaseLettersExplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 82)
    public void generateVariableLengthPasswordOnlyUppercaseLettersExplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only digits, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 83)
    public void generateVariableLengthPasswordOnlyDigitsExplicitSetUpLengthTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only special symbols, explicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 84)
    public void generateVariableLengthPasswordOnlyPunctuationSymbolsExplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 91)
    public void generateVariableLengthPasswordOnlyLowercaseLettersImplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, LOWERCASE_LETTERS);
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 92)
    public void generateVariableLengthPasswordOnlyUppercaseLettersImplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, UPPERCASE_LETTERS);
    }
    @Test(description = "Generate a variable-length password (only digits, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 93)
    public void generateVariableLengthPasswordOnlyDigitsImplicitSetUpContentTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, DIGITS);
    }
    @Test(description = "Generate a variable-length password (only special symbols, implicit set up). " +
            "Test the password content. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 94)
    public void generateVariableLengthPasswordOnlyPunctuationSymbolsImplicitSetUpContentTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertContainsOnlyExpectedSymbols(password, SPECIAL_SYMBOLS);
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 101)
    public void generateVariableLengthPasswordOnlyLowercaseLettersImplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 102)
    public void generateVariableLengthPasswordOnlyUppercaseLettersImplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only digits, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 103)
    public void generateVariableLengthPasswordOnlyDigitsImplicitSetUpLengthTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (only special symbols, implicit set up). " +
            "Test the password length. Positive case.",
            dataProvider = "validVariableLengthPassword",
            priority = 104)
    public void generateVariableLengthPasswordOnlyPunctuationSymbolsImplicitSetUpLengthTest(
            int minLength, int maxLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        assertEqualsExpectedLength(password, minLength, maxLength);
    }
    @Test(description = "Generate a fixed-length password (invalid length value). Negative case",
            dataProvider = "invalidFixedLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length value:.*",
            priority = 110)
    public void generateFixedLengthPasswordInvalidLengthTest(int passwordLength){
        passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
    }
    @Test(description = "Generate a fixed-length password (options for builder are false). Negative case",
            dataProvider = "validFixedLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 121)
    public void generateFixedLengthPasswordAnyOptionWasNotSelectedInBuilderExplicitSetUpNegativeTest(
            int passwordLength){
        passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
    }
    @Test(description = "Generate a fixed-length password (options for builder weren't chosen). Negative case",
            dataProvider = "validFixedLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 122)
    public void generateFixedLengthPasswordAnyOptionWasNotSelectedInBuilderImplicitSetUpNegativeTest(
            int passwordLength){
        passwordBuilder
                .build()
                .generatePassword(passwordLength);
    }
    @Test(description = "Generate a variable-length password (min and/or max lengths are invalid). Negative case",
            dataProvider = "invalidVariableLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length values:.*",
            priority = 130)
    public void generateVariableLengthPasswordInvalidLengthTest(int minLength, int maxLength){
        passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (options for builder are false). Negative case",
            dataProvider = "validVariableLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 141)
    public void generateVariableLengthPasswordAnyOptionWasNotSelectedInBuilderExplicitSetUpNegativeTest(
            int minLength, int maxLength){
        passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (options for builder weren't chosen). Negative case",
            dataProvider = "validVariableLengthPassword",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 142)
    public void generateVariableLengthPasswordAnyOptionWasNotSelectedInBuilderImplicitSetUpNegativeTest(
            int minLength, int maxLength){
        passwordBuilder
                .build()
                .generatePassword(minLength, maxLength);
    }
    @DataProvider
    private Object[][] validFixedLengthPassword() {
        return new Object[][]{
                {4},
                {52},
                {100}
        };
    }
    @DataProvider
    private Object[][] invalidFixedLengthPassword() {
        return new Object[][]{
                {3},
                {101}
        };
    }
    @DataProvider
    private Object[][] validVariableLengthPassword() {
        return new Object[][]{
                {4,     100},
                {4,     4},
                {100,   100},
                {4,     5},
                {99,    100},
                {5,     52},
                {52,    99},
                {52,    52}
        };
    }
    @DataProvider
    private Object[][] invalidVariableLengthPassword() {
        return new Object[][]{
                {3,     100},
                {4,     101},
                {3,     4},
                {100,   101},
                {3,     3},
                {101,   101},
                {3,     101},
                {3,     52},
                {52,    101},
                {-25,   52},
                {-52,   -20},
                {0,     0},
                {5,     4},
                {100,   99},
                {52,    5},
                {99,    52},
                {4,     3},
                {101,   100},
                {101,   3}
        };
    }

    private void assertContainsAtLeastOneExpectedSymbol(String password,
                                                        PasswordGenerateService.AvailableSymbols symbols){
        Assert.assertTrue(password
                        .chars()
                        .mapToObj(i -> (char)i)
                        .map(String::valueOf)
                        .anyMatch(symbols.getAsString()::contains),
                String.format("%s weren't found", symbols));
    }
    private void assertContainsOnlyExpectedSymbols(String password, PasswordGenerateService.AvailableSymbols symbols){
        Assert.assertTrue(password
                        .chars()
                        .mapToObj(i -> (char)i)
                        .map(String::valueOf)
                        .allMatch(symbols.getAsString()::contains),
                String.format("Not only %s were found", symbols));
    }
    private void assertEqualsExpectedLength(String password, int expectedLength){
        Assert.assertEquals(password.length(), expectedLength, "Password length incorrect");
    }
    private void assertEqualsExpectedLength(String password, int minExpectedLength, int maxExpectedLength){
        Assert.assertTrue(password.length() >= minExpectedLength
                        && password.length() <= maxExpectedLength,
                "Password length incorrect");
    }
}