package com.hirkanico.carconsumable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hirkanico.carconsumable.classes.CarConsumableObject;
import com.hirkanico.carconsumable.classes.ClickListener;
import com.hirkanico.carconsumable.library.DBManager;

public class AddConsumable extends AppCompatActivity {

    EditText addConsumableConsumeName;
    EditText addConsumableBestKilometer;
    AppCompatButton btnAddConsumable;

    RecyclerView recycleAllConsumable;

    DBManager database;

    ClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_consumable);

        addConsumableConsumeName = findViewById(R.id.addConsumableConsumeName);
        addConsumableBestKilometer = findViewById(R.id.addConsumableBestKilometer);
        btnAddConsumable = findViewById(R.id.btnAddConsumable);

        recycleAllConsumable = findViewById(R.id.recycleAllConsumable);

        database = new DBManager(AddConsumable.this);
        database.open();

        //Log.v("Number : ", "1");

        btnAddConsumable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addConsumableConsumeName.getText().toString().equals("") || addConsumableBestKilometer.getText().toString().equals("")){
                    Toast.makeText(AddConsumable.this,"both parameter must be determined", Toast.LENGTH_LONG).show();
                    return;
                }
                database.insertNewConsumable(addConsumableConsumeName.getText().toString(), addConsumableBestKilometer.getText().toString());
                Toast.makeText(AddConsumable.this,"data inserted", Toast.LENGTH_LONG).show();
                addConsumableConsumeName.setText("");
                addConsumableBestKilometer.setText("");
            }
        });

        listener = index -> {
            //Toast.makeText(MainActivity.this,"clicked item index is "+index,Toast.LENGTH_LONG).show();
           // String doneTaskId = todayTask.get(index).id;
            //fillTodayLists();
        };
        //Log.v("Number : ", "2");
        fillRecycleView();
    }

    public void fillRecycleView(){
        AllConsumableAdapter adapter = new AllConsumableAdapter(AddConsumable.this, database.fetchAllConsumable(), listener);
        recycleAllConsumable.setHasFixedSize(true);
        recycleAllConsumable.setLayoutManager(new LinearLayoutManager(AddConsumable.this));
        recycleAllConsumable.setAdapter(adapter);
        //Log.v("Number : ", "3");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillRecycleView();
    }
}