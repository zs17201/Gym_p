package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.gym_p.Classes.MuscleGroup;
import com.example.gym_p.Classes.MuscleGroupAdapter;
import com.example.gym_p.Classes.MuscleGroupData;
import com.example.gym_p.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewMuscleGroups;

    private ArrayList<MuscleGroup> MuscleGroupList;
    private MuscleGroupAdapter muscleGroupAdapter;

    public WorkoutsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutsFragment newInstance(String param1, String param2) {
        WorkoutsFragment fragment = new WorkoutsFragment();
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
        View view = inflater.inflate(R.layout.fragment_workouts, container, false);

        recyclerViewMuscleGroups = view.findViewById(R.id.recyclerViewMuscle);
        SearchView searchView = view.findViewById(R.id.searchView);
        resetList();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });



        return view;
    }


    private void resetList() {

        MuscleGroupList = new ArrayList<>(MuscleGroupData.getMuscleGroups());

        muscleGroupAdapter = new MuscleGroupAdapter(getContext(), MuscleGroupList,getParentFragmentManager());
        recyclerViewMuscleGroups.setAdapter(muscleGroupAdapter);

        recyclerViewMuscleGroups.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void filterList(String query) {
        ArrayList<MuscleGroup> filteredList = new ArrayList<>();
        for (MuscleGroup muscleGroup : MuscleGroupList) {
            if (muscleGroup.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(muscleGroup);
            }
        }
        muscleGroupAdapter.updateList(filteredList);
    }

}