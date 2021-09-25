package com.freisler_stopky.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.freisler_stopky.Model.LastLaps;
import com.freisler_stopky.R;

import java.util.ArrayList;

public class ActivityLastLaps extends AppCompatActivity {
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laps);

        Button stopwatch = findViewById(R.id.btn1);
        Button last_laps = findViewById(R.id.btn2);

        stopwatch.setSelected(false);
        stopwatch.setTextColor(Color.BLACK);
        last_laps.setSelected(true);

        stopwatch.setOnClickListener(view -> finish());

        table = findViewById(R.id.table_last_laps);

        LastLaps lastLaps = new LastLaps(this, "last_laps.txt");
        ArrayList<String> lapsArray = lastLaps.getLapsArray();

        for(int i = 20; i >= 1; i--) {
            addLastLap(i, lapsArray);
        }
    }

    private void addLastLap(int i, ArrayList<String> laps) {
        @SuppressLint("InflateParams")
        TableRow row = (TableRow) LayoutInflater.from(this).inflate(R.layout.row_lap, null, false);
        ((TextView)row.findViewById(R.id.lap_number)).setText(getString(R.string.lap_row, i));
        if((i >= 1) && (i <= laps.size()) && !laps.get(i - 1).equals(""))
            ((TextView)row.findViewById(R.id.lap_time)).setText(laps.get(i - 1));
        else
            ((TextView)row.findViewById(R.id.lap_time)).setText("---");
        table.addView(row, 0);
    }
}