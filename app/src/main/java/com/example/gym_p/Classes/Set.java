package com.example.gym_p.Classes;

public class Set {

    private int weight;
    private int reps;

    public Set() {}

    public Set(int weight, int reps) {
        this.weight = weight;
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
