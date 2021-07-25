package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */


import javafx.scene.control.Alert;

// helper functions

    public class Helper {

        // show error message

        public static void showErrorAlert(String title, String text){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
        }

        // show success messages
        public static void showSuccessAlert(String title, String text){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.showAndWait();
        }

        // item NameChecker checks if the item description  is less than 2 and over 256
        public static boolean itemNameChecker (String Name){
            if (Name.length() > 256 || Name.length() < 2){
                return false;
            }
            return true;
        }

        // checks if the serial number is exactly 10 chars

        public static boolean itemSerialNumberLengthChecker (String serialNumber){
            if (serialNumber.length() != 10){
                return false;
            }
            return true;
        }


        // checks if serial number is alphanumerical

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


