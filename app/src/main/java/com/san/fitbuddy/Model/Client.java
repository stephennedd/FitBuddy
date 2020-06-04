package com.san.fitbuddy.Model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String status;
    private double weight;
    private double currentWeight;
    private double targetWeight;
    private double height;
    private int age;
    private String gender;
    private String imageFile;
    private List<Workout> workoutList;

    public Client(String name) {
        this.name = name;
        status = "Available";
        weight = 0.0;
        currentWeight = 0.0;
        targetWeight = 0.0;
        height = 0.0;
        age = 0;
        gender = "noGender";
        imageFile = "default";
        workoutList = new ArrayList<Workout>();
    }

    public Client(String name,String status, double weight, double height, int age, String gender, double targetWeight,String imageFile, List<Workout> workoutList) {
        this.name = name;
        this.status = status;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.targetWeight = targetWeight;
        this.imageFile = imageFile;
        this.workoutList = workoutList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() { return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCurrentWeight() {return currentWeight;}

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getHeight() { return height;}

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getImageFile() { return imageFile;}

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", targetWeight=" + targetWeight +
                ", imageFile=" + imageFile + '\'' +
                ", workoutList=" + workoutList +
                '}';
    }
}
