package com.example.gym_p.Classes;

import com.example.gym_p.R;

import java.util.List;
import java.util.ArrayList;

public class MuscleGroupData {

    public static List<MuscleGroup> getMuscleGroups() {
        List<MuscleGroup> muscleGroups = new ArrayList<>();

        // Abs (בטן)
        MuscleGroup absGroup = new MuscleGroup("Abs", new ArrayList<>(),R.drawable.abs);
        absGroup.getExercises().add(new Exercise("Crunches", "Crunches for core", 3, 15, R.drawable.crunches));
        absGroup.getExercises().add(new Exercise("Plank", "Plank hold", 3, 30, R.drawable.plank));
        absGroup.getExercises().add(new Exercise("Leg Raise", "Leg raise for lower abs", 3, 12, R.drawable.leg_raise));
        muscleGroups.add(absGroup);

        // Back (גב)
        MuscleGroup backGroup = new MuscleGroup("Back", new ArrayList<>(),R.drawable.back);
        backGroup.getExercises().add(new Exercise("Pull-up", "Pull-up for upper back", 4, 10, R.drawable.pull_up));
        backGroup.getExercises().add(new Exercise("Lat Pulldown", "Lat pulldown for lats", 3, 12, R.drawable.lat_pulldown));
        backGroup.getExercises().add(new Exercise("Deadlift", "Deadlift for lower back", 3, 8, R.drawable.deadlift));
        muscleGroups.add(backGroup);

        // Biceps (שרירי יד קדמית)
        MuscleGroup bicepsGroup = new MuscleGroup("Biceps", new ArrayList<>(),R.drawable.biceps);
        bicepsGroup.getExercises().add(new Exercise("Bicep Curl", "Bicep curl with dumbbells", 3, 12, R.drawable.bicep_curl));
        bicepsGroup.getExercises().add(new Exercise("Hammer Curl", "Hammer curl for biceps", 3, 10, R.drawable.hammer_curl));
        muscleGroups.add(bicepsGroup);

        // Calf (שרירי שוק)
        MuscleGroup calfGroup = new MuscleGroup("Calf", new ArrayList<>(),R.drawable.calf);
        calfGroup.getExercises().add(new Exercise("Standing Calf Raise", "Standing calf raise", 4, 15, R.drawable.calf_raise));
        calfGroup.getExercises().add(new Exercise("Seated Calf Raise", "Seated calf raise", 4, 12, R.drawable.seated_calf_raise));
        muscleGroups.add(calfGroup);

        // Chest (חזה)
        MuscleGroup chestGroup = new MuscleGroup("Chest", new ArrayList<>(),R.drawable.chest);
        chestGroup.getExercises().add(new Exercise("Push-up", "Standard push-up", 3, 20, R.drawable.push_up));
        chestGroup.getExercises().add(new Exercise("Bench Press", "Barbell bench press", 4, 10, R.drawable.bench_press));
        chestGroup.getExercises().add(new Exercise("Dumbbell Fly", "Dumbbell chest fly", 3, 12, R.drawable.dumbbell_fly));
        muscleGroups.add(chestGroup);

        // Forearms (שרירי אמות)
        MuscleGroup forearmsGroup = new MuscleGroup("Forearms", new ArrayList<>(),R.drawable.forearms);
        forearmsGroup.getExercises().add(new Exercise("Wrist Curl", "Wrist curl with dumbbell", 3, 12, R.drawable.wrist_curl));
        forearmsGroup.getExercises().add(new Exercise("Reverse Wrist Curl", "Reverse wrist curl", 3, 10, R.drawable.reverse_wrist_curl));
        muscleGroups.add(forearmsGroup);

        // Legs (רגליים)
        MuscleGroup legsGroup = new MuscleGroup("Legs", new ArrayList<>(),R.drawable.legs);
        legsGroup.getExercises().add(new Exercise("Squats", "Barbell squats", 4, 10, R.drawable.squats));
        legsGroup.getExercises().add(new Exercise("Leg Press", "Leg press machine", 3, 12, R.drawable.leg_press));
        legsGroup.getExercises().add(new Exercise("Lunges", "Walking lunges with dumbbells", 3, 12, R.drawable.lunges));
        muscleGroups.add(legsGroup);

        // Shoulders (כתפיים)
        MuscleGroup shouldersGroup = new MuscleGroup("Shoulders", new ArrayList<>(),R.drawable.shoulders);
        shouldersGroup.getExercises().add(new Exercise("Shoulder Press", "Dumbbell shoulder press", 4, 10, R.drawable.shoulder_press));
        shouldersGroup.getExercises().add(new Exercise("Lateral Raise", "Lateral raise with dumbbells", 3, 12, R.drawable.lateral_raise));
        muscleGroups.add(shouldersGroup);

        // Triceps (יד אחורית)
        MuscleGroup tricepsGroup = new MuscleGroup("Triceps", new ArrayList<>(),R.drawable.triceps);
        tricepsGroup.getExercises().add(new Exercise("Triceps Pushdown", "Triceps pushdown with cable", 3, 12, R.drawable.triceps_pushdown));
        tricepsGroup.getExercises().add(new Exercise("Skull Crusher", "Lying triceps extension", 3, 10, R.drawable.skull_crusher));
        muscleGroups.add(tricepsGroup);

        return muscleGroups;
    }
}

