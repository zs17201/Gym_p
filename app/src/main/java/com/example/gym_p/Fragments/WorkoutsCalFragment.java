package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gym_p.Classes.DayWorkoutAdapter;
import com.example.gym_p.Classes.Exercise;
import com.example.gym_p.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutsCalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutsCalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private DayWorkoutAdapter adapter;
    private List<Exercise> exercises;

    public WorkoutsCalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutsCalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutsCalFragment newInstance(String param1, String param2) {
        WorkoutsCalFragment fragment = new WorkoutsCalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_workouts_cal, container, false);

        TextView textViewWorkoutDate = view.findViewById(R.id.textViewWorkoutDate);
        recyclerView = view.findViewById(R.id.recyclerViewWorkoutDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        exercises = new ArrayList<>();
        adapter = new DayWorkoutAdapter(exercises);
        recyclerView.setAdapter(adapter);

        if (getArguments() != null) {
            String workoutDate = getArguments().getString("day");
            textViewWorkoutDate.setText("Workout Date: " + workoutDate);

            exercises = (List<Exercise>) getArguments().getSerializable("exercises");
            if (exercises != null) {
                adapter.setExercises(exercises);
            }
        }

        return view;
    }
}