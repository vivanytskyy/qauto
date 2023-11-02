package com.gmail.ivanytskyy.vitaliy.ui.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.00
 * @date 01/11/2023
 */
public class InstructionsDataProviders {
    @DataProvider
    public static Object[][] numberOfInstructionsProviderPositiveCase(){
        return new Object[][]{
                {"Fiat", "Scudo", 6},
                {"Audi", "A6",    13},
                {"BMW",  "Z3",    0}
        };
    }
    @DataProvider
    public static Object[][] nameOfInstructionsProviderPositiveCase(){
        return new Object[][]{
                {"BMW",      "X5",      3,  "Front coil springs on BMW X5"},
                {"Ford",     "Fiesta",  12, "Rear suspension strut on Ford Fiesta"},
                {"Porsche", "Panamera", 1,  "Front brake discs on Porsche Panamera"},
                {"Fiat",    "Palio",    13, "Spark plugs on Fiat Palio"}
        };
    }
    @DataProvider
    public static Object[][] invalidInstructionNumbersProviderNegativeCase(){
        return new Object[][]{
                {"Audi", "A8",    -1},
                {"Audi", "R8",    0},
                {"BMW",  "Z3",    1},
                {"Ford", "Focus", 6},
                {"Fiat", "Palio", 14}
        };
    }
}