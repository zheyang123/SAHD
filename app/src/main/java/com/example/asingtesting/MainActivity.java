package com.example.asingtesting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Initialize Variable
    EditText companyNameTxt,companyAddressTxt,poscodeTxt,companyTypeTxt,companyEmailTxt,
            CompanyOperatingHourTxt,companyWorkingDateTxt;
    Button register;
    AwesomeValidation awesomeValidation;
    ImageView companyLogo ;
    private static final int image = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId())
        { case R.id.Company_Logo:
            Intent intent = new Intent(
              Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            );
            startActivityForResult(intent,image);
            break;}

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == image && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            companyLogo.setImageURI(uri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int img = R.drawable.camara;
        Intent intent = new Intent(this,login.class);
        startActivity(intent);


        //Assign Variable
        companyNameTxt = findViewById(R.id.company_name_txt);
        companyAddressTxt = findViewById(R.id.company_address_txt);
        poscodeTxt = findViewById(R.id.poscode_txt);
        companyTypeTxt = findViewById(R.id.company_type_txt);
        companyEmailTxt = findViewById(R.id.company_Email_txt);
        CompanyOperatingHourTxt = findViewById(R.id.Company_Operating_Hour_txt);
        companyWorkingDateTxt = findViewById(R.id.company_working_date_txt);
        register = findViewById(R.id.Register);
        companyLogo = findViewById(R.id.Company_Logo);
        companyLogo.setImageResource(img);

        //Initialize Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add Validation
        //Name
        awesomeValidation.addValidation(this,R.id.company_name_txt, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        //Phone Number
        awesomeValidation.addValidation(this,R.id.company_Email_txt, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        //Email Address
        awesomeValidation.addValidation(this,R.id.company_address_txt,RegexTemplate.NOT_EMPTY,R.string.invalid_Email_Address);
        //Poscode
        awesomeValidation.addValidation(this,R.id.poscode_txt,".{5,}",R.string.invalid_poscode);
        //Company Type
        awesomeValidation.addValidation(this,R.id.company_type_txt,RegexTemplate.NOT_EMPTY,R.string.invalid_company_type);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyname,companyaddress,companytype,companyemail,companyoperatinghour,companyworkingdate;
                int companyposcode;
                companyname = companyNameTxt.getText().toString();
                companyaddress = companyAddressTxt.getText().toString();
                companytype = companyTypeTxt.getText().toString();
                companyemail = companyEmailTxt.getText().toString();
                companyoperatinghour = CompanyOperatingHourTxt.getText().toString();
                companyworkingdate = companyWorkingDateTxt.getText().toString();
                companyposcode = Integer.parseInt(poscodeTxt.getText().toString());
                //check Validation
                if (awesomeValidation.validate()){
                    Toast.makeText(getApplicationContext(),"Register Successfull",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Register Failed",Toast.LENGTH_SHORT).show();
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("company Details");
                companyRegisterClass companyRegister = new companyRegisterClass();
                companyRegister.companyRegister(companyname,companyaddress,companytype,companyemail,companyoperatinghour,companyworkingdate,companyposcode);
                DatabaseReference newRef = myRef.push();
                newRef.setValue(companyRegister);
            }
        });
        companyLogo.setOnClickListener(this);

    }
}