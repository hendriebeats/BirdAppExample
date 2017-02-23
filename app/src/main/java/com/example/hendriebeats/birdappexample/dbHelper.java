package com.example.hendriebeats.birdappexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_NAME = "BIRD_DB";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String TABLE_BIRD = "birdtable";





    public dbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_BIRD + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIRD);

        // Create tables again
        onCreate(db);
    }

    // Adding new Bird
    void addBird(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bird.getName()); // Bird Name
        values.put(KEY_DESCRIPTION, bird.getDescription()); // Bird Description

        // Inserting Row
        db.insert(TABLE_BIRD, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Birds
    public List<Bird> getAllBirds() {
        List<Bird> contactList = new ArrayList<Bird>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BIRD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bird bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setDescription(cursor.getString(2));
                // Adding contact to list
                contactList.add(bird);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting single Bird
    Bird getBird(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BIRD, new String[] { KEY_ID,
                        KEY_NAME, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Bird contact = new Bird(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // Updating single contact
    public int updateContact(Bird contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_DESCRIPTION, contact.getDescription());

        // updating row
        return db.update(TABLE_BIRD, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single bird
    public void deleteBird(Bird bird) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BIRD, KEY_ID + " = ?",
                new String[] { String.valueOf(bird.getID()) });
        db.close();
    }


    // Getting contacts Count
    public int getbirdsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BIRD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
