package com.farmermanagement.farmer_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.farmermanagement.farmer_management.database.DatabaseHelper;
import com.farmermanagement.farmer_management.database.model.AddFarmer;

public class FarmerActivity extends AppCompatActivity {

    private EditText farmerName,fatherName,village,mobile;
    private Button save,cancel;
    private  DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer);
        final AddFarmer farmer = setUIValues();
        databaseHelper = new DatabaseHelper(this);

        save = (Button) findViewById(R.id.btn_farmer_save);
        cancel = (Button) findViewById(R.id.btn_farmer_cancel);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.insertFarmer(farmer);
                Toast.makeText(FarmerActivity.this,"Data saved successfully.",Toast.LENGTH_SHORT).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmerName.getText().clear();
                fatherName.getText().clear();
                village.getText().clear();
                mobile.getText().clear();
                Toast.makeText(FarmerActivity.this,"Data Clear.",Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Set UI value to Java Bean
     * @return AddFarmer
     */
    private AddFarmer setUIValues(){
        // Set UI value to
        farmerName = (EditText) findViewById(R.id.add_farmer_name);
        fatherName = (EditText) findViewById(R.id.add_farmer_father_name);
        village = (EditText) findViewById(R.id.add_farmer_village);
        mobile = (EditText) findViewById(R.id.add_farmer_mobile);

        return new AddFarmer(null,
                farmerName.getText().toString(),
                fatherName.getText().toString(),
                village.getText().toString(),
                mobile.getText().toString(),
                null);
    }


}
