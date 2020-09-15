package com.example.asingtesting;

public class companyRegisterClass{
    String company_name,company_address,company_type,company_email,company_operating_hour,company_working_date,url;
    int company_poscode;

    void companyRegister(String companyName,String companyAddress,String companyType,
                         String companyEmail, String companyOperatingHour,
                         String companyWorkingDate, int companyPoscode, String url)
    {
        this.company_name = companyName;
        this.company_address = companyAddress;
        this.company_type = companyType;
        this.company_email = companyEmail;
        this.company_operating_hour = companyOperatingHour;
        this.company_working_date = companyWorkingDate;
        this.company_poscode = companyPoscode;
        this.url = url;
    }
    public int getCompany_poscode() {return company_poscode;}
    public  String getCompany_name() {return company_name;}
    public  String getCompany_address() {return company_address;}
    public  String getCompany_type() {return company_type;}
    public  String getCompany_email() {return company_email;}
    public  String getCompany_operating_hour() {return company_operating_hour;}
    public  String getCompany_working_date() {return company_working_date;}
    public  String geturl() {return url;}
}
