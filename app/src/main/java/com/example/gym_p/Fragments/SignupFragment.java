package com.example.gym_p.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gym_p.Activities.MainActivity;
import com.example.gym_p.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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

        View view= inflater.inflate(R.layout.fragment_signup, container, false);
        EditText etEmail = view.findViewById(R.id.et_email_reg);
        EditText etPassword1=view.findViewById(R.id.et_password1);
        EditText etPassword2=view.findViewById(R.id.et_password2);
        EditText et_first_name = view.findViewById(R.id.et_first_name);
        EditText et_last_name=view.findViewById(R.id.et_last_name);

        Button btnreg=view.findViewById(R.id.btn_reg);
        Button btnlogin = view.findViewById(R.id.btn_login2);

        btnreg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validateAndSignup(etEmail, etPassword1, etPassword2,view,et_first_name,et_last_name);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_logInFragment);
            }
        });
        return view;
    }

    private void validateAndSignup(EditText etEmail, EditText etPassword1, EditText etPassword2,View view,EditText et_first_name, EditText et_last_name) {
        String email = etEmail.getText().toString().trim();
        String password1 = etPassword1.getText().toString().trim();
        String password2 = etPassword2.getText().toString().trim();
        String first_name = et_first_name.getText().toString().trim();
        String last_name = et_last_name.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(password1) || password1.length() <= 4) {
            Toast.makeText(getContext(), "Password must be more than 4 characters", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!password1.equals(password2)) {
            Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.reg(email,password1,first_name,last_name);
        Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_logInFragment);
    }
}