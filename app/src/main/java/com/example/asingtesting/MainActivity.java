package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    //Initialize Variable
    EditText companyNameTxt,companyAddressTxt,poscodeTxt,companyTypeTxt,companyPhoneTxt,
            CompanyOperatingHourTxt,companyWorkingDateTxt;
    Button register;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        companyNameTxt = findViewById(R.id.company_name_txt);
        companyAddressTxt = findViewById(R.id.company_address_txt);
        poscodeTxt = findViewById(R.id.poscode_txt);
        companyTypeTxt = findViewById(R.id.company_type_txt);
        companyPhoneTxt = findViewById(R.id.company_phone_txt);
        CompanyOperatingHourTxt = findViewById(R.id.Company_Operating_Hour_txt);
        companyWorkingDateTxt = findViewById(R.id.company_working_date_txt);
        register = findViewById(R.id.Register);

        //Initialize Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation
        //Name
        awesomeValidation.addValidation(this,R.id.company_name_txt, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //Phone Number
        awesomeValidation.addValidation(this,R.id.company_phone_txt,"^(\\+?6?01)[0|1|2|3|4|6|7|8|9]\\-*[0-9]{7,8}$",R.string.invalid_phone_number);
        //Email Address
        awesomeValidation.addValidation(this,R.id.company_address_txt,RegexTemplate.NOT_EMPTY,R.string.invalid_Email_Address);
        //Poscode
        awesomeValidation.addValidation(this,R.id.poscode_txt,".{5,}",R.string.invalid_poscode);
        //Company Type
        awesomeValidation.addValidation(this,R.id.company_type_txt,RegexTemplate.NOT_EMPTY,R.string.invalid_company_type);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check Validation
                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(),"Register Successfull",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Register Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}