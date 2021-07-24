package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InventoryListPageController implements Initializable {

    @FXML
    private TextField itemName ;
    @FXML
    private TextField itemSerialNumber;
    @FXML
    private TextField itemValue;
    @FXML
    private TextField searchItem;

    @FXML
    private TableView<Item> itemListView ;
    @FXML private TableColumn<Item, String> colName;
    @FXML private TableColumn<Item, String> colSerial;
    @FXML private TableColumn<Item, Double> colValue;

    @FXML
    RadioButton nameRadio;

    @FXML
    RadioButton serialRadio;

    @FXML
    RadioButton valueRadio;

    private ObservableList<Item> dataList;
    private FilteredList<Item> filteredList;
    private SortedList<Item> sortableData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        //add listener for dynamic search
        searchItem.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            filter();
        });
    }
    //initialize table view
    private void initTable(){
        colName.setCellValueFactory(new PropertyValueFactory<Item, String>("Name"));
        colSerial.setCellValueFactory(new PropertyValueFactory<Item, String>("SerialNumber"));
        colValue.setCellValueFactory(new PropertyValueFactory<Item, Double>("Value"));

        dataList = FXCollections.observableArrayList();
        filteredList = new FilteredList<>(dataList, p -> true);
        sortableData = new SortedList<>(filteredList);
        itemListView.setItems(sortableData);
        sortableData.comparatorProperty().bind(itemListView.comparatorProperty());
        filteredList.addListener((Observable o) -> {
            Platform.runLater(() -> {

            });
        });
    }
    @FXML
    public void addItem() {
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

        if(!Helper.itemSerialNumberLengthChecker(serialNumber)){
            Helper.showErrorAlert("Error", "Serial Number needs to contain 10 Characters.");
            return;
        }

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
        Item item = new Item();
        item.setName(description);
        item.setSerialNumber(serialNumber);
        item.setValue(value);

        //check same item
        if(dataList.contains(item)){
            Helper.showErrorAlert("Error", "Same serial number is existing now...");
            return;
        }
        //check item list capacity
        if(dataList.size() >= 100){
            Helper.showErrorAlert("Error", "Max capacity is 100.\nYou can not add anymore from now.");
            return;
        }
        dataList.add(item);

        //ready to enter new item
        itemName.setText("");
        itemSerialNumber.setText("");
        itemValue.setText("");
    }

    private void filter(){
        final String searchKey = searchItem.getText().toString().trim();
        filteredList.setPredicate(model -> {
            if(searchKey.isEmpty()){
                return true;
            }
            if(nameRadio.isSelected()){
                if(!model.getName().trim().toLowerCase().startsWith(searchKey)){
                    return false;
                }
            }else if(serialRadio.isSelected()){
                if(!model.getSerialNumber().trim().toLowerCase().startsWith(searchKey)){
                    return false;
                }
            }else if(valueRadio.isSelected()){
                if(!String.valueOf(model.getValue()).startsWith(searchKey)){
                    return false;
                }
            }
            return true;
        });
    }

    @FXML
    public void deleteItem() {
        Item item = itemListView.getSelectionModel().getSelectedItem();
        if(item == null) return;
        //bufferList.remove(item);
        dataList.remove(item);
    }

    @FXML
    public void editItem() throws IOException {

        Item item = itemListView.getSelectionModel().getSelectedItem();
        if(item == null) return;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditItemPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();

        EditItemPageController controller = fxmlLoader.getController();
        controller.setItem(this, item);

        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Edit Item Page");
        stage.setResizable(false);
        stage.setScene(new Scene(root1));
        stage.showAndWait();

        itemListView.refresh();
    }

    @FXML
    public void saveItems() {
        if(dataList == null || dataList.isEmpty()) return;

        try{
            FileChooser fileChooser = new FileChooser();
            // Set extension filter
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Json", "*.json"),
                    new FileChooser.ExtensionFilter("TSV", "*.txt"),
                    new FileChooser.ExtensionFilter("html", "*.html")
            );

            // Show open file dialog
            File file = fileChooser.showSaveDialog(itemName.getScene().getWindow());
            if (file != null) {
                String extension = fileChooser.getSelectedExtensionFilter().getExtensions().get(0);
                if(extension.equals("*.json")){
                    if(saveJson(file)){
                        Helper.showSuccessAlert("Success","Success to save items");
                    }else{
                        Helper.showErrorAlert("Error","Fail to save items");
                    }
                }else if(extension.equals("*.txt")){
                    if(saveTSV(file)){
                        Helper.showSuccessAlert("Success","Success to save items");
                    }else{
                        Helper.showErrorAlert("Error","Fail to save items");
                    }
                }else if(extension.equals("*.html")){
                    if(saveHtml(file)){
                        Helper.showSuccessAlert("Success","Success to save items");
                    }else{
                        Helper.showErrorAlert("Error","Fail to save items");
                    }
                }
            }
        }catch (Exception e){
            Helper.showErrorAlert("Error","Fail to save items");
        }
    }

    public ObservableList<Item> getItems(){
        return this.dataList;
    }

    private boolean saveJson(File file){
        try{
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Item>>() {}.getType();
            Writer writer = new FileWriter(file);
            gson.toJson(getItems(), listType, writer);
            writer.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private boolean saveTSV(File file){
        try{
            Writer writer = new FileWriter(file);
            PrintWriter dos = new PrintWriter(writer);
            for(Item item : dataList){
                String row = item.getName() + "\t" + item.getSerialNumber() + "\t" + item.getValue();
                dos.println(row);
            }
            writer.close();
            dos.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private boolean saveHtml(File file){
        try{
            Writer writer = new FileWriter(file);
            PrintWriter dos = new PrintWriter(writer);
            dos.println("<html><body><table>");
            for(Item item : dataList){
                dos.println("<tr>");
                dos.print("<td>");
                dos.print(item.getName());
                dos.println("</td>");
                dos.print("<td>");
                dos.print(item.getSerialNumber());
                dos.println("</td>");
                dos.print("<td>");
                dos.print(item.getValue());
                dos.println("</td>");
                dos.println("</tr>");
            }
            dos.println("</table></body></html>");
            writer.close();
            dos.close();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @FXML
    public void loadItems() {
        try {
            FileChooser fileChooser = new FileChooser();
            // Set extension filter
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Json", "*.json"),
                    new FileChooser.ExtensionFilter("TSV", "*.txt"),
                    new FileChooser.ExtensionFilter("html", "*.html")
            );

            // Show open file dialog
            File file = fileChooser.showOpenDialog(itemName.getScene().getWindow());

            if (file != null) {
                String extension = fileChooser.getSelectedExtensionFilter().getExtensions().get(0);
                if(extension.equals("*.json")){
                    List<Item> itemList = readJson(file);
                    if(itemList != null){
                        dataList.clear();
                        dataList.addAll(itemList);
                    }
                }else if(extension.equals("*.txt")){
                    List<Item> itemList = readTSV(file);
                    if(itemList != null){
                        dataList.clear();
                        dataList.addAll(itemList);
                    }
                }else if(extension.equals("*.html")){
                    List<Item> itemList = readHtml(file);
                    if(itemList != null){
                        dataList.clear();
                        dataList.addAll(itemList);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    private List<Item> readJson(File file){
        try{
            Type listOfMyClassObject = new TypeToken<ArrayList<Item>>() {}.getType();
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(file));
            List<Item> itemList = gson.fromJson(reader, listOfMyClassObject);
            return itemList;
        }catch (Exception e){
            return null;
        }
    }
    private List<Item> readTSV(File file){
        try{
            List<Item> itemList = new ArrayList<>();
            try (BufferedReader TSVReader = new BufferedReader(new FileReader(file))) {
                String line = null;
                while ((line = TSVReader.readLine()) != null) {
                    String[] lineItems = line.split("\t");
                    if(lineItems.length != 3) continue;
                    Item item = new Item();
                    item.setName(lineItems[0]);
                    item.setSerialNumber(lineItems[1]);
                    try{
                        item.setValue(Double.parseDouble(lineItems[2]));
                    }catch (Exception e){
                        continue;
                    }
                    itemList.add(item);
                }
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
            return itemList;
        }catch (Exception e){
            return null;
        }
    }
    private List<Item> readHtml(File file){
        try{
            List<Item> itemList = new ArrayList<>();
            Document doc = Jsoup.parse(file, null);
            Elements elements = doc.getElementsByTag("tr");
            for(Element element : elements){
                Elements rows = element.getElementsByTag("td");
                Item item = new Item();
                try{
                    item.setName(rows.get(0).text());
                    item.setSerialNumber(rows.get(1).text());
                    item.setValue(Double.parseDouble(rows.get(2).text()));
                }catch (Exception e){
                    continue;
                }
                itemList.add(item);
            }
            return itemList;
        }catch (Exception e){
            return null;
        }
    }
}
