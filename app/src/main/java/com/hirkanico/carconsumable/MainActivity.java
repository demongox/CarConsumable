package com.hirkanico.carconsumable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hirkanico.carconsumable.classes.ChangingConsumableObject;
import com.hirkanico.carconsumable.classes.ClickListener;
import com.hirkanico.carconsumable.library.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView btnReport;
    ImageView btnChange;
    ImageView btnConsumable;

    RecyclerView recycleShowChanging;

    ClickListener listener;

    DBManager database;

    ArrayList<ChangingConsumableObject> allChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReport = findViewById(R.id.btnReport);
        btnChange = findViewById(R.id.btnChange);
        btnConsumable = findViewById(R.id.btnConsumable);

        recycleShowChanging = findViewById(R.id.recycleShowChanging);

        btnConsumable.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddConsumable.class);
            startActivity(intent);
        });

        btnChange.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddChanges.class);
            startActivity(intent);
        });

        btnReport.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Reports.class);
            startActivity(intent);
        });

        listener = index -> {
            //Toast.makeText(MainActivity.this,"clicked item index is "+index,Toast.LENGTH_LONG).show();
            // String doneTaskId = todayTask.get(index).id;
            //fillTodayLists();
        };

        database = new DBManager(MainActivity.this);
        database.open();

        fillRecycleViewLists();
    }

    public void fillRecycleViewLists(){
        allChanges = database.fetchAllConsumableHistoryForChange();
        ChangingConsumableAdapter adapter = new ChangingConsumableAdapter(MainActivity.this, allChanges , listener);
        recycleShowChanging.setHasFixedSize(true);
        recycleShowChanging.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recycleShowChanging.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillRecycleViewLists();
    }
}