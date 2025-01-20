package com.example.gym_p.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_p.R;

import java.util.List;

public class DayWorkoutAdapter extends RecyclerView.Adapter<DayWorkoutAdapter.ExerciseViewHolder> {

    private List<Exercise> exercises;

    public DayWorkoutAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise_calendar, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.name.setText(exercise.getName());
        holder.description.setText(exercise.getDescription());
        holder.image.setImageResource(exercise.getImageResId());

        // Display sets
        StringBuilder setsString = new StringBuilder();
        for (Set set : exercise.getSets()) {
            setsString.append("Weight: ").append(set.getWeight()).append("kg, Reps: ").append(set.getReps()).append("\n");
        }
        holder.sets.setText(setsString.toString());
    }

    @Override
    public int getItemCount() {
        return exercises != null ? exercises.size() : 0;
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, sets;
        ImageView image;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.exerciseNameCal);
            description = itemView.findViewById(R.id.exerciseDescriptionCal);
            image = itemView.findViewById(R.id.exerciseImageCal);
            sets = itemView.findViewById(R.id.exerciseSetsCal);
        }
    }
}
