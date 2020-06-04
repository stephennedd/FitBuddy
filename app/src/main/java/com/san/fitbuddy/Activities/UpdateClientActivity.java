package com.san.fitbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UpdateClientActivity extends AppCompatActivity {

    private Button updateButton;
    private EditText name;
    private EditText age;
    private EditText status;
    private EditText height;
    private EditText weight;
    private EditText tweight;
    private ImageView profile;
    private ClientAdmin clientAdmin;
    private List<Client> clients;
    private int position;
    private RadioButton male;
    private RadioButton female;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client);

        updateButton = findViewById(R.id.updateButton);
        name = findViewById(R.id.updateClientName);
        age = findViewById(R.id.updateClientAge);
        status = findViewById(R.id.updateClientStatus);
        height = findViewById(R.id.updateClientHeight);
        weight = findViewById(R.id.updateClientWeight);
        tweight = findViewById(R.id.updateClientTargWeight);
        profile = findViewById(R.id.clientImageId);
        clients = ClientAdmin.clientList;
        radioGroup = findViewById(R.id.updateRadioGroup);
        male = findViewById(R.id.updateClientRadioMale);
        female = findViewById(R.id.updateClientRadioFemale);

        Intent intent = getIntent();
        if (intent != null) {
            position = intent.getIntExtra("ListItemPosition", position);

            String userName = clients.get(position).getName();
            double userWeight = clients.get(position).getWeight();
            int userAge = clients.get(position).getAge();
            String userStatus = clients.get(position).getStatus();
            double userHeight = clients.get(position).getHeight();
            double userTWeight = clients.get(position).getTargetWeight();
            String userGender = clients.get(position).getGender();
            if (userGender.matches("Male")) {
                male.toggle();
            } else if (userGender.matches("Female")){
                female.toggle();
            }

            if (userName != null) {
                name.setText(userName);
                age.setText(userAge+"");
                status.setText(userStatus);
                height.setText(userHeight+"");
                weight.setText(userWeight+"");
                tweight.setText(userTWeight+"");


                InputStream inputStream = null;
                try {
                    String image = clients.get(position).getImageFile();
                    inputStream = getAssets().open(image);
                    Drawable d = Drawable.createFromStream(inputStream, null);
                    profile.setImageDrawable(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateButtonClicked();
            }
        });
    }

    public void onUpdateButtonClicked() {
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

        String statusInput = status.getText().toString();
        if (statusInput.matches("")) {
            Toast.makeText(this, "You did not enter a status", Toast.LENGTH_SHORT).show();
            return;
        }


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

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Client client = clients.get(position);
        client.setName(nameInput);
        client.setWeight(weightInput);
        client.setTargetWeight(targetWeightInput);
        client.setHeight(heightInput);
        client.setAge(ageInput);
        client.setStatus(statusInput);
        client.setGender(radioButton.getText()+"");
//        client.setImageFile(filePath);
        ClientAdmin.clientList.remove(position);
        ClientAdmin.clientList.add(position,client);
        Log.i("clientlist", "onAddButtonClicked: " + ClientAdmin.clientList.size());
        setResult(RESULT_OK);
        finish();
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

    }

}
