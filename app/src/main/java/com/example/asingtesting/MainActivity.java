package com.example.asingtesting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Initialize Variable
    EditText companyNameTxt,companyAddressTxt,poscodeTxt,companyTypeTxt,companyEmailTxt,
            CompanyOperatingHourTxt,companyWorkingDateTxt;
    Button register;
    AwesomeValidation awesomeValidation;
    ImageView companyLogo ;
    private static final int image = 1;
    Uri uri;
    StorageReference ImgRef;
    FirebaseStorage ImgStorage;
    String companyname = "defualt";
    String url;
    String companyaddress,companytype,companyemail,companyoperatinghour,companyworkingdate;
    companyRegisterClass companyRegister;
    DatabaseReference myRef;
    int companyposcode;

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
            uri = data.getData();
            companyLogo.setImageURI(uri);
        }
    }

    private void uploadImg() {
        final ProgressDialog PD = new ProgressDialog(this);
        PD.setTitle("Uploading Image...");
        StorageReference riversRef = ImgRef.child(companyname);
        PD.show();

        riversRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        PD.dismiss();
                        Snackbar.make(findViewById(R.id.Register), "Image Uploaded Successfully", Snackbar.LENGTH_LONG).show();
                        ImgRef.child(companyname).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Toast.makeText(getApplicationContext(), "get URL successfull", Toast.LENGTH_LONG).show();
                                url = uri.toString();
                                companyRegister.companyRegister(companyname,companyaddress,companytype,companyemail,companyoperatinghour,companyworkingdate,companyposcode,url);
                                DatabaseReference newRef = myRef.push();
                                newRef.setValue(companyRegister);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getApplicationContext(), "get URL failed", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(), "Image Upload Failed", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercentage = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        PD.setMessage("Progress: " + (int) progressPercentage + "%" );
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int img = R.drawable.camara;
        ImgStorage = FirebaseStorage.getInstance();
        ImgRef = ImgStorage.getReference("Company List");
        Intent intent = new Intent(this,Company_Main_Page.class);
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
                //check Validation
                if (awesomeValidation.validate()){
                    companyname = companyNameTxt.getText().toString();
                    companyaddress = companyAddressTxt.getText().toString();
                    companytype = companyTypeTxt.getText().toString();
                    companyemail = companyEmailTxt.getText().toString();
                    companyoperatinghour = CompanyOperatingHourTxt.getText().toString();
                    companyworkingdate = companyWorkingDateTxt.getText().toString();
                    companyposcode = Integer.parseInt(poscodeTxt.getText().toString());
                    uploadImg();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    myRef = database.getReference("company Details");
                    companyRegister = new companyRegisterClass();
                    Toast.makeText(getApplicationContext(),"Register Successfull",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Register Failed",Toast.LENGTH_SHORT).show();
                }
                gotomainpage();
            }
        });
        companyLogo.setOnClickListener(this);

    }

    void gotomainpage()
    {
        Intent intent = new Intent(this,Company_Main_Page.class);
        startActivity(intent);
    }
}