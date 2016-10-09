package com.example.android.habittrackerapp.data;

/**
 * Created by ABHISHEK RAJ on 10/9/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittrackerapp.data.CodeContract.CodeEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class CodeDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CodeDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "habbits.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link CodeDbHelper}.
     *
     * @param context of the app
     */
    public CodeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + CodeEntry.TABLE_NAME + " ("
                + CodeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CodeEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + CodeEntry.COLUMN_BREED + " TEXT, "
                + CodeEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + CodeEntry.COLUMN_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}