package com.hirkanico.carconsumable.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String CAR_CONSUMABLE_TABLE_NAME = "car_consumable";
    public static final String CHANGING_HISTORY_TABLE_NAME = "changing_history";

    // Table columns
    public static final String ID = "id";
    public static final String CONSUMABLE_NAME = "consumable_name";
    public static final String BEST_KILOMETER_TO_CHANGE = "best_kilometer_to_change";

    public static final String CONSUMABLE_ID = "consumable_id";
    public static final String CHANGE_DATE = "change_date";
    public static final String CURRENT_KILOMETER = "current_kilometer";
    public static final String CHANGING_PRICE = "change_price";
    public static final String DESCRIPTION = "description";

    // Database Information
    static final String DB_NAME = "CAR_CONSUMABLE.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_CONSUMABLE_TABLE = "create table if not exists " + CAR_CONSUMABLE_TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONSUMABLE_NAME + " TEXT NOT NULL, "
            + BEST_KILOMETER_TO_CHANGE + " TEXT NOT NULL);";

    private static final String CREATE_CHANGING_TABLE = "create table if not exists " + CHANGING_HISTORY_TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONSUMABLE_ID + " TEXT NOT NULL, "
            + CHANGE_DATE + " TEXT NOT NULL, " + CURRENT_KILOMETER + " TEXT NOT NULL, "
            + CHANGING_PRICE + " TEXT NOT NULL, "+ DESCRIPTION + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONSUMABLE_TABLE);
        db.execSQL(CREATE_CHANGING_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_ALL_PLAN_TABLE);
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_DAILY_PLANNER_TABLE);
        onCreate(db);
    }

}
