package com.vinay.loginform;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText username;
    EditText password;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Select Category"); // This is the hint
        spinnerItems.add("Supervisor");
        spinnerItems.add("Manager");
        spinnerItems.add("Admin");
        spinnerItems.add("Staff");
        spinnerItems.add("Clients");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, spinnerItems) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0; // Disable the hint item
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if (position == 0) {
                    textView.setTextColor(Color.GRAY); // Set the hint text color
                } else {
                    textView.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        username = findViewById(R.id.edit_text_username);
        password = findViewById(R.id.edit_text_password);
        button = findViewById(R.id.sign_in_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String selectedItem = spinner.getSelectedItem().toString();
                String admin = "admin";
                if(user.equals(admin) && pass.equals(admin) && selectedItem.equals("Manager")){
                    Intent adminActivityIntent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(adminActivityIntent);
                    //Toast.makeText(MainActivity.this, "Hello "+admin+ " you have logged in successfully !!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Enter Valid Details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i > 0) { // Skip the hint item
            String selectedItem = adapterView.getItemAtPosition(i).toString();
            Toast.makeText(adapterView.getContext(), "Selected: " + selectedItem, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}