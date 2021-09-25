package com.freisler_stopky.Controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freisler_stopky.Model.Timer;
import com.freisler_stopky.R;

import java.util.Observable;
import java.util.Observer;

public class FragmentStopwatch extends Fragment implements Observer, View.OnClickListener {
    private Timer timer;
    private boolean running;
    private LapButtonListener lapButtonListener;
    private TextView tv_timer;
    private ImageButton start;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        lapButtonListener = (LapButtonListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lapButtonListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        timer = new Timer();
        timer.addObserver(this);

        tv_timer = view.findViewById(R.id.tv_timer);
        start = view.findViewById(R.id.btn_start_stop);
        ImageButton lap = view.findViewById(R.id.btn_lap);
        ImageButton reset = view.findViewById(R.id.btn_reset);

        start.setOnClickListener(this);
        lap.setOnClickListener(this);
        reset.setOnClickListener(this);

        running = timer.getTimerStatus();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_start_stop: {
                timer.runTimer();
                running = timer.getTimerStatus();
                if(running) {
                    start.setBackgroundResource(R.drawable.btn_pause_selector);
                } else
                    start.setBackgroundResource(R.drawable.btn_start_selector);
                break;
            }
            case R.id.btn_lap: {
                lapButtonListener.sendLap(tv_timer.getText().toString());
                break;
            }
            case R.id.btn_reset: {
                timer.resetTimer();
                tv_timer.setText(getString(R.string.default_time));
                start.setBackgroundResource(R.drawable.btn_start_selector);
                lapButtonListener.reset();
                break;
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        tv_timer.setText(timer.setTime());
    }
}