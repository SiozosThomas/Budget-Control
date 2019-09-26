package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncome extends Fragment {

    private EditText valueTxt, labelTxt;
    private Button btnSave;


    public AddIncome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);
        valueTxt = view.findViewById(R.id.editTextValue);
        labelTxt = view.findViewById(R.id.labelEditText);
        btnSave = view.findViewById(R.id.save_button_income);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Income income = new Income();
                List<Income> incomes = MainActivity.incomeDatabase.incomeDao().getIncomes();
                if ( incomes.isEmpty()) {
                    income.setId(0);
                } else {
                    income.setId(incomes.get(incomes.size() - 1).getId() + 1);
                }
                try {
                    UserInfo user = getUser();
                    user.setIncome(getUserIncome() + Double.parseDouble(valueTxt.getText().toString()));
                    MainActivity.userDatabase.myUserAccessObject().updateUserInfo(user);
                    income.setValue(Double.parseDouble(valueTxt.getText().toString()));
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    income.setYear(year);
                    income.setMonth(month);
                    income.setDay(day);
                    MainActivity.incomeDatabase.incomeDao().addIncome(income);
                    Toast.makeText(getActivity(),"Income added successfully", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(),"Wrong value", Toast.LENGTH_SHORT).show();
                }

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new Balance()).commit();
            }
        });
        return view;
    }

    private UserInfo getUser() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0);
    }

    private double getUserIncome() {
        return getUser().getIncome();
    }

}
