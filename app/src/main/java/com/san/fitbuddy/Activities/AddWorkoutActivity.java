package com.san.fitbuddy.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.san.fitbuddy.Model.Workout;
import com.san.fitbuddy.Model.WorkoutAdmin;
import com.san.fitbuddy.R;

import java.util.List;

public class AddWorkoutActivity extends AppCompatActivity {

    private EditText name;
    private WorkoutAdmin workoutAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        name = findViewById(R.id.addWorkoutName);

        Button addButton = findViewById(R.id.addWorkoutButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick();
            }
        });

    }

    public void onAddButtonClick() {
        String nameInput = name.getText().toString();
        Workout workout = new Workout(nameInput);
        WorkoutAdmin.workoutList.add(workout);
        Log.i("workoutlistSize", "onAddButtonClick: " + workout.toString());
        setResult(RESULT_OK);
        finish();
    }
}
