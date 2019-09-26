package com.example.budgetcontrol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GraphPerYear extends Fragment {

    private LineChart chart;
    private List<Outcome> outcomes = MainActivity.outcomeDatabase.outcomeDao().getOutcomes();

    public GraphPerYear() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_graph_per_year, container, false);
        chart = (LineChart) view.findViewById(R.id.line_chart);
//        chart.setOnChartGestureListener(this);
//        chart.setOnChartValueSelectedListener(this);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(false);

        Calendar calendar = Calendar.getInstance();
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Double> amountsPerYear = new ArrayList<>();
        for (int i = 0; i < getMaxYear() - getMinimumYear() + 1; i++) {
            years.add(getMinimumYear() + i);
        }
        for (int i = 0; i < getMaxYear() - getMinimumYear() + 1; i++) {
            amountsPerYear.add(0.0);
        }
        int find = -1;
        for (Outcome outcome : outcomes) {
            find = -1;
            for (int i = 0; i < years.size(); i++) {
                if (years.get(i) == outcome.getYear()) {
                    find = i;
                    break;
                }
            }
            if ( find != -1) amountsPerYear.set(find, amountsPerYear.get(find) + outcome.getValue());
        }
        System.out.println(amountsPerYear);
        System.out.println(years);

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < years.size(); i++) {
            yValues.add(new Entry(years.get(i),  amountsPerYear.get(i).floatValue()));
        }
        LineDataSet set1 = new LineDataSet(yValues, "Graph per Year");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        chart.setData(data);
        return view;
    }

    private int getMinimumYear() {
        int min = 100000;
        for (Outcome outcome : outcomes) {
            if (outcome.getYear() < min) min = outcome.getYear();
        }
        return min;
    }

    private int getMaxYear() {
        int max = 0;
        for (Outcome outcome : outcomes) {
            if (outcome.getYear() > max) max = outcome.getYear();
        }
        return max;
    }

}
