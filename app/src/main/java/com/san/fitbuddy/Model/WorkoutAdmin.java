package com.san.fitbuddy.Model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdmin {
    public static List<Workout> workoutList;

    static {
        workoutList = new ArrayList<Workout>();
    }

    public WorkoutAdmin() {workoutList = new ArrayList<Workout>(); }

    public List getWorkoutList() {
        if (workoutList != null) {
            return workoutList;
        }
        return null;
    }

    public void setWorkoutList() {
        Workout workout1 = new Workout("Push-ups", "workout_1.png");
        Workout workout2 = new Workout("Running", "workout_2.png");
        Workout workout3 = new Workout("Walking", "workout_3.png");
        Workout workout4 = new Workout("Biking", "workout_4.png");
        Workout workout5 = new Workout("Pull-ups", "workout_5.png");
        Workout workout6 = new Workout("Rowing Machine", "workout_6.png");
        Workout workout7 = new Workout("Sit-ups", "workout_7.png");

        workoutList.add(workout1);
        workoutList.add(workout2);
        workoutList.add(workout3);
        workoutList.add(workout4);
        workoutList.add(workout5);
        workoutList.add(workout6);
        workoutList.add(workout7);
    }
}
