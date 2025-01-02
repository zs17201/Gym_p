package com.example.gym_p.Classes;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_p.R;

import java.util.List;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.WorkoutViewHolder> {
    private List<Exercise> exerciseList;

    public CurrentAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
        if (exerciseList != null && !exerciseList.isEmpty()) {
            Exercise exercise = exerciseList.get(position);
            holder.exerciseName.setText(exercise.getName());
            holder.exerciseDescription.setText(exercise.getDescription());
        }

        String weightText = ((EditText)holder.weight).getText().toString();
        String repsText = ((EditText)holder.reps).toString();

        holder.removeButton.setOnClickListener(v -> {
            exerciseList.remove(position);
            notifyItemRemoved(position);
        });

        holder.addSet.setOnClickListener(v -> {
           if(TextUtils.isDigitsOnly(weightText) && TextUtils.isDigitsOnly(repsText)){

           }
           else{
              // Toast.makeText(getContext(), "Check your weight or reps", Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName, exerciseDescription;
        EditText weight,reps;
        Button removeButton, addSet;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.muscleGroup);
            exerciseDescription = itemView.findViewById(R.id.exerciseName);
            removeButton = itemView.findViewById(R.id.ButtonRemoveEx);
            addSet = itemView.findViewById(R.id.ButtonAddSet);
            weight = itemView.findViewById(R.id.EditTextWeight);
            reps = itemView.findViewById(R.id.EditTextReps);
        }
    }
}

