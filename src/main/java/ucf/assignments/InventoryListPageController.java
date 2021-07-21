package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class InventoryListPageController implements Initializable {

    @FXML
    private TextField itemName ;
    @FXML
    private TextField itemSerialNumber;
    @FXML
    private TextField itemValue;

    @FXML
    private TableView<Item> itemTableView ;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Item> items = loadItems();
        if(items != null){
            itemTableView.getItems().addAll(items);
        }
    }


    @FXML
    public void addItem(MouseEvent mouseEvent) {

        String description = itemName.getText().trim();


        if(description.isEmpty()){
            showErrorAlert("Error", "Please enter item name.");
            return;
        }
        //check length is more than 256 length
        if(!itemNameChecker(description)){
            showErrorAlert("Error", "Min description length is 2 \n and Max description length is 257.");
            return;
        }

        String serialNumber = itemSerialNumber.getText().trim();

        if(serialNumber.isEmpty()){
            showErrorAlert("Error", "Please enter item name.");
            return;
        }

        if(!itemSerialNumberLengthChecker(serialNumber)){

            showErrorAlert("Error", "Serial Number needs to contain 10 Characters.");
            return;

        }

        if(!itemSerialNumberCharAndDigitChecker (serialNumber)){

            showErrorAlert("Error", "Serial Number shall be Alphanumeric.");
            return;

        }



        Item item = new Item();
        item.setName(description);
        item.setSerialNumber(serialNumber);
        item.setValue(Double.parseDouble(itemValue.getText()));

        //check item list capacity
        if(itemTableView.getItems().size() >= 100){
            showErrorAlert("Error", "Max capacity is 100.\nYou can not add anymore from now.");
            return;
        }
        itemTableView.getItems().add(item);

        //ready to enter new item
        itemName.setText("");
        itemSerialNumber.setText("");
        itemValue.setText("");
    }

    public boolean itemNameChecker (String Name){

        if (Name.length()>257 && Name.length()<2)
            return false;

        return true;
    }

    public boolean itemSerialNumberLengthChecker (String serialNumber){

        if (serialNumber.length() != 10)
            return false;

        return true;
    }

    public boolean itemSerialNumberCharAndDigitChecker (String serialNumber){

        for (int i=0; i<serialNumber.length(); i++) {
            char x = serialNumber.charAt(i);
            boolean letterOrDigit = Character.isLetterOrDigit(x);
            if (!letterOrDigit)
                return false;
        }
        return true;
    }

    public boolean itemSerialNumberIsUniqueChecker(String serialNumber){

        if ()
        return true;
    }
    public void searchItem(MouseEvent mouseEvent) {
    }

    public void deleteItem(MouseEvent mouseEvent) {
    }

    public void editItem(MouseEvent mouseEvent) {
    }

    public void saveItems(MouseEvent mouseEvent) {
    }

    private void showErrorAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
    private void showSuccessAlert(String title, String text){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public List<Item> loadItems() {
        try {
            FileInputStream fi = new FileInputStream("Database/items");
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            List<Item> items = (List<Item>) oi.readObject();
            oi.close();
            fi.close();

            return items;
        } catch (Exception e) {
            return null;
        }
    }
}
