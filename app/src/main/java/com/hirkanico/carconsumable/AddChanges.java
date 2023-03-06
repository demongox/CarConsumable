package com.hirkanico.carconsumable;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.hirkanico.carconsumable.classes.CarConsumableObject;
import com.hirkanico.carconsumable.library.DBManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddChanges extends AppCompatActivity {

    EditText carCurrentKilometer;
    EditText editChangingPrice;
    EditText editDescription;
    AppCompatButton btnAddChange;
    Spinner consumableSpinner;

    DBManager database;

    List<CarConsumableObject> allConsumable;
    String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_changes);

        carCurrentKilometer = findViewById(R.id.carCurrentKilometer);
        editChangingPrice = findViewById(R.id.editChangingPrice);
        editDescription = findViewById(R.id.editDescription);
        btnAddChange = findViewById(R.id.btnAddChange);
        consumableSpinner = findViewById(R.id.consumableSpinner);

        database = new DBManager(AddChanges.this);
        database.open();

        loadSpinnerData();

        btnAddChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedId.equals("") || carCurrentKilometer.getText().toString().equals("") ||
                        editChangingPrice.getText().toString().equals("") ||
                        editDescription.getText().toString().equals(""))
                {
                    Toast.makeText(AddChanges.this,"all parameters must be determined", Toast.LENGTH_LONG).show();
                    return;
                }
                String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                database.insertNewChange(selectedId, today,
                        carCurrentKilometer.getText().toString(),
                        editChangingPrice.getText().toString(),
                        editDescription.getText().toString());

                Toast.makeText(AddChanges.this,"data inserted", Toast.LENGTH_LONG).show();
                carCurrentKilometer.setText("");
                editChangingPrice.setText("");
                editDescription.setText("");
            }
        });

        consumableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();

                CarConsumableObject selected = allConsumable.get(position);
                selectedId = selected.id;


                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadSpinnerData() {
        allConsumable = database.fetchAllConsumable();
        List<String> labels = new ArrayList<String>();;
        for (CarConsumableObject all: allConsumable) {
            labels.add(all.pieceName);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        consumableSpinner.setAdapter(dataAdapter);
    }
}