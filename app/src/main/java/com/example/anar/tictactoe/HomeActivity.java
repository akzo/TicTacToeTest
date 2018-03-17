package com.example.anar.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    public void xButton(View v){
        pageChanger("X");
    }

    public void oButton(View v){
        pageChanger("0");
    }

    public void pageChanger(final String player){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(HomeActivity.this, GameZone.class);
                homeIntent.putExtra("player", player);
                startActivity(homeIntent);
                finish();
            }
        }, 1500);
    }
}
