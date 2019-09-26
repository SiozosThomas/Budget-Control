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
public class Balance extends Fragment implements View.OnClickListener {

    private TextView viewUserName, viewIncome, viewOutcome, viewBalance;
    private Button btnIncome, btnOutcome;

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
        btnIncome = view.findViewById(R.id.add_income_button);
        viewBalance = view.findViewById(R.id.balance_txt);
        btnIncome.setOnClickListener(this);
        btnOutcome = view.findViewById(R.id.add_outcome_button);
        btnOutcome.setOnClickListener(this);

        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        viewUserName.setText(users.get(0).getName().toString());
        viewIncome.setText("Income: \t" + String.valueOf(users.get(0).getIncome()));
        viewOutcome.setText("Outcome: \t" + String.valueOf(users.get(0).getOutcome()));
        viewBalance.setText("Balance: \t" + String.valueOf(users.get(0).getIncome() - users.get(0).getOutcome()));
        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_income_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddIncome()).addToBackStack(null).commit();
                break;
            case R.id.add_outcome_button:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddOutcome()).addToBackStack(null).commit();
                break;
        }
    }

}
