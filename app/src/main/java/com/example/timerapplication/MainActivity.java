package com.example.timerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME = 600000;

    private TextView mTextView;
    private Button mButtonStartPause;
    private Button getmButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        getmButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    System.out.println(mTimerRunning);
                    if(mTimerRunning){
                        pauseTimer();
                    }else{
                        startTimer();
                    }
                }
        });

        getmButtonReset.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    resetTimer();
                }
        });

        updateTextView();
    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateTextView();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mTextView.setText("00:00");
                mButtonStartPause.setText("スタート");
                getmButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        System.out.println("一時停止処理前：" + mTimerRunning);
        mCountDownTimer.cancel();
        mTimerRunning = false;
        System.out.println("一時停止処理後：" + mTimerRunning);
        mButtonStartPause.setText("スタート");
        getmButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME;
        updateTextView();
        mButtonStartPause.setVisibility(View.VISIBLE);
        getmButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateTextView(){
        int minutes = (int)(mTimeLeftInMillis / 1000) / 60;
        int seconds = (int)(mTimeLeftInMillis / 1000) % 60;
        int miliSeconds = (int)(mTimeLeftInMillis / 60) % 10;
        String timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d.%01d", minutes, seconds, miliSeconds);
        mTextView.setText(timerLeftFormatted);
    }




}
