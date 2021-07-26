package ucf.assignments;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListPageControllerTest {

    File file;
    InventoryListPageController inventoryListPageController = new InventoryListPageController();


    @Test
    void saveJsonNull() {

    // since file is pointing to NULL saveJson should return false. I am expecting false
        boolean saved = inventoryListPageController.saveJson(file);
        assertFalse(saved);
    }

    @Test

    void saveJsonSaved() {

        File file = new File ("src/test/resources/Files/jsonfile.json");

        // since file is pointing to a json file saved in test resources file saveJson should return true. I am expecting true
        boolean saved = inventoryListPageController.saveJson(file);
        assertTrue(saved);
    }

    @Test
    void saveTSV() {


        // since file is pointing to NULL saveTSV should return false. I am expecting false
        boolean saved = inventoryListPageController.saveTSV(file);
        assertFalse(saved);
    }


    @Test
    void saveHtml() {

        // since file is pointing to NULL saveTSV should return false. I am expecting false
        boolean saved = inventoryListPageController.saveHtml(file);
        assertFalse(saved);
    }



    @Test
    void readJson() {

        // having the readJson read from a test file in the resources
        // reading from the file, and returning a List of items.  I am expecting the first element of
        //the list to contain an item with the name item1.
        // Assert true if list[0].name equals to item1

        List<Item> itemList = new ArrayList<>();
        File file = new File ("src/test/resources/Files/jsontest.json");

        itemList = inventoryListPageController.readJson(file);

        String expectedItemName ="item1";
        String actualItemName = itemList.get(0).getName();

        Assertions.assertTrue(expectedItemName.equals(actualItemName));


    }

    @Test
    void readTSV() {

        // having the readTSV read from a test file in the resources
        // reading from the file, and returning a List of items.  I am expecting the first element of
        //the list to contain an item with the name item1.
        // Assert true if list[0].name equals to item1

        List<Item> itemList = new ArrayList<>();
        File file = new File ("src/test/resources/Files/txtload.txt");

        itemList = inventoryListPageController.readTSV(file);

        String expectedItemName ="item1";


        String actualItemName = itemList.get(0).getName();
        System.out.println(actualItemName);
     Assertions.assertTrue(expectedItemName.equals(actualItemName));
    }

    @Test
    void readHtml() {

        // having the htmlRead read from a test file in the resources
        // reading from the file, and returning a List of items.  I am expecting the first element of
        //the list to contain an item with the name item1.
        // Assert true if list[0].name equals to item1

        List<Item> itemList = new ArrayList<>();
        File file = new File ("src/test/resources/Files/htmlload.html");

        itemList = inventoryListPageController.readHtml(file);

        String expectedItemName ="item1";


        String actualItemName = itemList.get(0).getName();
        System.out.println(actualItemName);
        Assertions.assertTrue(expectedItemName.equals(actualItemName));
    }
}