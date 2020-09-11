package com.example.asingtesting;



import java.util.Calendar;
import java.util.Date;

public class historyDetailsClass {
    double Totalprice = 0.00;
    Date orderDate = Calendar.getInstance().getTime();
    String companyName;

    void historyDetails(double totalprice , Date orderDate, String companyName){
        totalprice = this.Totalprice;
        orderDate = this.orderDate;
        companyName = this.companyName;
    }

    public double getTotalprice() {
        return Totalprice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCompanyName() {
        return companyName;
    }
}
