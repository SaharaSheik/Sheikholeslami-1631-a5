package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Sahar Sheikholeslami
 */
import java.io.Serializable;

public class Item  implements Serializable {

    String name;
    String serialNumber;
    double Value;

    public Item() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        return serialNumber.equals(((Item)obj).getSerialNumber());
    }

    @Override
    public String toString(){
        return "Name: " + name + "\n" + "Serial Number: " + serialNumber + "\n" + "Value: " + "$" + Value;
    }
}


