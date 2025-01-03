package com.example.gym_p.Classes;

import android.content.Context;
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

import com.example.gym_p.Activities.MainActivity;
import com.example.gym_p.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CurrentAdapter extends RecyclerView.Adapter<CurrentAdapter.WorkoutViewHolder> {
    private List<Exercise> exerciseList;
    private String email; // Add email as a member variable

    public CurrentAdapter(List<Exercise> exerciseList, String email) {
        this.exerciseList = exerciseList;
        this.email = email; // Initialize email
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
        Exercise exercise = exerciseList.get(position);
        holder.exerciseName.setText(exercise.getName());
        holder.exerciseDescription.setText(exercise.getDescription());

        holder.removeButton.setOnClickListener(v -> {
            if(position >= 0 && position < exerciseList.size()){
                exerciseList.remove(position);
                notifyItemRemoved(position);
                for (int i = position; i < exerciseList.size(); i++) {
                    notifyItemChanged(i);
                }
            }
        });

        holder.addSet.setOnClickListener(v -> {
            if(!AddEx(holder,exercise)){
                Toast.makeText(holder.itemView.getContext(), "Check your weight or reps", Toast.LENGTH_SHORT).show();
            }
        });

        holder.cancelButton.setOnClickListener(v -> {
            if(!AddEx(holder,exercise)){
                Toast.makeText(holder.itemView.getContext(), "Finish exercise", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(holder.itemView.getContext(), "Finish exercise and save set", Toast.LENGTH_SHORT).show();
            }
            AddEx(holder,exercise);
            if(position >= 0 && position < exerciseList.size()){
                exerciseList.remove(position);
                notifyItemRemoved(position);
                for (int i = position; i < exerciseList.size(); i++) {
                    notifyItemChanged(i);
                }
            }
        });
    }

    private boolean AddEx(@NonNull WorkoutViewHolder holder, Exercise exercise){
        String weightText = holder.weight.getText().toString();
        String repsText = holder.reps.getText().toString();

        if (!weightText.isEmpty() && !repsText.isEmpty() && TextUtils.isDigitsOnly(weightText) && TextUtils.isDigitsOnly(repsText)) {
            int weightValue = Integer.parseInt(weightText);
            int repsValue = Integer.parseInt(repsText);
            exercise.AddSet(weightValue, repsValue);
            Toast.makeText(holder.itemView.getContext(), "Set added", Toast.LENGTH_SHORT).show();

            Context context = holder.itemView.getContext();
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.addExerciseToDatabase(exercise, email,getCurrentDate()); // Pass email
            }

            holder.weight.setText("");
            holder.reps.setText("");
            return true;
        } else {
            return false;
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName, exerciseDescription;
        EditText weight, reps;
        Button removeButton, addSet,cancelButton;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.muscleGroup);
            exerciseDescription = itemView.findViewById(R.id.exerciseName);
            removeButton = itemView.findViewById(R.id.ButtonRemoveEx);
            addSet = itemView.findViewById(R.id.ButtonAddSet);
            weight = itemView.findViewById(R.id.EditTextWeight);
            reps = itemView.findViewById(R.id.EditTextReps);
            cancelButton = itemView.findViewById(R.id.buttonFinishExercise);
        }
    }
}
