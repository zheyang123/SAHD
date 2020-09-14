package com.example.asingtesting;

public class Product_List_class {
    private String image;
    private String productName;
    private double price;

    public String getImage() {
        return image;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void setProduct(String image, String name, double price) {
        this.image = image;
        this.productName = name;
        this.price = price;
    }

}
