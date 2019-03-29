package com.example.bloodbank;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.math.MathContext;



public class MainActivity extends AppCompatActivity {
    Button buttonDonor;
    public static String donorId="no";
    SharedPreferences sharedPreferences;

    public static String lat;
    public static String lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonDonor = (Button) findViewById(R.id.btn_donor_profile);

            buttonDonor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, DonorForm.class));


                }
            });
        }
}

