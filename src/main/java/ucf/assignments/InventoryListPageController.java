package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 first_name last_name
 */

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class InventoryListPageController  implements Initializable {

    public void addItem(MouseEvent mouseEvent) {
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
}
