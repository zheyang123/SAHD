package com.example.asingtesting;

public class ProductInfoClass {
    private String productName;
    private String description;
    private double price;
    public void setInfo(String product_name, String description, double price)
    {
        this.productName = product_name;
        this.description = description;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
