package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddOutcome extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton homeRadioButton, clothesRadioButton, enterRadioButton, fuelRadioButton,
            otherRadioButton;
    private Button btnSave;
    private String whichRButton = "Other";
    private EditText valueEditText;


    public AddOutcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_outcome, container, false);
        btnSave = view.findViewById(R.id.save_button_outcome);
        valueEditText = view.findViewById(R.id.editTextValue);
        radioGroup = view.findViewById(R.id.radioGroup);
        homeRadioButton = view.findViewById(R.id.home_r_button);
        clothesRadioButton = view.findViewById(R.id.clothes_r_button);
        enterRadioButton = view.findViewById(R.id.entertainment_r_button);
        fuelRadioButton = view.findViewById(R.id.fuel_r_button);
        otherRadioButton = view.findViewById(R.id.other_r_button);

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
                try {
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

}
