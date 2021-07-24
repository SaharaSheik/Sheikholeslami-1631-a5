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
        String description = itemName.getText().trim();
        if(description.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter item name.");
            return;
        }
        //check length is more than 256 length
        if(!Helper.itemNameChecker(description)){
            Helper.showErrorAlert("Error", "Min description length is 2 \n and Max description length is 256.");
            return;
        }

        String serialNumber = itemSerialNumber.getText().trim();

        if(serialNumber.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter item name.");
            return;
        }

//        if(!itemSerialNumberLengthChecker(serialNumber)){
//            showErrorAlert("Error", "Serial Number needs to contain 10 Characters.");
//            return;
//        }

        if(!Helper.itemSerialNumberCharAndDigitChecker (serialNumber)){
            Helper.showErrorAlert("Error", "Serial Number shall be Alphanumeric.");
            return;
        }

        String value_str = itemValue.getText().trim();
        if(value_str.isEmpty()){
            Helper.showErrorAlert("Error", "Please enter value.");
            return;
        }
        double value = 0;
        try{
            value = Double.parseDouble(value_str);
            if(value <= 0){
                Helper.showErrorAlert("Error", "Please enter value over zero.");
                return;
            }
        }catch (NumberFormatException e){
            Helper.showErrorAlert("Error", "You have to enter numerical values.");
            return;
        }
        Item updated_item = new Item();
        updated_item.setName(description);
        updated_item.setSerialNumber(serialNumber);
        updated_item.setValue(value);

        //check same item
        if(!updated_item.getSerialNumber().equals(item.getSerialNumber()) && parent.getItems().contains(updated_item)){
            Helper.showErrorAlert("Error", "Same serial number is existing now...");
            return;
        }

        item.setName(description);
        item.setSerialNumber(serialNumber);
        item.setValue(value);

        Stage stage = (Stage) itemName.getScene().getWindow();
        stage.close();
    }
}

