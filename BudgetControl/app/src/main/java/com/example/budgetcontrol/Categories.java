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

    private ProgressBar homeProgressBar, clothesProgressBar, entertainmentProgressBar,
            fuelProgressBar, otherProgressBar;
    private TextView user_name;

    public Categories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_categories, container, false);
        user_name = view.findViewById(R.id.user_name_categories);
        homeProgressBar = view.findViewById(R.id.home_progress_bar);
        clothesProgressBar = view.findViewById(R.id.clothes_progress_bar);
        entertainmentProgressBar = view.findViewById(R.id.entertainment_progress_bar);
        fuelProgressBar = view.findViewById(R.id.fuel_progress_bar);
        otherProgressBar = view.findViewById(R.id.other_progress_bar);

        user_name.setText(getUserName());

        homeProgressBar.setMax((int) Math.round(getUserIncome()));
        homeProgressBar.setProgress(20);

        return view;
    }

    private Double getUserIncome() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0).getIncome();
    }

    private String getUserName() {
        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        return users.get(0).getName();
    }

}
