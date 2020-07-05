package com.example.gcuitfesst;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.onesignal.OneSignal;


public class MainActivity extends AppCompatActivity {
    private ImageView iv1;
    Animation animation;
    private static int Splash_time=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1=(ImageView) findViewById(R.id.iv);

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_dow);
        iv1.setAnimation(animation);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent second_page= new Intent(MainActivity.this,LoginPageScreen.class);
                startActivity(second_page);
                overridePendingTransition(R.anim.slide_right,R.anim.slide_out);
                finish();
            }
        },Splash_time);
    }
}
