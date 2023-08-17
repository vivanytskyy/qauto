package com.gmail.ivanytskyy.vitaliy.unit;

import com.gmail.ivanytskyy.vitaliy.utils.PasswordGenerateService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 17/08/2023
 */
public class PasswordGenerateServiceTest {
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_SYMBOLS = "!@#$%&*()_+-=[]|,./?><";
    private PasswordGenerateService.Builder serviceBuilder;
    @BeforeMethod
    public void setUp(){
        this.serviceBuilder = new PasswordGenerateService.Builder();
    }
    @Test(description = "Generate a fixed-length password. Positive case")
    public void generatePasswordFixedLengthTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(20);
        Assert.assertEquals(password.length(), 20, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a fixed-length password (only lowercase letters). Positive case.")
    public void generatePasswordFixedLengthOnlyLowercaseLettersTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(5);
        Assert.assertEquals(password.length(), 5, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist &&
                !(isUpperCaseExist || isDigitExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a fixed-length password (only uppercase letters). Positive case.")
    public void generatePasswordFixedLengthOnlyUppercaseLettersTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(7);
        Assert.assertEquals(password.length(), 7, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isUpperCaseExist &&
                !(isLowerCaseExist || isDigitExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a fixed-length password (only digits). Positive case.")
    public void generatePasswordFixedLengthOnlyDigitsTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(10);
        Assert.assertEquals(password.length(), 10, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isDigitExist &&
                !(isUpperCaseExist || isLowerCaseExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a fixed-length password (only special symbols). Positive case.")
    public void generatePasswordFixedLengthOnlyPunctuationSymbolsTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(15);
        Assert.assertEquals(password.length(), 15, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isPunctuationSymbolExist &&
                !(isLowerCaseExist || isUpperCaseExist || isDigitExist));
    }
    @Test(description = "Generate a fixed-length password (min length = 4). Positive case")
    public void generatePasswordFixedMinLengthValueTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(4);
        Assert.assertEquals(password.length(), 4, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a fixed-length password (max length = 100). Positive case")
    public void generatePasswordFixedMaxLengthValueTest(){
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(100);
        Assert.assertEquals(password.length(), 100, "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a fixed-length password (length less then min value). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length value: 3")
    public void generatePasswordFixedLengthLessThenMinValueNegativeTest(){
        serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(3);
    }
    @Test(description = "Generate a fixed-length password (length more then max value). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length value: 101")
    public void generatePasswordFixedLengthMoreThenMaxValueNegativeTest(){
        serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(101);
    }
    @Test(description = "Generate a fixed-length password (options for builder wasn't chosen). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options")
    public void generatePasswordFixedLengthDidNotChooseAnyOptionsInBuilderNegativeTest(){
        serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(8);
    }
    @Test(description = "Generate a variable-length password. Positive case")
    public void generatePasswordVariableLengthTest(){
        int minLength = 20;
        int maxLength = 45;
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a variable-length password (only lowercase letters). Positive case.")
    public void generatePasswordVariableLengthOnlyLowercaseLettersTest(){
        int minLength = 15;
        int maxLength = 77;
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist &&
                !(isUpperCaseExist || isDigitExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a variable-length password (only uppercase letters). Positive case.")
    public void generatePasswordVariableLengthOnlyUppercaseLettersTest(){
        int minLength = 33;
        int maxLength = 34;
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(true)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isUpperCaseExist &&
                !(isLowerCaseExist || isDigitExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a variable-length password (only digits). Positive case.")
    public void generatePasswordVariableLengthOnlyDigitsTest(){
        int minLength = 48;
        int maxLength = 95;
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(true)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isDigitExist &&
                !(isUpperCaseExist || isLowerCaseExist || isPunctuationSymbolExist));
    }
    @Test(description = "Generate a variable-length password (only special symbols). Positive case.")
    public void generatePasswordVariableLengthOnlyPunctuationSymbolsTest(){
        int minLength = 12;
        int maxLength = 18;
        String password = serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isPunctuationSymbolExist &&
                !(isLowerCaseExist || isUpperCaseExist || isDigitExist));
    }
    @Test(description = "Generate a variable-length password (min length = 4). Positive case")
    public void generatePasswordVariableMinLengthValueTest(){
        int minLength = 4;
        int maxLength = 5;
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a variable-length password (max length = 100). Positive case")
    public void generatePasswordVariableMaxLengthValueTest(){
        int minLength = 99;
        int maxLength = 100;
        String password = serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
        Assert.assertTrue(password.length() >= minLength && password.length() <= maxLength,
                "Password length incorrect");
        boolean isLowerCaseExist = isSymbolExist(password, LOWERCASE_LETTERS);
        boolean isUpperCaseExist = isSymbolExist(password, UPPERCASE_LETTERS);
        boolean isDigitExist = isSymbolExist(password, DIGITS);
        boolean isPunctuationSymbolExist = isSymbolExist(password, SPECIAL_SYMBOLS);
        Assert.assertTrue(isLowerCaseExist
                && isUpperCaseExist
                && isDigitExist
                && isPunctuationSymbolExist);
    }
    @Test(description = "Generate a variable-length password (min length less then min value). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length values: min = 3, max = 7")
    public void generatePasswordVariableMinLengthLessThenMinValueNegativeTest(){
        int minLength = 3;
        int maxLength = 7;
        serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (max length more then max value). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length values: min = 95, max = 101")
    public void generatePasswordVariableMaxLengthMoreThenMaxValueNegativeTest(){
        int minLength = 95;
        int maxLength = 101;
        serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (min length more then max length). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Incorrect length values: min = 7, max = 5")
    public void generatePasswordVariableMinLengthMoreThenMaxLengthNegativeTest(){
        int minLength = 7;
        int maxLength = 5;
        serviceBuilder
                .useLowerCaseLetters(true)
                .useUpperCaseLetters(true)
                .useDigits(true)
                .useSpecialSymbols(true)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @Test(description = "Generate a variable-length password (options for builder wasn't chosen). Negative case",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Object wasn't built correctly. Add the necessary options")
    public void generatePasswordVariableLengthDidNotChooseAnyOptionsInBuilderNegativeTest(){
        int minLength = 15;
        int maxLength = 19;
        serviceBuilder
                .useLowerCaseLetters(false)
                .useUpperCaseLetters(false)
                .useDigits(false)
                .useSpecialSymbols(false)
                .build()
                .generatePassword(minLength, maxLength);
    }
    @AfterMethod
    public void tearDown(){
        serviceBuilder = null;
    }
    private boolean isSymbolExist(String password, String symbols){
        char[] passwordAsChars = password.toCharArray();
        for (char symbol : passwordAsChars) {
            if(symbols.indexOf(symbol) != -1) return true;
        }
        return false;
    }
}
