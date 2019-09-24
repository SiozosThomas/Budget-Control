package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Balance extends Fragment {

    private TextView viewUserName, viewIncome, viewOutcome;
    private Button btnSave;

    public Balance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        viewUserName = view.findViewById(R.id.user_name_balance);
        viewIncome = view.findViewById(R.id.income_txt);
        viewOutcome = view.findViewById(R.id.outcome_txt);
        btnSave = view.findViewById(R.id.add_outcome_button);

        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        viewUserName.setText(users.get(0).getName().toString());
        viewIncome.setText("Income: \t" + String.valueOf(users.get(0).getIncome()));
        viewOutcome.setText("Oucome: \t" + String.valueOf(users.get(0).getOutcome()));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddOutcome()).addToBackStack(null).commit();
            }
        });
        return view;
    }

}
