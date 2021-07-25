package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelperTest {


    protected static Item item = new Item();

    String shortString = "1";
    String longString = "javaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    String acceptableString = "Acceptable item description shall be more than 2 and not exceed 256 chars";

    String shortSerialNumber = "12345";
    String longSerialNumber = "1234567890123456";
    String nonAlphaNumericalSerialNumber = "1234567$#@";
    String acceptableSerialNumber = "123456abcd";


    @Test
    void itemNameCheckerShortItemName() {

        // I expect the itemNameChecker method to return false if the string is less than 2 chars

        boolean resultShortString = Helper.itemNameChecker(shortString);
        assertFalse(resultShortString);

    }


    @Test
    void itemNameCheckerLongItemName() {

        // I expect the itemNameChecker method to return false if the string is more than 256 chars

        boolean resultLongString = Helper.itemNameChecker(longString);
        assertFalse(resultLongString);

    }


    @Test
    void itemNameCheckerAcceptableItemName() {

        // I expect the itemNameChecker method to return true if the string is between 2 and 256 chars

        boolean resultAcceptableString = Helper.itemNameChecker(acceptableString);
        assertTrue(resultAcceptableString);

    }

    @Test
    void itemSerialNumberLengthCheckerShortSerialNumber() {


        // I expect the itemSerialNumberLengthChecker method to return false if the string is less than 10 chars

        boolean resultShortString = Helper.itemSerialNumberLengthChecker(shortSerialNumber);
        assertFalse(resultShortString);

    }


    @Test
    void itemSerialNumberLengthCheckerLongSerialNumber() {


        // I expect the itemSerialNumberLengthChecker method to return false if the string is more than 10 chars

        boolean resultLongSerialNumber = Helper.itemSerialNumberLengthChecker(longSerialNumber);
        assertFalse(resultLongSerialNumber);

    }


    @Test
    void itemSerialNumberLengthCheckerAcceptableSerialNumber() {


        // I expect the itemSerialNumberLengthChecker method to return true if the serialNumber is exactly 10 chars

        boolean resultAcceptableSerialNumber = Helper.itemSerialNumberLengthChecker(acceptableSerialNumber);
        assertTrue(resultAcceptableSerialNumber);

    }


    @Test
    void itemSerialNumberCharAndDigitCheckerIsAlphaNumeric() {

        // I expect the itemSerialNumberCharAndDigitChecker method to return true if the serialNumber is  alphaNumerical


        boolean resultAcceptableSerialNumber = Helper.itemSerialNumberCharAndDigitChecker(acceptableSerialNumber);
        assertTrue(resultAcceptableSerialNumber);

    }


    @Test
    void itemSerialNumberCharAndDigitCheckerIsNotAlphaNumeric() {

        // I expect the itemSerialNumberCharAndDigitChecker method to return false if the serialNumber is not alphaNumerical
        // for example if it has special charactors,...

        boolean resultNonAlphaNumericSerialNumber = Helper.itemSerialNumberCharAndDigitChecker(nonAlphaNumericalSerialNumber);
        assertFalse(resultNonAlphaNumericSerialNumber);

    }


}