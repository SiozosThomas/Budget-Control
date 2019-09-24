package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Double.parseDouble;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserInfo extends Fragment {

    private Button btnSave;
    private EditText userName, outcomeLimit;

    public AddUserInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user_info, container, false);
        btnSave = view.findViewById(R.id.btn_save);
        userName = view.findViewById(R.id.txt_set_name);
        outcomeLimit = view.findViewById(R.id.txt_set_outcome_limit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo user = new UserInfo();
                user.setId(0);
                user.setName(userName.getText().toString());
                user.setIncome(1000);
                user.setOutcome(0);
                user.setLimit(parseDouble(outcomeLimit.getText().toString()));

                MainActivity.userDatabase.myUserAccessObject().addUser(user);
                Toast.makeText(getActivity(),"User added successfully", Toast.LENGTH_SHORT).show();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new Balance()).commit();
            }
        });
        return view;
    }

}
