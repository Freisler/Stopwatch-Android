package com.freisler_stopky.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.freisler_stopky.R;

public class FragmentLaps extends Fragment {
    private TableLayout table;
    private ViewGroup parent;
    private int n;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        this.parent = parent;
        return inflater.inflate(R.layout.fragment_bottom, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        table = view.findViewById(R.id.table_layout);
        n = 0;
    }

    public void addLap(String lap) {
        TableRow row = (TableRow) LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.row_lap, parent, false);
        ((TextView)row.findViewById(R.id.lap_number)).setText(getString(R.string.lap_row, ++n));
        ((TextView)row.findViewById(R.id.lap_time)).setText(lap);
        table.addView(row, 0);
    }

    public void resetTable() {
        n = 0;
        table.removeAllViews();
    }
}