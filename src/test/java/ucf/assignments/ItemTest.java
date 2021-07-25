package ucf.assignments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    protected static Item item = new Item();


    @Test
    void getName() {

        // getting an item name based on a test value
        // I expect my test value will be the same as item name
        // I first set the item description based on the test value and then get the item description via item's get description method
        // compare if test string and item description are equal

        String test = "test item";
        item.setName(test);
        String itemDescription = item.getName();
        Assertions.assertTrue(test.equals(itemDescription));
    }

    @Test
    void setName() {

        // setting an item name based on a test value
        // I expect my test value will be the same as item name
        // I get the item name via item's getName method
        // compare if test string and item name are equal

        String test = "test item";
        item.setName(test);
        String itemName = item.getName();
        Assertions.assertTrue(test.equals(itemName));
    }

    @Test
    void getSerialNumber() {

        // getting an item serialNumber based on a serialNumber value
        // I expect my serialNumber  value will be the same as item serialNumber
        // I first set the item serialNumber  via setSerialNumber, then
        // get the item serialNumber via item's getSerialNumber  method
        // compare if test serialNumber string and item serialNumber string are equal

        String testSerialNumber = "1234567890";
        item.setSerialNumber(testSerialNumber);
        String itemSerialNumber = item.getSerialNumber();
        Assertions.assertTrue(testSerialNumber.equals(itemSerialNumber));
    }

    @Test
    void setSerialNumber() {

        // setting an item serialNumber based on a serialNumber value
        // I expect my serialNumber value will be the same as item serialNumber
        // I first set the item serialNumber  via setSerialNumber, then
        // get the item serialNumber via item's getSerialNumber  method
        // compare if test serialNumber string and item serialNumber string are equal

        String testSerialNumber = "1234567890";
        item.setSerialNumber(testSerialNumber);
        String itemSerialNumber = item.getSerialNumber();
        Assertions.assertTrue(testSerialNumber.equals(itemSerialNumber));
    }

    @Test
    void getValue() {

        // getting an item value based on a item value
        // I expect my item value will be the same as item value
        // I first set the item value  via setValue method, then
        // get the item value via item's getvalue  method
        // compare if test value and item value  are equal

        double testValue = 24.6789;

        item.setValue(testValue);

        double actualValue = item.getValue();

        Assertions.assertTrue(actualValue == testValue);

    }

    @Test
    void setValue() {


        // setting an item value based on a item value
        // I expect my item value will be the same as item value
        // I first set the item value  via setValue method, then
        // get the item value via item's getvalue  method
        // compare if test value and item value  are equal

        double testValue = 24.6789;

        item.setValue(testValue);

        double actualValue = item.getValue();

        Assertions.assertTrue(actualValue == testValue);

    }


    @Test
    void testEquals() {

        // I am expecting the equal function to return false since the item has only one serial number and not duplicated

        boolean test = equals(item);
        assertFalse(test);
    }


    @Test
    void testToString() {

        //I expect  the Override ToString to return:
       // "Name: " + itemName + "\n" + "Serial Number: " + itemSerialNumber + "\n" + "Value: " + "$" + itemValue;

        String itemName = "test item";
        String itemSerialNumber = "1234567890";
        double itemValue = 45.56;

        item.setName(itemName);
        item.setSerialNumber(itemSerialNumber);
        item.setValue(itemValue);

        String test1 = "Name: " + itemName + "\n" + "Serial Number: " + itemSerialNumber + "\n" + "Value: " + "$" + itemValue;


        Assertions.assertTrue(test1.equals(item.toString()));


    }
}