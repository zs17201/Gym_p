package com.example.gym_p.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym_p.Activities.MainActivity;
import com.example.gym_p.Classes.WorkoutViewModel;
import com.example.gym_p.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String email = "";

    public ProfileSettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSettingsFragment newInstance(String param1, String param2) {
        ProfileSettingsFragment fragment = new ProfileSettingsFragment();
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

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_settings, container, false);

        Button change_name,change_pass,delete_account;
        EditText EditText_name,EditText_pass;
        TextView TextView_email;

        change_name = view.findViewById(R.id.saveNameButton);
        change_pass = view.findViewById(R.id.savePasswordButton);
        delete_account = view.findViewById(R.id.deleteAccountButton);
        EditText_name = view.findViewById(R.id.nameEditText);
        TextView_email = view.findViewById(R.id.ETEmail);

        EditText_pass = view.findViewById(R.id.passwordEditTextSettings);
        MainActivity mainActivity = (MainActivity) getActivity();



        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("user_email");
            if(email != null) {
                TextView_email.setText(email);
            }
        }

        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =((EditText)EditText_name).getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                }
                else{
                    mainActivity.changeName(email,name);
                    Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_logInFragment);
                }
            }
        });

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass =((EditText)EditText_pass).getText().toString().trim();

                if (pass.length() <= 5) {
                    Toast.makeText(getContext(),"Password must be more than 5 characters", Toast.LENGTH_SHORT).show();
                }
                else{
                    mainActivity.changePass(email,pass);
                    EditText_pass.setText("");
                }

            }
        });

        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.deleteAccount(email);
                WorkoutViewModel viewModel = new ViewModelProvider((AppCompatActivity) getContext()).get(WorkoutViewModel.class);
                viewModel.clearExercises();
                Navigation.findNavController(view).navigate(R.id.action_mainPageFragment_to_logInFragment);
            }
        });


        return view;
    }
}