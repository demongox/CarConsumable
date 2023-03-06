package com.hirkanico.carconsumable.library;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hirkanico.carconsumable.classes.CarConsumableObject;
import com.hirkanico.carconsumable.classes.ChangingConsumableObject;

import java.util.ArrayList;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertNewConsumable(String consumableName, String bestKilometerToChange) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.CONSUMABLE_NAME, consumableName);
        contentValue.put(DatabaseHelper.BEST_KILOMETER_TO_CHANGE, bestKilometerToChange);

        database.insert(DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME, null, contentValue);
    }

    public void insertNewChange(String consumableId, String changeDate, String currentKilometer, String changingPrice, String description) {
        ContentValues contentValue = new ContentValues();

        contentValue.put(DatabaseHelper.CONSUMABLE_ID, consumableId);
        contentValue.put(DatabaseHelper.CHANGE_DATE, changeDate);
        contentValue.put(DatabaseHelper.CURRENT_KILOMETER, currentKilometer);
        contentValue.put(DatabaseHelper.DESCRIPTION, description);
        contentValue.put(DatabaseHelper.CHANGING_PRICE, changingPrice);

        database.insert(DatabaseHelper.CHANGING_HISTORY_TABLE_NAME, null, contentValue);
    }

    @SuppressLint("Range")
    public ArrayList<CarConsumableObject> fetchAllConsumable() {
        String MY_QUERY = "SELECT * FROM " + DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME;

        Cursor cursor = database.rawQuery(MY_QUERY,new String[]{});

        ArrayList<CarConsumableObject> allDailyTask = new ArrayList<>();

        while(cursor.moveToNext()){
            //Log.v("Id" , cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID)));
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
            @SuppressLint("Range") String consumable_name =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_NAME));
            @SuppressLint("Range") String kilometer =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.BEST_KILOMETER_TO_CHANGE));

            allDailyTask.add(new CarConsumableObject(id, consumable_name, kilometer));
        }

        return allDailyTask;
    }

    @SuppressLint("Range")
    public ArrayList<ChangingConsumableObject> fetchAllConsumableHistoryForChange() {

        String MY_QUERY = "SELECT cht.*, b.* FROM " + DatabaseHelper.CHANGING_HISTORY_TABLE_NAME +
                " cht Left JOIN " + DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME +
                " b ON cht.consumable_id = b.id " +
                " GROUP BY cht.consumable_id ORDER BY cht.id DESC";

        Cursor cursor = database.rawQuery(MY_QUERY,new String[]{});

        ArrayList<ChangingConsumableObject> allDailyTask = new ArrayList<>();

        while(cursor.moveToNext()){
            //Log.v("Id" , cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_NAME)));
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
            @SuppressLint("Range") String consumable_name =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_NAME));
            @SuppressLint("Range") String consumable_id =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_ID));
            @SuppressLint("Range") String changing_date =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHANGE_DATE));
            @SuppressLint("Range") String previous_kilometer_to_change =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CURRENT_KILOMETER));
            @SuppressLint("Range") String best_kilometer_to_change =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.BEST_KILOMETER_TO_CHANGE));
            int kilometerToChange = Integer.parseInt(previous_kilometer_to_change) + Integer.parseInt(best_kilometer_to_change);
            @SuppressLint("Range") String changingPrice =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHANGING_PRICE));
            @SuppressLint("Range") String description =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION));
            //@SuppressLint("Range") String isDone =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.PLAN_IS_DONE));

            allDailyTask.add(new ChangingConsumableObject(id, consumable_id, consumable_name,
                    changing_date, previous_kilometer_to_change, String.valueOf(kilometerToChange),
                    changingPrice,  description));
        }

        return allDailyTask;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllTodayInsertedTasks(String today){

        ArrayList<String> allInsertedId = new ArrayList<>();

        String MY_QUERY = "SELECT * FROM " + DatabaseHelper.CHANGING_HISTORY_TABLE_NAME +
                " a Left JOIN " + DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME +
                " b ON a.plan_id=b.id WHERE  a." + DatabaseHelper.CHANGE_DATE + " = '"+today+"'";

        Cursor cursor = database.rawQuery(MY_QUERY,new String[]{});

        while (cursor.moveToNext()) {
            allInsertedId.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_ID)));
        }

        //database.close();

        return allInsertedId;
    }

    public ChangingConsumableObject getSingleTask(String _id){
        String MY_QUERY = "SELECT * FROM " + DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME +
                " WHERE " + DatabaseHelper.ID + " = '" + _id + "'";

        ChangingConsumableObject allDailyTask = null;
        try (Cursor cursor = database.rawQuery(MY_QUERY, new String[]{})) {
            while (cursor.moveToNext()) {
                //Log.v("Id" , cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID)));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.ID));
                @SuppressLint("Range") String consumable_name =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_NAME));
                @SuppressLint("Range") String consumable_id =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONSUMABLE_ID));
                @SuppressLint("Range") String changing_date =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHANGE_DATE));
                @SuppressLint("Range") String previous_kilometer_to_change =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CURRENT_KILOMETER));
                @SuppressLint("Range") String best_kilometer_to_change =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.BEST_KILOMETER_TO_CHANGE));
                int kilometerToChange = Integer.parseInt(previous_kilometer_to_change) + Integer.parseInt(best_kilometer_to_change);
                @SuppressLint("Range") String changingPrice =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.CHANGING_PRICE));
                @SuppressLint("Range") String description =  cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION));

                allDailyTask = new ChangingConsumableObject(id, consumable_name, consumable_id,
                        changing_date, previous_kilometer_to_change, String.valueOf(kilometerToChange),
                        changingPrice,  description);
            }
        }

        //database.close();

        return allDailyTask;
    }

    public int updateAllPlane(String _id, String title, String date, String time, String repeat, String priority) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.CONSUMABLE_NAME, title);
        contentValues.put(DatabaseHelper.BEST_KILOMETER_TO_CHANGE, date);
        //contentValues.put(DatabaseHelper.NEW_PLAN_DOING_TIME, time);
        //contentValues.put(DatabaseHelper.PLAN_REPEAT_DAY, repeat);
        contentValues.put(DatabaseHelper.CHANGING_PRICE, priority);

        int i = database.update(DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME, contentValues, DatabaseHelper.ID + " = " + _id, null);
        return i;
    }

    public void deleteFromAllPlaneTable(String _id) {
        database.delete(DatabaseHelper.CAR_CONSUMABLE_TABLE_NAME, DatabaseHelper.ID + "=" + _id, null);
    }

    public void deleteDailyPlaneWithAllPlanId(String _id) {
        database.delete(DatabaseHelper.CHANGING_HISTORY_TABLE_NAME, DatabaseHelper.CONSUMABLE_ID + "='" + _id +"'", null);
    }

}