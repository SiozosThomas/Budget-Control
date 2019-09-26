package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphPerMonth extends Fragment {

    private LineChart chart;
    private List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();

    public GraphPerMonth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph_per_month, container, false);
        chart = (LineChart) view.findViewById(R.id.line_chart);
//        chart.setOnChartGestureListener(this);
//        chart.setOnChartValueSelectedListener(this);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);

        Calendar calendar = Calendar.getInstance();
        ArrayList<Integer> months = new ArrayList<>();
        ArrayList<Double> amountsPerMonth = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            months.add(i + 1);
        }
        for (int i = 0; i < 12; i++) {
            amountsPerMonth.add(0.0);
        }
        int find = -1;
        for (Outcome outcome : outcomes) {
            find = -1;
            for (int i = 0; i < months.size(); i++) {
                if (months.get(i) == outcome.getMonth()) {
                    find = i + 1;
                    break;
                }
            }
            if ( find != -1) amountsPerMonth.set(find, amountsPerMonth.get(find) + outcome.getValue());
        }
        System.out.println(amountsPerMonth);
        System.out.println(months);

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            yValues.add(new Entry(months.get(i),  amountsPerMonth.get(i).floatValue()));
        }
        LineDataSet set1 = new LineDataSet(yValues, "Graph per Month");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        return view;
    }
}

