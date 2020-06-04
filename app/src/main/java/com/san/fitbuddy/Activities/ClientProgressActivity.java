package com.san.fitbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.san.fitbuddy.CustomViews.CustomView;
import com.san.fitbuddy.Model.ClientAdmin;
import com.san.fitbuddy.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;


public class ClientProgressActivity extends AppCompatActivity {
    static final String DATE_FORMAT = "EEE, MMM d, ''yy";
    private TextInputEditText currentWeightTv;
    private Button done;
    private double current;
    private double min;
    private double max;
    private double progress;
    private TextView start;
    private TextView target;
    private TextView percent;
    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_progress);
        Intent intent = getIntent();
        int position = intent.getIntExtra("ListItemPosition", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        done = findViewById(R.id.progressDoneButton);
        currentWeightTv = findViewById(R.id.progressCurrentWeight);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDoneClick();
            }
        });

        min = ClientAdmin.clientList.get(position).getWeight();
        max = ClientAdmin.clientList.get(position).getTargetWeight();
        TextView textView = findViewById(R.id.dateOutput);
        textView.setText(getCurrentDate());

        start = findViewById(R.id.startWeight);
        start.setText("Start: "+min+"kg");

        target = findViewById(R.id.targetWeight);
        target.setText("Target: " + max+"kg");

        percent = findViewById(R.id.percentageDone);
        customView = findViewById(R.id.customView);
        customView.setCircleColor(Color.TRANSPARENT);

    }

    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    public void onDoneClick() {
        String currentWeightInput = Objects.requireNonNull(currentWeightTv.getText()).toString();
        current = Double.parseDouble(currentWeightInput);
        if(currentWeightInput.matches("")) {
            currentWeightTv.setError("Can't be blank");
            Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (max < min && current < min) {
            progress = calculateLossProgress(min, max, current);
            percent.setText(progress+"%");
            TextView textView = findViewById(R.id.completedTv);
            textView.setText("completed");
            if (progress > 75) {
                customView.setCircleColor(Color.GREEN);
            } else if (progress <= 75 && progress > 50) {
                customView.setCircleColor(Color.YELLOW);
            } else if (progress <= 50) {
                customView.setCircleColor(Color.RED);
            }
        } else if (max > min && current > min) {
            progress = calculateGainProgress(min, max, current);
            percent.setText(progress+"%");
            TextView textView = findViewById(R.id.completedTv);
            textView.setText("completed");
            if (progress > 75) {
                customView.setCircleColor(Color.GREEN);
            } else if (progress <= 75 && progress > 50) {
                customView.setCircleColor(Color.YELLOW);
            } else if (progress <= 50) {
                customView.setCircleColor(Color.RED);
            }
        } else {
            return;
        }
    }

    public double calculateGainProgress(double start, double target, double current){
        double amtCompleted = (current - start);
        double amtRemaining = (target - current);
        double devision = (amtCompleted / (amtRemaining+amtCompleted));
        double result = devision * 100;
        Log.i("almostthere", "calculateProgress: " + result);
        return result;
    }

    public double calculateLossProgress(double start, double target, double current) {
        double amtCompleted = (start - current);
        double amtRemaining = (current - target);
        double devision = (amtCompleted / (amtRemaining+amtCompleted));
        double result = devision * 100;
        Log.i("almostthere", "calculateProgress: " + result);
        return result;
    }
}
