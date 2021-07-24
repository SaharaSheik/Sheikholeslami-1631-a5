package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */
import javafx.scene.control.Alert;


    public class Helper {

        public static void showErrorAlert(String title, String text){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
        }
        public static void showSuccessAlert(String title, String text){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
        }

        public static boolean itemNameChecker (String Name){
            if (Name.length() > 256 || Name.length() < 2){
                return false;
            }
            return true;
        }

        public static boolean itemSerialNumberLengthChecker (String serialNumber){
            if (serialNumber.length() != 10){
                return false;
            }
            return true;
        }

        public static boolean itemSerialNumberCharAndDigitChecker (String serialNumber){

            for (int i=0; i<serialNumber.length(); i++) {
                char x = serialNumber.charAt(i);
                boolean letterOrDigit = Character.isLetterOrDigit(x);
                if (!letterOrDigit){
                    return false;
                }
            }
            return true;
        }
    }


