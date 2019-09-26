package com.example.budgetcontrol;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddOutcome extends Fragment {

    private RadioButton homeRadioButton, clothesRadioButton, enterRadioButton, fuelRadioButton,
            otherRadioButton;
    private String whichRButton = "Other";
    private EditText valueEditText;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private int day;
    private int month;
    private int year;


    public AddOutcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_outcome, container, false);
        Button btnSave, btnDate;
        btnSave = view.findViewById(R.id.save_button_outcome);
        valueEditText = view.findViewById(R.id.editTextValue);
        homeRadioButton = view.findViewById(R.id.home_r_button);
        clothesRadioButton = view.findViewById(R.id.clothes_r_button);
        enterRadioButton = view.findViewById(R.id.entertainment_r_button);
        fuelRadioButton = view.findViewById(R.id.fuel_r_button);
        otherRadioButton = view.findViewById(R.id.other_r_button);
        btnDate = view.findViewById(R.id.date_button);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                setYear(calendar.get(Calendar.YEAR));
                setMonth(calendar.get(Calendar.MONTH));
                setDay(calendar.get(Calendar.DAY_OF_MONTH));
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, getYear(), getMonth(), getDay());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setYear(year);
                setMonth(month);
                setDay(dayOfMonth);
                Toast.makeText(getActivity(),"date: " + dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
            }
        };

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();
                Outcome outcome = new Outcome();
                if (homeRadioButton.isChecked()) whichRButton = "Home";
                if (clothesRadioButton.isChecked()) whichRButton = "Clothes";
                if (enterRadioButton.isChecked()) whichRButton = "Entertainment";
                if (fuelRadioButton.isChecked()) whichRButton = "Fuel";
                if (otherRadioButton.isChecked()) whichRButton = "Other";
                if ( outcomes.isEmpty()) {
                    outcome.setId(0);
                } else {
                    outcome.setId(outcomes.get(outcomes.size() - 1).getId() + 1);
                }
                outcome.setType(whichRButton);
                outcome.setYear(getYear());
                outcome.setMonth(getMonth());
                outcome.setDay(getDay());
                try {
                    UserInfo user = getUser();
                    user.setOutcome(getUserOutcome() + Double.parseDouble(valueEditText.getText().toString()));
                    MainActivity.userDatabase.myUserAccessObject().updateUserInfo(user);
                    outcome.setValue(Double.parseDouble(valueEditText.getText().toString()));
                    MainActivity.outcomeDatabase.outcomeDao().addOutcome(outcome);
                    Toast.makeText(getActivity(),"Outcome added successfully", Toast.LENGTH_SHORT).show();
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

    private double getUserOutcome() {
        return getUser().getOutcome();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return this.month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

}
