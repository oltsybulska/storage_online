package org.example.storage_online.model;

public class Item {
    private String name;
    private String designer;
    private double price;


    public Item(String name, String designer, double price) {
        this.name = name;
        this.designer = designer;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
