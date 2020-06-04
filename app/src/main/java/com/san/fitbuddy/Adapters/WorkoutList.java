package com.san.fitbuddy.Adapters;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.san.fitbuddy.Activities.AddWorkoutActivity;
import com.san.fitbuddy.Activities.ClientDetailsActivity;
import com.san.fitbuddy.Model.Client;
import com.san.fitbuddy.Model.ClientAdmin;
import com.san.fitbuddy.Model.Workout;
import com.san.fitbuddy.Model.WorkoutAdmin;
import com.san.fitbuddy.R;

import java.util.List;

public class WorkoutList extends AppCompatActivity {

    private ListView listView;
    private List<Workout> workoutList;
    private WorkoutAdmin workoutAdmin;
    private List clients;
    private Client client;
    private int position;
    private WorkoutListAdapter adapter;
    static final int ADD_WORKOUT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);

        Intent intent = getIntent();
        position = intent.getIntExtra("ListItemPosition", 0);

        workoutAdmin = new WorkoutAdmin();
        workoutList = WorkoutAdmin.workoutList;

        workoutAdmin.setWorkoutList();


        clients = ClientAdmin.clientList;

        FloatingActionButton addButton = findViewById(R.id.workoutBtnFloating);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClicked();
            }
        });

        listView = findViewById(R.id.workout_list);
        adapter = new WorkoutListAdapter(this, workoutList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onWorkoutClicked(position);
            }
        });
    }

    private void onWorkoutClicked(int pos) {
        Intent intent = new Intent(this, ClientDetailsActivity.class);
        client = ClientAdmin.clientList.get(position);
        client.addWorkout(WorkoutAdmin.workoutList.get(pos));
        finish();
    }

    private void onAddButtonClicked() {
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivityForResult(intent, ADD_WORKOUT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WORKOUT_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
