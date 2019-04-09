package com.example.bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DonorForm extends AppCompatActivity {
    public static final String SHARED_PREFS= "sharedPrefs";
    public static int i=1;
    public static final String val= "";

    Spinner cityChoice;
    Spinner groupChoice;
    Spinner locationChoice;

    EditText Name;
    EditText Mobile;

    Button Save;

    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_form);
        loadData();


        cityChoice = (Spinner) findViewById(R.id.dropdownCity);

        String[] citis = new String[]{"Barisal","Chittagong", "Dhaka", "Mymensingh","Khulna", "Rajshahi", "Rangpur", "Sylhet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, citis);
        cityChoice.setAdapter(adapter);


        locationChoice = (Spinner) findViewById(R.id.dropdownLocation);

        String[] location = new String[]{"kalabagan","dhanmondi", "Mohammadpur", "Lalmatia","Mirpur", "banani", "Gulshan", "Mohakhali", "Pachlaish", "Agrabad", "Kulshi", "raj1", "raj2"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, location);
        locationChoice.setAdapter(adapter3);


        groupChoice = (Spinner) findViewById(R.id.dropdownGroup);
        String[] group = new String[]{"O+","O-", "A+", "B+","A-", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, group);
        groupChoice.setAdapter(adapter1);

        Name = (EditText) findViewById(R.id.edt_name);
        Mobile = (EditText) findViewById(R.id.edt_mobileNumber);
        Save =  findViewById(R.id.btn_saveDonor);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Donors");

                String postid =  String.valueOf(i);

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Full_Name", Name.getText().toString());
                hashMap.put("City", cityChoice.getSelectedItem().toString());
                hashMap.put("Location", locationChoice.getSelectedItem().toString());
                hashMap.put("Blood_Group", groupChoice.getSelectedItem().toString());
                hashMap.put("Number", Mobile.getText().toString());


                reference.child(postid).setValue(hashMap);

                Toast.makeText(DonorForm.this,"Added Successfully", Toast.LENGTH_LONG).show();
                i++;
                saveData();




            }
            });
    }
    public void saveData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putInt(val,i);
        editor.apply();

    }
    public void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        i=sharedPreferences.getInt(val,i);

    }
}



