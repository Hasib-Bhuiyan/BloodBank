package com.example.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NeedBlood extends AppCompatActivity {
    Spinner groupChoice;
    Spinner cityChoice;
    Spinner locationChoice;
    Button need;
    String group;
    String city;
    String location;
    public static String donorname [] = new String[100];
    public static String donornum  [] = new String[100];
    public static String donorloc [] = new String[100];
    public static int tablec=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_blood);
        cityChoice = (Spinner) findViewById(R.id.needCity);
        locationChoice = (Spinner) findViewById(R.id.needlocation);
        groupChoice = (Spinner) findViewById(R.id.needGroup);
        need = (Button) findViewById(R.id.startSearch);

        String[] citis = new String[]{"Barisal","Chittagong", "Dhaka", "Mymensingh","Khulna", "Rajshahi", "Rangpur", "Sylhet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, citis);
        cityChoice.setAdapter(adapter);


        String[] group = new String[]{"O+","O-", "A+", "B+","A-", "B-", "AB+", "AB-"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, group);
        groupChoice.setAdapter(adapter1);

        String[] location = new String[]{"kalabagan","dhanmondi", "Mohammadpur", "Lalmatia","Mirpur", "banani", "Gulshan", "Mohakhali", "Pachlaish", "Agrabad", "Kulshi", "raj1", "raj2"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, location);
        locationChoice.setAdapter(adapter3);

        need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String group = groupChoice.getSelectedItem().toString();
                final String city = cityChoice.getSelectedItem().toString();
                final String location = locationChoice.getSelectedItem().toString();
                //Toast.makeText(NeedBlood.this,group, Toast.LENGTH_LONG).show();

                for(int i=0; i<100; i++) {

                    FirebaseDatabase.getInstance().getReference().child("Donors").child(i+"")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        String x = dataSnapshot.child("City").getValue(String.class);
                                        String y = dataSnapshot.child("Blood_Group").getValue(String.class);
                                        String z = dataSnapshot.child("Location").getValue(String.class);
                                        String k = dataSnapshot.child("Full_Name").getValue(String.class);
                                        String p = dataSnapshot.child("Number").getValue(String.class);

                                        if(x.equals(city) && (y.equals(group))&& z.equals(location))
                                        {
                                            donorname[tablec]=k;
                                            donornum[tablec]=p;
                                            donorloc[tablec]=z;
                                            tablec++;
                                        }
                                    }

                                    startActivity(new Intent(NeedBlood.this, DonorList.class));


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                }
                //startActivity(new Intent(NeedBlood.this, DonorList.class));

            }
        });
    }
}
