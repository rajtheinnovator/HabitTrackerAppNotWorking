package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.CodeContract.CodeEntry;
import com.example.android.habittrackerapp.data.CodeDbHelper;

public class CatalogActivity extends AppCompatActivity {
    private CodeDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

//        // Setup FAB to open EditorActivity
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
//                startActivity(intent);
//            }
//        });

        displayDatabaseInfo();
        mDbHelper = new CodeDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        CodeDbHelper mDbHelper = new CodeDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Use query method on Database object making use of projection, selection and selection argument
        String[] projection = {CodeEntry._ID, CodeEntry.COLUMN_NAME,
                CodeEntry.COLUMN_BREED, CodeEntry.COLUMN_GENDER, CodeEntry.COLUMN_WEIGHT};
        Cursor cursor = db.query(CodeEntry.TABLE_NAME, projection,
                null, null,
                null, null, null);
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            displayView.setText("Pets database contains: " + cursor.getCount() + "pets \n\n");
            displayView.append(CodeEntry._ID + "-" + CodeEntry.COLUMN_NAME + "-" + CodeEntry.COLUMN_BREED + "-" +
                    CodeEntry.COLUMN_GENDER + "-" + CodeEntry.COLUMN_WEIGHT + "\n");
            int idColumnIndex = cursor.getColumnIndex(CodeEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(CodeEntry.COLUMN_NAME);
            int breedColumnIndex = cursor.getColumnIndex(CodeEntry.COLUMN_BREED);
            int genderColumnIndex = cursor.getColumnIndex(CodeEntry.COLUMN_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(CodeEntry.COLUMN_WEIGHT);
            while (cursor.moveToNext()){
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                String currentGender = String.valueOf(cursor.getInt(genderColumnIndex));
                String currentWeight = String.valueOf(cursor.getInt(weightColumnIndex));
                displayView.append("\n" + currentId + "-" + currentName + "-" + currentBreed + "-" +
                        currentGender + "-" + currentWeight);
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
            db.close();
        }
    }

    private void insertPet() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CodeEntry.COLUMN_NAME, "Toto");
        values.put(CodeEntry.COLUMN_BREED, "Terrier");
        values.put(CodeEntry.COLUMN_GENDER, CodeEntry.GENDER_MALE);
        values.put(CodeEntry.COLUMN_WEIGHT, "7Kg");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CodeEntry.TABLE_NAME, null, values);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
