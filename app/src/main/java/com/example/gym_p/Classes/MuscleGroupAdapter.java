package com.example.gym_p.Classes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_p.Fragments.ExerciseFragment;
import com.example.gym_p.R;

import java.util.ArrayList;
import java.util.List;

public class MuscleGroupAdapter extends RecyclerView.Adapter<MuscleGroupAdapter.MuscleGroupViewHolder> {

    private Context context;
    private List<MuscleGroup> muscleGroups;
    private FragmentManager fragmentManager;

    // Constructor
    public MuscleGroupAdapter(Context context, List<MuscleGroup> muscleGroups,FragmentManager fragmentManager) {
        this.context = context;
        this.muscleGroups = muscleGroups;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public MuscleGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each muscle group item
        View view = LayoutInflater.from(context).inflate(R.layout.muscle_group_item, parent, false);
        return new MuscleGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MuscleGroupViewHolder holder, int position) {
        MuscleGroup muscleGroup = muscleGroups.get(position);

        holder.muscleGroupName.setText(muscleGroup.getName());

        holder.muscleGroupIcon.setImageResource(muscleGroup.getIconResource());

        holder.itemView.setOnClickListener(view -> {

            ExerciseFragment exerciseFragment = ExerciseFragment.newInstance(new ArrayList<>(muscleGroup.getExercises()));

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainerView, exerciseFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return muscleGroups.size();
    }

    public static class MuscleGroupViewHolder extends RecyclerView.ViewHolder {
        ImageView muscleGroupIcon;
        TextView muscleGroupName;

        public MuscleGroupViewHolder(View itemView) {
            super(itemView);
            muscleGroupIcon = itemView.findViewById(R.id.MuscleGroup_image);
            muscleGroupName = itemView.findViewById(R.id.MuscleGroup_name);
        }
    }

    public void updateList(List<MuscleGroup> newMuscleGroups) {
        this.muscleGroups = newMuscleGroups;
        notifyDataSetChanged();
    }
}

