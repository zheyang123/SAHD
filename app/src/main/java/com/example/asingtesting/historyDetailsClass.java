package com.example.asingtesting;



import java.util.Calendar;
import java.util.Date;

public class historyDetailsClass {
    private double totalprice = 0.00;
    private Date orderDate = Calendar.getInstance().getTime();
    String companyName;

    void historyDetails(double totalprice , Date orderDate, String companyName){
        this.totalprice= totalprice ;
       this.orderDate = orderDate;
        this.companyName =companyName;

    }

    public double getTotalprice() {
        return totalprice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCompanyName() {
        return companyName;
    }
}
