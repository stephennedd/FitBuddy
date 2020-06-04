package com.san.fitbuddy.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.san.fitbuddy.Model.Workout;
import com.san.fitbuddy.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WorkoutListAdapter extends ArrayAdapter {

    LayoutInflater layoutInflater;
    List<Workout> workoutList;
    ImageView imageView;
    TextView workoutName;
    Button removeButton;

    public WorkoutListAdapter(Context context, List objects) {
        super(context, R.layout.workout_list_item, objects);
        layoutInflater = LayoutInflater.from(context);
        workoutList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Workout workout = workoutList.get(position);
        Log.i("help", "getView: " + workout.toString());

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.workout_list_item, parent, false);
        }
        if (convertView != null) {
            workoutName = convertView.findViewById(R.id.workoutName);
            workoutName.setText(workout.getName());

            imageView = convertView.findViewById(R.id.workoutImage);
            InputStream inputStream = null;
            try {
                String imageFile = workout.getImageFile();

                inputStream = getContext().getAssets().open(imageFile);
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(d);
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

            removeButton = convertView.findViewById(R.id.removeButtonWorkout);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    workoutList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }
}
