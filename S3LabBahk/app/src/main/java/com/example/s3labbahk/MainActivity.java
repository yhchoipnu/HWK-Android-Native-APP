package com.example.s3labbahk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    protected Button btn0;
    protected Button btn1;
    protected Button btn2;
    protected Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HWK App");  // 해당 액티비티의 toolbar에 있는 title

        btn0 = findViewById(R.id.btnMap);
        btn1 = findViewById(R.id.btnEst);
        btn2 = findViewById(R.id.btnReport);
        btn3 = findViewById(R.id.btnEmail);


        btn0.setBackgroundColor(Color.parseColor("#D1B6E1"));
        btn0.setTextColor(Color.WHITE);
        btn1.setBackgroundColor(Color.parseColor("#519D9E"));
        btn1.setTextColor(Color.WHITE);
        btn2.setBackgroundColor(Color.parseColor("#58C9B9"));
        btn2.setTextColor(Color.WHITE);
        btn3.setBackgroundColor(Color.parseColor("#9DC8C8"));
        btn3.setTextColor(Color.WHITE);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                // run a SubActivity
                startActivityForResult(new Intent(MainActivity.this, LocationActivity.class), 100);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                startActivityForResult(new Intent(MainActivity.this, EstimateActivity.class), 200);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                startActivityForResult(new Intent(MainActivity.this, ReportActivity.class), 300);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                startActivityForResult(new Intent(MainActivity.this, EmailActivity.class), 400);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, "Back to Main", Toast.LENGTH_SHORT).show();
    }
}