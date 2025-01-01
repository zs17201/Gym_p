package com.example.gym_p.Classes;

import java.util.List;

public class Exercise {

    private String name;
    private String description;
    private int sets;
    private int reps;
    private int imageResId;

    public Exercise(String name,String description,int sets,int reps ,int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.reps = reps;
        this.sets = sets;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
