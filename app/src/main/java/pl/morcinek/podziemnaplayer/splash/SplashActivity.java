package pl.morcinek.podziemnaplayer.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import pl.morcinek.podziemnaplayer.R;
import pl.morcinek.podziemnaplayer.home.HomeActivity;


public class SplashActivity extends ActionBarActivity implements Runnable {

    public static final int SPLASH_DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(this, SPLASH_DELAY_MILLIS);
    }

    @Override
    public void run() {
        if (!isFinishing()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}
