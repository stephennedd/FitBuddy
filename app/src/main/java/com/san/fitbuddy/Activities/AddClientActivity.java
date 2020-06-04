package com.san.fitbuddy.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.san.fitbuddy.Model.Client;
import com.san.fitbuddy.Model.ClientAdmin;
import com.san.fitbuddy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddClientActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText height;
    private EditText weight;
    private EditText tweight;
    private EditText status;
    private ImageView imageView;
    //TODO add gender attributes to app.
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    private String filePath;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        name = findViewById(R.id.addClientName);
        age = findViewById(R.id.addClientAge);
        height = findViewById(R.id.addClientHeight);
        weight = findViewById(R.id.addClientWeight);
        tweight = findViewById(R.id.addClientTargWeight);
        status = findViewById(R.id.clientStatus);
        imageView = findViewById(R.id.clientImageId);
        radioGroup = findViewById(R.id.addRadioGroup);


        Button addButton = findViewById(R.id.addClientButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });
    }

    public void onAddButtonClicked() {

        String nameInput = name.getText().toString();
        if (nameInput.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        String temp1 = age.getText().toString();
        if (temp1.matches("")) {
            Toast.makeText(this, "You did not enter an age", Toast.LENGTH_SHORT).show();
            return;
        }
        int ageInput = Integer.parseInt(temp1);

        String temp2 = height.getText().toString();
        if (temp2.matches("")) {
            Toast.makeText(this, "You did not enter a height", Toast.LENGTH_SHORT).show();
            return;
        }
        double heightInput = Double.parseDouble(temp2);

        String temp3 = weight.getText().toString();
        if (temp3.matches("")) {
            Toast.makeText(this, "You did not enter a weight", Toast.LENGTH_SHORT).show();
            return;
        }
        double weightInput = Double.parseDouble(temp3);

        String temp4 = tweight.getText().toString();
        if (temp4.matches("")) {
            Toast.makeText(this, "You did not enter a target weight", Toast.LENGTH_SHORT).show();
            return;
        }
        double targetWeightInput = Double.parseDouble(temp4);

        String statusInput = status.getText().toString();
        if (statusInput.matches("")) {
            Toast.makeText(this, "You did not enter a status", Toast.LENGTH_SHORT).show();
            return;
        }

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);


        Client client = new Client(nameInput);
        client.setStatus(statusInput);
        client.setWeight(weightInput);
        client.setTargetWeight(targetWeightInput);
        client.setHeight(heightInput);
        client.setAge(ageInput);
        client.setGender(radioButton.getText()+"");
//        client.setImageFile(filePath);
        ClientAdmin.clientList.add(client);
        Log.i("clientlist", "onAddButtonClicked: " + ClientAdmin.clientList.size());
        finish();
    }

    public void onClickPhoto() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            filePath = saveImageToStorage(bitmap)+"/"+"avatar_"+(ClientAdmin.clientList.size()+1)+"jpg";
//            client.setImageFile(filePath);
        }
    }

    //TODO implement save image file to drive
    public String saveImageToStorage(Bitmap bitmap) {
        File file = getApplicationContext().getFilesDir();
        File file1 = new File(file, "avatar_"+ (ClientAdmin.clientList.size()+1) +".jpg");
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file1);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file1.getAbsolutePath();
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

    }

}
