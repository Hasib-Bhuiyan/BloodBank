package com.example.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DonorList extends AppCompatActivity {
    private TableLayout tableLayout;
    private TableRow    tableRow;
    private TextView dname, did, dose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        setupUILogin();

        for(int i = 0; i < NeedBlood.tablec; i+=5){

            TableRow tr =  new TableRow(this);
            TextView c1 = new TextView(this);
            c1.setText(NeedBlood.donorname[i]);
            TextView c2 = new TextView(this);
            c2.setText(NeedBlood.donorloc[i]+"");
            TextView c3 = new TextView(this);
            c3.setText(NeedBlood.donornum[i]);
            tr.addView(c1);
            tr.addView(c2);
            tr.addView(c3);
            tableLayout.addView(tr);
        }
    }

    private void setupUILogin()
    {
        tableLayout= (TableLayout)findViewById(R.id.table);
        tableRow= (TableRow)findViewById(R.id.data);
        dname= (TextView)findViewById(R.id.drugname);
        did= (TextView)findViewById(R.id.drugind);
        dose= (TextView)findViewById(R.id.drugdose);


    }
}
