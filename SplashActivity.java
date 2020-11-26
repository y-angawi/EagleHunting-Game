package com.example.johndoe.eaglehunting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent main =  new Intent(SplashActivity.this, StartActivity.class);
                    startActivity(main);
                    finish();
                }

            }
        };
        thread.start();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }
}
