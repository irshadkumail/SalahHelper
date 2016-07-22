package com.sodo.kumail.salahhelper;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

public class SplashActivity extends Activity {

    Handler handler;
    StartApp startApp;
    TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashText = (TextView) findViewById(R.id.splash_text);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "ArabDances.ttf");
        splashText.setTypeface(typeface);

        if (MyApplication.CURRENT_VERSION < Build.VERSION_CODES.M) {
            handler = new Handler();
            startApp = new StartApp();
            handler.postDelayed(startApp, 3000);
        } else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MainActivity.REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    handler = new Handler();
                    startApp = new StartApp();
                    handler.postDelayed(startApp, 2000);


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    class StartApp implements Runnable {
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();


        }
    }

}
