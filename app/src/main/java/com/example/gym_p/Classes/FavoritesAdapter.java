package com.example.gym_p.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gym_p.Activities.MainActivity;
import com.example.gym_p.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private List<String> favoriteWorkouts;

    private List<Exercise> FavWorkoutExercises = new ArrayList<>();
    private String email = "";

    public FavoritesAdapter(Context context, List<String> favoriteWorkouts, String email) {
        this.context = context;
        this.favoriteWorkouts = favoriteWorkouts;
        this.email = email;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.name_fav_workout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String workout_name = favoriteWorkouts.get(position);
        holder.titleTextView.setText(workout_name);

        MainActivity mainActivity = (MainActivity) context;
        WorkoutViewModel viewModel = new ViewModelProvider((AppCompatActivity) context).get(WorkoutViewModel.class);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.onAddToCurrentWorkout(workout_name, email, new MainActivity.DataFetchedCallback() {
                        @Override
                        public void onDataFetched(List<Exercise> exercises) {
                            // Handle the exercise list after data is fetched
                            viewModel.clearExercises();
                            viewModel.addExercises(exercises);
                            Toast.makeText(holder.itemView.getContext(), "Size: " + exercises.size(), Toast.LENGTH_SHORT).show();
                            FavWorkoutExercises.clear();
                            FavWorkoutExercises.addAll(exercises); // Now you can use the data

                            // Notify adapter about the updated data (if necessary)
                            notifyDataSetChanged();
                        }
                    });
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null) {
                    mainActivity.onDeleteWorkout(workout_name,email); // Directly call the method in MainActivity
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return favoriteWorkouts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        Button add,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.FavWorkoutTitle);
            add = itemView.findViewById(R.id.addToCurrentWorkoutButton);
            delete = itemView.findViewById(R.id.deleteFabWorkoutButton);
        }
    }
}
