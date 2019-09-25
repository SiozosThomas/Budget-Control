package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OutcomeLimit extends Fragment {

    private TextView currentLimitTextView;
    private Button btnSave;
    private EditText valueTextView;


    public OutcomeLimit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outcome_limit, container, false);
        currentLimitTextView = view.findViewById(R.id.current_out_limit_tv);
        btnSave = view.findViewById(R.id.btn_pic);
        valueTextView = view.findViewById(R.id.editTextValue);

        currentLimitTextView.setText("Your current outcome limit is: " + getUserLimit() + " ");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserInfo user = new UserInfo();
                user = getUser();
                if (!valueTextView.toString().isEmpty()) {
                    try {
                        user.setLimit(Double.parseDouble(valueTextView.getText().toString()));
                        MainActivity.userDatabase.myUserAccessObject().updateUserInfo(user);
                        Toast.makeText(getActivity(),"Limit added successfully", Toast.LENGTH_SHORT).show();
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new Balance()).commit();
                    } catch (NumberFormatException e) {
                        Toast.makeText(getActivity(),"Wrong value", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    private double getUserLimit() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0).getLimit();
    }

    private UserInfo getUser() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0);
    }

}
