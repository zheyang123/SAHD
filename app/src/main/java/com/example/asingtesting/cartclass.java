package com.example.asingtesting;

public class cartclass {
   private String productname;
  private double price;
  private String companyName;
    public void setcart(String productname,double price,String companyName){
        this.productname = productname;
        this.price = price;
        this.companyName= companyName;
    }
  public String getProductname(){
        return productname;
  }
  public double getprice(){
        return price;
  }
  public String companyName(){
        return companyName;
  }
}
