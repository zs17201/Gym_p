package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gym_p.Classes.Exercise;
import com.example.gym_p.Classes.ExerciseAdapter;
import com.example.gym_p.Classes.MuscleGroupAdapter;
import com.example.gym_p.Classes.MuscleGroupData;
import com.example.gym_p.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExerciseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerViewExercises;
    private ExerciseAdapter exerciseAdapter;
    private static final String ARG_EXERCISES = "exercises";
    private ArrayList<Exercise> exerciseList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExerciseFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public static ExerciseFragment newInstance(ArrayList<Exercise> exercises) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXERCISES, exercises); // מעביר את הרשימה כ-Serializable
        fragment.setArguments(args);
        return fragment;
    }

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exerciseList = (ArrayList<Exercise>) getArguments().getSerializable(ARG_EXERCISES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        recyclerViewExercises = view.findViewById(R.id.recyclerViewExercises);

        // No need to call getArguments again here
        // exerciseList is already set in onCreate

        exerciseAdapter = new ExerciseAdapter(getContext(), exerciseList);
        recyclerViewExercises.setAdapter(exerciseAdapter);

        recyclerViewExercises.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}