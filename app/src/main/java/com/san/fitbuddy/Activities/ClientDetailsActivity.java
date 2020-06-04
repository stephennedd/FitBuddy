package com.san.fitbuddy.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.san.fitbuddy.Model.Client;
import com.san.fitbuddy.Model.ClientAdmin;
import com.san.fitbuddy.Model.WorkoutAdmin;
import com.san.fitbuddy.R;
import com.san.fitbuddy.Adapters.ClientWorkoutListAdapter;
import com.san.fitbuddy.Adapters.WorkoutList;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClientDetailsActivity extends AppCompatActivity {

    private WorkoutAdmin workoutAdmin;
    private List workouts;
    private ClientWorkoutListAdapter adapter;
    private List<Client> clients;
    static final int ADD_WORKOUT_CODE = 1;
    static final int UPDATE_CLIENT_CODE = 2;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_client_details);

        Button edit = findViewById(R.id.clientEditButton);
        clients = ClientAdmin.clientList;
        position = intent.getIntExtra("ListItemPosition", 0);
        workouts = clients.get(position).getWorkoutList();

        String name = clients.get(position).getName();
        String status = clients.get(position).getStatus();
        double weight = clients.get(position).getWeight();
        int age = clients.get(position).getAge();
        double height = clients.get(position).getHeight();

//TODO implement user taken profile image
        ImageView profile = findViewById(R.id.detailProfileImage);
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

        TextView nameTv = findViewById(R.id.detailName);
        nameTv.setText(name);
        TextView preStatusTv = findViewById(R.id.preStatus);
        preStatusTv.setText(R.string.update_client_status);
        TextView statusTv = findViewById(R.id.detailStatus);
        statusTv.setText(status);
        TextView preWeightTv = findViewById(R.id.preWeightID);
        preWeightTv.setText(getText(R.string.add_client_weight));
        TextView weightTv = findViewById(R.id.detailWeight);
        weightTv.setText(weight+"");
        TextView preAgeTv = findViewById(R.id.preAgeID);
        preAgeTv.setText(R.string.add_client_age);
        TextView ageTv = findViewById(R.id.detailAge);
        ageTv.setText(age+"");
        TextView preHeightTv = findViewById(R.id.preHeightID);
        preHeightTv.setText(R.string.add_client_height);
        TextView heightTv = findViewById(R.id.detailHeight);
        heightTv.setText(height+"");


        ListView listView = findViewById(R.id.workoutList);
        adapter = new ClientWorkoutListAdapter(this, workouts);
        listView.setAdapter(adapter);

        FloatingActionButton addWorkout = findViewById(R.id.add_workout);
        addWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whenAddButtonClicked();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateClientClicked();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.client_progress:
                Intent intent = new Intent(this, ClientProgressActivity.class);
                intent.putExtra("ListItemPosition", position);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onUpdateClientClicked() {
        Intent editIntent = new Intent(this, UpdateClientActivity.class);
        editIntent.putExtra("ListItemPosition", position);

        startActivityForResult(editIntent, UPDATE_CLIENT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_CLIENT_CODE && resultCode == RESULT_OK) {
            recreate();
        }
        if (requestCode == ADD_WORKOUT_CODE && resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();
        }
    }

    public void whenAddButtonClicked() {
        Intent addIntent = new Intent(this, WorkoutList.class);
        addIntent.putExtra("ListItemPosition", position);
        startActivityForResult(addIntent, ADD_WORKOUT_CODE);
    }

    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}
