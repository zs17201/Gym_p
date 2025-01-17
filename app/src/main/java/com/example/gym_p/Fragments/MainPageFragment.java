package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gym_p.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String email;

    public MainPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainpage, container, false);

        // Set up button click listeners
        ImageButton currentButton = view.findViewById(R.id.current_button);
        ImageButton workoutsButton = view.findViewById(R.id.workoutsButton);
        ImageButton settingsButton = view.findViewById(R.id.setting_button);
        TextView userName = view.findViewById(R.id.topNavbarTitle);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("user_name", "Guest");
            userName.setText("Hellow, " + name);
            email = bundle.getString("user_email");
        }

        replaceFragment(new StartPageFragment(),email);

        // Handle button clicks and send data to the next fragment
        currentButton.setOnClickListener(v -> replaceFragment(new CurrentFragment(),email));
        workoutsButton.setOnClickListener(v -> replaceFragment(new WorkoutsFragment(),email));
        settingsButton.setOnClickListener(v -> replaceFragment(new SettingsFragment(),email));

        return view;
    }
    // Helper method to replace fragments
    private void replaceFragment(Fragment fragment, String email) {

        // Create a Bundle to pass data
        Bundle bundle = new Bundle();
        bundle.putString("user_email", email); // Add the email to the bundle

        // Set the arguments to the fragment
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager(); // Use childFragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null); // Optional: Allows back navigation
        transaction.commit();
    }
}