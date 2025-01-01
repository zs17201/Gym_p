package com.example.gym_p.Classes;

import java.util.List;



import java.util.List;

public class MuscleGroup {

    private String name;
    private int iconResource;
    private List<Exercise> exercises;

    public MuscleGroup(String name, List<Exercise> exerciseList, int iconResource) {
        this.name = name;
        this.exercises = exerciseList;
        this.iconResource = iconResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exercises = exerciseList;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}

