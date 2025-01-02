package com.example.gym_p.Classes;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    private String name;
    private String description;
    List<Set> sets;
    private int imageResId;

    public Exercise(String name,String description ,int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
        this.sets = sets != null ? sets : new ArrayList<>();
    }

    public List<Set> getSets() {
        return sets;
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

    public void AddSet(int weight, int reps) {
        if (sets == null) {
            sets = new ArrayList<>();
        }
        sets.add(new Set(weight, reps));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
