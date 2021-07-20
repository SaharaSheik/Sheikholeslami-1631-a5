package ucf.assignments;

public class Item {

    String name;
    String serialNumber;
    Double Value;

    public Item(String name, String serialNumber, Double value) {
        this.name = name;
        this.serialNumber = serialNumber;
        Value = value;
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

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }
}


