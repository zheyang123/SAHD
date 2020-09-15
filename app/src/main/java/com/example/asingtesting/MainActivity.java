package com.example.asingtesting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private static final int Result_Image = 1;
Uri selected_image;
String image_name = "i_name";
String product_name;
String url;
String companyName = "watson";
double price = 200.00;
private FirebaseStorage storage;
private StorageReference storageReference;

    ImageView imageView;
    Button button;
    int camera = R.drawable.camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,Company_Main_Page.class);
       startActivity(intent);
        imageView = (ImageView) findViewById(R.id.image1);
        button = (Button) findViewById(R.id.uploadImage);
        imageView.setOnClickListener(this);
        button.setOnClickListener(this);
        imageView.setImageResource(camera);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference(companyName);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.image1:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, Result_Image);
                break;
            case R.id.uploadImage:
                EditText p_name;
                p_name = findViewById(R.id.name);
                product_name = p_name.getText().toString();
                image_name = product_name;
                Bitmap p_pic = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                // Write a message to the database
                uploadPicture();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Result_Image && resultCode==RESULT_OK && data!=null) {
            selected_image = data.getData();
            imageView.setImageURI(selected_image);
        }
    }

    private void uploadPicture() {
        final ProgressDialog PD = new ProgressDialog(this);
        PD.setTitle("Uploading Image...");
        StorageReference riversRef = storageReference.child(image_name);
        PD.show();

        riversRef.putFile(selected_image)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        PD.dismiss();
                        Snackbar.make(findViewById(R.id.uploadImage), "Image Uploaded Successfully", Snackbar.LENGTH_LONG).show();
                        storageReference.child(product_name).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                url = uri.toString();
                                Product_List_class PL_class = new Product_List_class();
                                PL_class.setProduct(url, product_name, price);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference(companyName);
                                DatabaseReference newRef = myRef.push();
                                newRef.setValue(PL_class);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getApplicationContext(), "Get Image URL Failed", Toast.LENGTH_LONG).show();                            }
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
}