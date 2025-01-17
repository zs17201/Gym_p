package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym_p.Activities.MainActivity;
import com.example.gym_p.Classes.CurrentAdapter;
import com.example.gym_p.Classes.Exercise;
import com.example.gym_p.Classes.WorkoutViewModel;
import com.example.gym_p.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private WorkoutViewModel workoutViewModel;

    public CurrentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentFragment newInstance(String param1, String param2) {
        CurrentFragment fragment = new CurrentFragment();
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
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        Calendar calendar = Calendar.getInstance();
        String email = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        String formattedDate = dateFormat.format(calendar.getTime());

        String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        String workoutTitleText = "Your workout for today - " + formattedDate + " - " + dayOfWeek;

        TextView workoutTitle = view.findViewById(R.id.workoutTitle);
        ImageButton AddFavButton = view.findViewById(R.id.AddFavoriteButton);
        Button closeInputSectionButton = view.findViewById(R.id.closeInputSectionButton);
        LinearLayout inputSectionContainer = view.findViewById(R.id.inputSectionContainer);

        closeInputSectionButton.setOnClickListener(v -> {
            inputSectionContainer.setVisibility(View.GONE);
        });
        workoutTitle.setText(workoutTitleText);

        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("user_email");
        }

        String finalEmail = email;
        AddFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
                List<Exercise> selectedExercises = workoutViewModel.getSelectedExercises();
                if (selectedExercises.isEmpty()) {
                    Toast.makeText(getContext(), "Your workout empty", Toast.LENGTH_SHORT).show();
                } else {

                    Button saveWorkoutButton = view.findViewById(R.id.saveWorkoutButton);
                    EditText workoutNameEditText = view.findViewById(R.id.workoutNameEditText);
                    inputSectionContainer.setVisibility(View.VISIBLE);

                    saveWorkoutButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String workout_name = ((EditText) workoutNameEditText).getText().toString();
                            if (workout_name.isEmpty()) {
                                Toast.makeText(getContext(), "Please enter workout name", Toast.LENGTH_SHORT).show();
                            } else {
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.AddFavWorkout(finalEmail, workout_name);
                                inputSectionContainer.setVisibility(View.GONE);
                            }

                        }
                    });


                }
            }
        });


        // Reference the LinearLayout container where items will be added
        RecyclerView workoutRecyclerView = view.findViewById(R.id.workoutRecyclerView);

        workoutViewModel = new ViewModelProvider(requireActivity()).get(WorkoutViewModel.class);
        List<Exercise> selectedExercises = workoutViewModel.getSelectedExercises();
        CurrentAdapter adapter = new CurrentAdapter(selectedExercises,email);
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        workoutRecyclerView.setAdapter(adapter);

     return view;
    }


}