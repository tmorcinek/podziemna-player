package pl.morcinek.podziemnaplayer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashActivity extends ActionBarActivity implements Runnable {

    public static final int SPLASH_DELAY_MILLIS = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(this, SPLASH_DELAY_MILLIS);
    }

    @Override
    public void run() {
        if (!isFinishing()) {
            // TODO Start HomeActivity
            finish();
        }
    }
}
