package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gym_p.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String email = "";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ImageButton ImButtonCalendar = view.findViewById(R.id.ImButtonCalendar);
        ImageButton displayFavorite = view.findViewById(R.id.ButtonDisplayFavorites);
        ImageButton profileSettings = view.findViewById(R.id.IBProfileSettings);
        ImageButton SearchGymButton = view.findViewById(R.id.IBSearchGym);


        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("user_email");
        }

        ImButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ManageWorkouts_Fragment(),email);
            }
        });

        displayFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new FavoritesFragment(),email);
            }
        });

        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new ProfileSettingsFragment(),email);
            }
        });

        SearchGymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SearchGymFragment(),email);
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment, String email) {

        // Create a Bundle to pass data
        Bundle bundle = new Bundle();
        bundle.putString("user_email", email); // Add the email to the bundle

        // Set the arguments to the fragment
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager(); // Use childFragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null); // Optional: Allows back navigation
        transaction.commit();
    }
}