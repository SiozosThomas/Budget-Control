package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddYearForGraph extends Fragment {

    private Button btnSend;
    private EditText year;

    public AddYearForGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_add_year_for_graph, container, false);
        year = view.findViewById(R.id.month_edit_text);
        btnSend = view.findViewById(R.id.send_month);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();
                    boolean find = false;
                    for (Outcome outcome : outcomes) {
                        if (outcome.getYear() == Double.parseDouble(year.getText().toString())) {
                            find = true;
                            break;
                        }
                    }
                    if (find == true) {
                        Bundle bundle = new Bundle();
                        bundle.putString("Year", year.getText().toString());
                        GraphPerMonth graphPerMonth = new GraphPerMonth();
                        graphPerMonth.setArguments(bundle);
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, graphPerMonth).commit();
                    } else {
                        Toast.makeText(getActivity(),"Not valid year", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(),"Wrong value", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

}
