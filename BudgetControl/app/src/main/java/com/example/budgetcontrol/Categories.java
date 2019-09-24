package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Categories extends Fragment {

    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);
        ProgressBar homeProgressBar, clothesProgressBar, entertainmentProgressBar,
                fuelProgressBar, otherProgressBar;
        TextView homeTextView, clothesTextView, enterTextView, fuelTextView, otherTextView;
        homeTextView = view.findViewById(R.id.home_text_view);
        clothesTextView = view.findViewById(R.id.clothes_text_view);
        enterTextView = view.findViewById(R.id.entertainment_text_view);
        fuelTextView = view.findViewById(R.id.fuel_text_view);
        otherTextView = view.findViewById(R.id.other_text_view);
        homeTextView.setText("Home: \t" + getTotalAmountOfType("Home").toString());
        clothesTextView.setText("Clothes: \t" + getTotalAmountOfType("Clothes").toString());
        enterTextView.setText("Entertainment: \t" + getTotalAmountOfType("Entertainment").toString());
        fuelTextView.setText("Fuel: \t" + getTotalAmountOfType("Fuel").toString());
        otherTextView.setText("Other: \t" + getTotalAmountOfType("Other").toString());
        homeProgressBar = view.findViewById(R.id.home_progress_bar);
        clothesProgressBar = view.findViewById(R.id.clothes_progress_bar);
        entertainmentProgressBar = view.findViewById(R.id.entertainment_progress_bar);
        fuelProgressBar = view.findViewById(R.id.fuel_progress_bar);
        otherProgressBar = view.findViewById(R.id.other_progress_bar);

        homeProgressBar.setMax((int) Math.round(getUserIncome()));
        homeProgressBar.setProgress((int) Math.round(getTotalAmountOfType("Home")));

        clothesProgressBar.setMax((int) Math.round(getUserIncome()));
        clothesProgressBar.setProgress((int) Math.round(getTotalAmountOfType("Clothes")));

        entertainmentProgressBar.setMax((int) Math.round(getUserIncome()));
        entertainmentProgressBar.setProgress((int) Math.round(getTotalAmountOfType("Entertainment")));

        fuelProgressBar.setMax((int) Math.round(getUserIncome()));
        fuelProgressBar.setProgress((int) Math.round(getTotalAmountOfType("Fuel")));

        otherProgressBar.setMax((int) Math.round(getUserIncome()));
        otherProgressBar.setProgress((int) Math.round(getTotalAmountOfType("Other")));

        return view;
    }

    private Double getUserIncome() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0).getIncome();
    }

    private Double getTotalAmountOfType(String type) {
        List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();
        double totalAmount = 0.0;
        for (Outcome outcome : outcomes) if (outcome.getType().equals(type))
            totalAmount += outcome.getValue();
        return totalAmount;
    }

}
