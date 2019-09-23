package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Balance extends Fragment {

    private TextView viewUserName;

    public Balance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);
        viewUserName = view.findViewById(R.id.textView);

        List<UserInfo> users = MainActivity.userDatabase.myUserAccessObject().getUsers();
        viewUserName.setText(users.get(0).getName().toString());

        return view;
    }

}
