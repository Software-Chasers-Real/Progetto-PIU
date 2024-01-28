package it.uniba.dib.piu.softwarechasers.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import it.uniba.dib.piu.softwarechasers.fitnessapp.access.LoginSignupActivity;

public class SplashActivity extends AppCompatActivity {

    private boolean loggato = false;
    private boolean fecthCompletato = false;
    private static final long MIN_WAIT_INTERVAL = 1500L;
    private static final long MAX_WAIT_INTERVAL = 3000L;
    private static final int GO_AHEAD_WHAT = 1;
    private static final int FECTH_TERMINATO = 2;
    private long mStartTime;
    private boolean mIsDone;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                    if ((elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone) && fecthCompletato) {
                        mIsDone = true;
                        startMainActivity();
                    }
                    break;
                case FECTH_TERMINATO:
                    if (fecthCompletato) {
                        mIsDone = true;
                        startMainActivity();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        Log.d("SplashActivity", "OnCreate");
        decorView.setSystemUiVisibility(uiOptions);
        fetchDataFromDataBase();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStartTime = SystemClock.uptimeMillis();
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
    }

    private void fetchDataFromDataBase() {
        loggato = false;
        fecthCompletato = true;
    }

    private void startMainActivity() {
        Log.d("SplashActivity", "Login lanciata");
        startActivity(new Intent(this, LoginSignupActivity.class));
        finish();
    }
}
