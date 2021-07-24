package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditItemPageController implements Initializable {
    @FXML
    TextField itemName;

    @FXML
    TextField itemSerialNumber;

    @FXML
    TextField itemValue;

    Item item;
    InventoryListPageController parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setItem(InventoryListPageController parent, Item item){
        this.parent = parent;
        this.item = item;
        this.itemName.setText(item.getName());
        this.itemSerialNumber.setText(item.getSerialNumber());
        this.itemValue.setText(String.valueOf(item.getValue()));
    }
    @FXML

    public void updateItem() {

        // trim the text to check for requirements of a item description

        String description = itemName.getText().trim();

        if(description.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter item name.");
            return;
        }

        //check length is more than 256 length
        if(!Helper.itemNameChecker(description)){
            Helper.showErrorAlert("Error", "Min description length should be 2 \nand Max description length should be 256.");
            return;
        }

        String serialNumber = itemSerialNumber.getText().trim();

        if(serialNumber.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter a valid serial number.");
            return;
        }

       if(!Helper.itemSerialNumberLengthChecker(serialNumber)){
           Helper.showErrorAlert("Error", "Serial Number needs to contain 10 Characters.");
        return;
     }

        if(!Helper.itemSerialNumberCharAndDigitChecker (serialNumber)){
            Helper.showErrorAlert("Error", "Serial Number shall be Alphanumeric.");
            return;
        }

        String valueString = itemValue.getText().trim();

        if(valueString.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter value.");
            return;
        }

        double value = 0;
        try{
            value = Double.parseDouble(valueString);
            if(value <= 0){
                Helper.showErrorAlert("Error", "Please enter a value greater than zero.");
                return;
            }
        }catch (NumberFormatException e){
            Helper.showErrorAlert("Error", "You have to enter a numerical value.");
            return;
        }

        Item updatedItem = new Item();
        updatedItem.setName(description);
        updatedItem.setSerialNumber(serialNumber);
        updatedItem.setValue(value);

        //check if the modified serial number in the edit screen duplicates any of the previously entered serial number
        if(!updatedItem.getSerialNumber().equals(item.getSerialNumber()) && parent.getItems().contains(updatedItem)){
            Helper.showErrorAlert("Error", "Duplicate serial number was entered.\nPlease enter a unique Serial Number");
            return;
        }

        item.setName(description);
        item.setSerialNumber(serialNumber);
        item.setValue(value);

        Stage stage = (Stage) itemName.getScene().getWindow();
        stage.close();
    }
}

