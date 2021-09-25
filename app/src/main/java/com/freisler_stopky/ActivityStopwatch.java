package com.freisler_stopky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.freisler_stopky.Controller.FragmentLaps;
import com.freisler_stopky.Controller.LapButtonListener;
import com.freisler_stopky.Controller.ActivityLastLaps;
import com.freisler_stopky.Model.FileOperations;

import java.util.Objects;

public class ActivityStopwatch extends AppCompatActivity implements LapButtonListener {
    private FragmentLaps fragmentLaps;
    private FileOperations fileOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        Button stopwatch = findViewById(R.id.btn1);
        Button last_laps = findViewById(R.id.btn2);

        stopwatch.setSelected(true);
        last_laps.setSelected(false);
        last_laps.setTextColor(Color.BLACK);

        fileOperations = new FileOperations();

        last_laps.setOnClickListener(view1 -> {
            Intent i = new Intent(this, ActivityLastLaps.class);
            startActivity(i);
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentLaps = (FragmentLaps) fragmentManager.findFragmentById(R.id.fragment2);
    }

    @Override
    public void sendLap(String lap) {
        fragmentLaps.addLap(lap);
        fileOperations.writeToFile(this, "last_laps.txt", lap + "\n", true);
    }

    @Override
    public void reset() {
        fragmentLaps.resetTable();
    }
}