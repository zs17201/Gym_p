package com.example.gym_p.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gym_p.Classes.DayWorkoutAdapter;
import com.example.gym_p.Classes.Exercise;
import com.example.gym_p.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageWorkouts_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageWorkouts_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CalendarView calendarView;
    private List<String> workoutDates; // Set to store the dates with workouts
    private String email = "";
    private List<Exercise> day_exercise;

    public ManageWorkouts_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ManageWorkouts_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageWorkouts_Fragment newInstance(String param1, String param2) {
        ManageWorkouts_Fragment fragment = new ManageWorkouts_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_manage_workouts_, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        day_exercise = new ArrayList<>();

        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("user_email");
        }
        workoutDates = new ArrayList<>();
        // Fetch workouts data from Firebase
        fetchWorkoutsFromFirebase();

        calendarView.setOnDayClickListener(eventDay -> {
            Calendar clickedDate = eventDay.getCalendar();
            String dateString = formatDate(clickedDate);

            fetchWorkoutsForDate(dateString, exercises -> {
                if (exercises.isEmpty()) {
                    Toast.makeText(getContext(), "No workouts found for this date", Toast.LENGTH_SHORT).show();
                } else {
                    WorkoutsCalFragment fragment = new WorkoutsCalFragment();
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("exercises", new ArrayList<>(exercises));
                    bundle2.putSerializable("day", dateString);
                    fragment.setArguments(bundle2);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        });

        return view;
    }

    private void fetchWorkoutsForDate(String date, OnWorkoutsFetchedCallback callback) {
        String sanitizedEmail = email.replace(".", ",");
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("usersWorkouts")
                .child(sanitizedEmail)
                .child(date);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Exercise> exercises = new ArrayList<>();
                for (DataSnapshot exerciseSnapshot : snapshot.getChildren()) {
                    Exercise exercise = exerciseSnapshot.getValue(Exercise.class);
                    if (exercise != null) {
                        exercises.add(exercise);
                    }
                }
                callback.onWorkoutsFetched(exercises);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load exercises: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    interface OnWorkoutsFetchedCallback {
        void onWorkoutsFetched(List<Exercise> exercises);
    }


    private String formatDate(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(calendar.getTime());
    }
    private void fetchWorkoutsFromFirebase() {
        String sanitizedEmail = email.replace(".", ","); // Remove dots from the email
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usersWorkouts").child(sanitizedEmail);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                workoutDates.clear();

                for (DataSnapshot dateSnapshot : dataSnapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    if (isValidDate(date)) {
                        workoutDates.add(date);
                    }
                }
                markWorkoutDates(workoutDates);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load workouts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidDate(String date) {
        try {
            new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }

    private long parseDateToMillis(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1;
        int day = Integer.parseInt(parts[2]);
        return new java.util.GregorianCalendar(year, month, day).getTimeInMillis();
    }

    private void markWorkoutDates(List<String> workoutDates) {
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (String date : workoutDates) {
            String[] dateParts = date.split("-");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1;  // Month is 0-based in Calendar (January = 0)
            int day = Integer.parseInt(dateParts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            CalendarDay calendarDay = new CalendarDay(calendar);
            calendarDay.setImageResource(R.drawable.ic_sample);
            calendarDays.add(calendarDay);
        }
        calendarView.setCalendarDays(calendarDays);
    }


}