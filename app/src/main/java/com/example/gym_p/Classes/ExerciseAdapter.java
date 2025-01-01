package com.example.gym_p.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_p.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private Context context;
    private List<Exercise> exercises;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each exercise item
        View view = LayoutInflater.from(context).inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);

        // Set exercise name and description
        holder.exerciseName.setText(exercise.getName());
        holder.exerciseDescription.setText(exercise.getDescription());
        holder.exerciseImage.setImageResource(exercise.getImageResId());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName, exerciseDescription;
        ImageView exerciseImage;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseDescription = itemView.findViewById(R.id.exercise_description);
            exerciseImage = itemView.findViewById(R.id.exercise_image);
        }
    }
}
