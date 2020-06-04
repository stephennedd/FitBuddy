package com.san.fitbuddy.Model;

public class Workout {
    private String name;
    private String imageFile;


    public Workout(String name) {
        this.name = name;
        imageFile = "default_image.png";
    }

    public Workout(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFile() {
        return imageFile;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", imageFile='" + imageFile + '\'' +
                '}';
    }
}
