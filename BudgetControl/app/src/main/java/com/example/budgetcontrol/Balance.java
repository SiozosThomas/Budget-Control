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

    private TextView viewUserName, viewIncome, viewOutcome;
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
        btnIncome.setOnClickListener(this);
        btnOutcome = view.findViewById(R.id.add_outcome_button);
        btnOutcome.setOnClickListener(this);

        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        viewUserName.setText(users.get(0).getName().toString());
        setIncome();
        viewIncome.setText("Income: \t" + String.valueOf(users.get(0).getIncome()));
        setOutcome();
        viewOutcome.setText("Oucome: \t" + String.valueOf(users.get(0).getOutcome()));
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

    private void setIncome() {
        UserInfo user = new UserInfo();
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        user = users.get(0);
        user.setIncome(getTotalIncome());
        MainActivity.userDatabase.myUserAccessObject().updateUserInfo(user);
    }

    private double getTotalIncome() {
        List<Income> incomes = MainActivity.incomeDatabase.incomeDao().getIncomes();
        double totalAmount = 0.0;
        for (Income income : incomes) totalAmount += income.getValue();
        return totalAmount;
    }

    private void setOutcome() {
        UserInfo user = new UserInfo();
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        user = users.get(0);
        user.setOutcome(getTotalOutcome());
        MainActivity.userDatabase.myUserAccessObject().updateUserInfo(user);
    }

    private double getTotalOutcome() {
        List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();
        double totalAmount = 0.0;
        for (Outcome outcome : outcomes) totalAmount += outcome.getValue();
        return totalAmount;
    }

}
