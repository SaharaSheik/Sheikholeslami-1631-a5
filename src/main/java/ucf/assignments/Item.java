package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */

import java.io.Serializable;

public class Item  implements Serializable {
    // each item object will have 3 fields, an item name, serial number, value

    String name;
    String serialNumber;
    double Value;

    public Item() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        // use keyword this. to set the itemName from InventoryPage GUI

        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        // use keyword this. to set the itemSerialNumber from InventoryPage GUI

        this.serialNumber = serialNumber;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        // use keyword this. to set the itemValue from InventoryPage GUI

        Value = value;
    }

    public String getDollarValue(){

        // formatimg the dollar value to 2 decimal
        return String.format("$%.2f", Value);
    }

    @Override
    public boolean equals(Object obj) {
        // this function will check to see if the new item has duplicated serial numbers

        if(obj == null) return false;
        return serialNumber.equals(((Item)obj).getSerialNumber());
    }

    @Override
    public String toString(){
        return "Name: " + name + "\n" + "Serial Number: " + serialNumber + "\n" + "Value: " + "$" + Value;
    }
}
