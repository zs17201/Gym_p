package com.example.gym_p.Classes;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewModel extends ViewModel {

    private final List<Exercise> selectedExercises = new ArrayList<>();

    public List<Exercise> getSelectedExercises() {
        return selectedExercises;
    }

    public void addExercise(Exercise exercise) {
        selectedExercises.add(exercise);
    }
    // Add a list of exercises to the list
    public void addExercises(List<Exercise> exercises) {
        selectedExercises.addAll(exercises);
    }

    public void clearExercises() {
        selectedExercises.clear();
    }
}
