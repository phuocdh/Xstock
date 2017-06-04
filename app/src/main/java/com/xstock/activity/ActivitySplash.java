package com.xstock.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xstock.R;

/**
 * Created by Administrator on 6/4/2017.
 */

public class ActivitySplash extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
                finish();
            }
        }, 1000);
    }
}
