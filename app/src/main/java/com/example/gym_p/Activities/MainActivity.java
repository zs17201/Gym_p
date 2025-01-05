package com.example.gym_p.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.gym_p.Classes.Exercise;
import com.example.gym_p.Classes.User;
import com.example.gym_p.Classes.WorkoutViewModel;
import com.example.gym_p.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
    }


    public void reg(String email, String pass, String first_name, String last_name) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        addDATA(email, pass, first_name, last_name); // Pass data to addDATA
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(MainActivity.this, "Email already in use. Try logging in.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addDATA(String email, String pass, String first_name, String last_name) {


        String sanitizedEmail = email.replace(".", ",");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(sanitizedEmail);
        //Toast.makeText(MainActivity.this, first_name +" " + last_name, Toast.LENGTH_SHORT).show();

        User u = new User(email, pass, first_name, last_name);
        myRef.setValue(u).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Firebase", "Data saved successfully.");
                Toast.makeText(MainActivity.this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("Firebase", "Error saving data: " + task.getException().getMessage());
                Toast.makeText(MainActivity.this, "Error saving user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(String email, String password, OnLoginResultListener listener) {

        if (email.equals("") || password.equals("")) {
            listener.onFailure();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onFailure();
                    }
                });
    }

    public interface OnLoginResultListener {
        void onSuccess();

        void onFailure();
    }

    public void getName(OnGetNameListener listener) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");

        String email = ((EditText) findViewById(R.id.et_email_login)).getText().toString();

        if (!email.isEmpty()) {

            String sanitizedEmail = email.replace(".", ",");

            usersRef.child(sanitizedEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String userName = dataSnapshot.child("first_name").getValue(String.class);
                        listener.onNameReceived(userName);
                    } else {
                        Log.d("Firebase", "User not found.");
                        listener.onNameReceived("Guest");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Firebase", "Error fetching user data", databaseError.toException());
                    listener.onNameReceived("Guest");
                }
            });
        } else {
            Log.d("Firebase", "No email found in SharedPreferences.");
            listener.onNameReceived("Guest");
        }
    }

    public interface OnGetNameListener {
        void onNameReceived(String userName);
    }

    public void addExerciseToDatabase(Exercise exercise, String email, String date) {
        String sanitizedEmail = email.replace(".", ",");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("usersWorkouts").child(sanitizedEmail);
        DatabaseReference dateRef = userRef.child(date);

        String exerciseName = exercise.getName();
        if (exerciseName != null && !exerciseName.isEmpty()) {
            dateRef.child(exerciseName).setValue(exercise)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Exercise added to database", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to add exercise: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "Exercise name cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void AddFavWorkout(String email, String workout_name) {

        String sanitizedEmail = email.replace(".", ",");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usersWorkouts").child(sanitizedEmail).child("Favorite_Workouts").child(workout_name);
        WorkoutViewModel workoutViewModel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        List<Exercise> selectedExercises = workoutViewModel.getSelectedExercises();

        if (selectedExercises == null || selectedExercises.isEmpty()) {
            Toast.makeText(this, "No exercises selected.", Toast.LENGTH_SHORT).show();
            return;
        }

        myRef.setValue(selectedExercises).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error saving user data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onAddToCurrentWorkout(String workout_name, String email, final DataFetchedCallback callback) {
        final List<Exercise> currentWorkoutExercises = new ArrayList<>();

        // Reference to the "Current_Workout" section in Firebase
        DatabaseReference currentWorkoutRef = FirebaseDatabase.getInstance()
                .getReference("usersWorkouts")
                .child(email.replace(".", ","))  // Firebase email formatting
                .child("Favorite_Workouts")
                .child(workout_name); // The workout name

        // Listen for the data in the "workout_name" node
        currentWorkoutRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through all the exercises under the workout name
                for (DataSnapshot exerciseSnapshot : snapshot.getChildren()) {
                    // Deserialize the exercise object
                    Exercise exercise = exerciseSnapshot.getValue(Exercise.class);
                    if (exercise != null) {
                        currentWorkoutExercises.add(exercise);  // Add exercise to the list
                    }
                }
                // Now notify the caller (MainActivity) that data is fetched
                callback.onDataFetched(currentWorkoutExercises);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors that occur while retrieving the data
                Toast.makeText(MainActivity.this, "Failed to load exercises", Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void onDeleteWorkout(String workout_name, String email){

        DatabaseReference favoriteWorkoutsRef = FirebaseDatabase.getInstance()
                .getReference("usersWorkouts")
                .child(email.replace(".", ",")) // Handle email format for Firebase keys
                .child("Favorite_Workouts");  // Section for favorite workouts

        // Remove workout from favorites
        favoriteWorkoutsRef.child(workout_name).removeValue()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(MainActivity.this, workout_name + " deleted from favorites.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Failed to delete workout from favorites.", Toast.LENGTH_SHORT).show();
                });
    }

    public interface DataFetchedCallback {
        void onDataFetched(List<Exercise> exercises);
    }
}