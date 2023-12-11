package com.gmail.ivanytskyy.vitaliy.unit;

import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService.AvailableSymbols.*;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 11/12/2023
 */
public class PasswordGenerateServiceTest {
    private PasswordGenerateService.Builder passwordBuilder;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp(){
        this.passwordBuilder = new PasswordGenerateService.Builder();
        softAssert = new SoftAssert();
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        passwordBuilder = null;
        softAssert = null;
    }
    @Test(description = "Generate a fixed-length password. Positive case",
            dataProvider = "validPasswordFixedLength",
            priority = 10)
    public void generatePasswordFixedLengthTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, explicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 21)
    public void generatePasswordFixedLengthOnlyLowercaseLettersExplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, explicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 22)
    public void generatePasswordFixedLengthOnlyUppercaseLettersExplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only digits, explicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 23)
    public void generatePasswordFixedLengthOnlyDigitsExplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only special symbols, explicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 24)
    public void generatePasswordFixedLengthOnlyPunctuationSymbolsExplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters, implicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 31)
    public void generatePasswordFixedLengthOnlyLowercaseLettersImplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters, implicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 32)
    public void generatePasswordFixedLengthOnlyUppercaseLettersImplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only digits, implicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 33)
    public void generatePasswordFixedLengthOnlyDigitsImplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (only special symbols, implicit set up). Positive case.",
            dataProvider = "validPasswordFixedLength",
            priority = 34)
    public void generatePasswordFixedLengthOnlyPunctuationSymbolsImplicitSetUpTest(int passwordLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
        softAssert.assertEquals(password.length(), passwordLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password. Positive case",
            dataProvider = "validPasswordVariableLength",
            priority = 40)
    public void generatePasswordVariableLengthTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, explicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 51)
    public void generatePasswordVariableLengthOnlyLowercaseLettersExplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, explicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 52)
    public void generatePasswordVariableLengthOnlyUppercaseLettersExplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only digits, explicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 53)
    public void generatePasswordVariableLengthOnlyDigitsExplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only special symbols, explicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 54)
    public void generatePasswordVariableLengthOnlyPunctuationSymbolsExplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only lowercase letters, implicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 61)
    public void generatePasswordVariableLengthOnlyLowercaseLettersImplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useLowerCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertTrue(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only uppercase letters, implicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 62)
    public void generatePasswordVariableLengthOnlyUppercaseLettersImplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useUpperCaseLetters(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter wasn't found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only digits, implicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 63)
    public void generatePasswordVariableLengthOnlyDigitsImplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useDigits(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertTrue(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character wasn't found");
        softAssert.assertFalse(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol was found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a variable-length password (only special symbols, implicit set up). Positive case.",
            dataProvider = "validPasswordVariableLength",
            priority = 64)
    public void generatePasswordVariableLengthOnlyPunctuationSymbolsImplicitSetUpTest(int minLength, int maxLength){
        String password = passwordBuilder
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        softAssert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        softAssert.assertFalse(isSymbolExist(password, LOWERCASE_LETTERS.getAsString()),
                "Lowercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, UPPERCASE_LETTERS.getAsString()),
                "Uppercase letter was found");
        softAssert.assertFalse(isSymbolExist(password, DIGITS.getAsString()),
                "Digit character was found");
        softAssert.assertTrue(isSymbolExist(password, SPECIAL_SYMBOLS.getAsString()),
                "Special symbol wasn't found");
        softAssert.assertAll();
    }
    @Test(description = "Generate a fixed-length password (invalid length value). Negative case",
            dataProvider = "invalidPasswordFixedLength",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length value:.*",
            priority = 70)
    public void generatePasswordInvalidFixedLengthTest(int passwordLength){
        passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(passwordLength);
    }
    @Test(description = "Generate a fixed-length password (options for builder are false). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 81)
    public void generatePasswordFixedLengthDidNotChooseAnyOptionsInBuilderExplicitSetUpNegativeTest(){
        passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(8);
    }
    @Test(description = "Generate a fixed-length password (options for builder weren't chosen). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 82)
    public void generatePasswordFixedLengthDidNotChooseAnyOptionsInBuilderImplicitSetUpNegativeTest(){
        passwordBuilder
                .build()
                .generatePassword(8);
    }
    @Test(description = "Generate a variable-length password (min and/or max lengths are invalid). Negative case",
            dataProvider = "invalidPasswordVariableLength",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length values:.*",
            priority = 90)
    public void generatePasswordInvalidVariableLengthTest(int minLength, int maxLength){
        passwordBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (options for builder are false). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 101)
    public void generatePasswordVariableLengthDidNotChooseAnyOptionsInBuilderExplicitSetUpNegativeTest(){
        int minLength = 15;
        int maxLength = 19;
        passwordBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (options for builder weren't chosen). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options",
            priority = 102)
    public void generatePasswordVariableLengthDidNotChooseAnyOptionsInBuilderImplicitSetUpNegativeTest(){
        int minLength = 15;
        int maxLength = 19;
        passwordBuilder
                .build()
                .generatePassword(minLength, maxLength);
    }
    @DataProvider
    public static Object[][] validPasswordFixedLength() {
        return new Object[][]{
                {4},
                {52},
                {100}
        };
    }
    @DataProvider
    public static Object[][] invalidPasswordFixedLength() {
        return new Object[][]{
                {3},
                {101}
        };
    }
    @DataProvider
    public static Object[][] validPasswordVariableLength() {
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
    public static Object[][] invalidPasswordVariableLength() {
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
    private boolean isSymbolExist(String password, String symbols){
        return  password
                .chars()
                .mapToObj(i -> (char)i)
                .map(String::valueOf)
                .anyMatch(symbols::contains);
    }
}