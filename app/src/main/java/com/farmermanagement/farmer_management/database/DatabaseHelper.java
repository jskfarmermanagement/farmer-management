package com.farmermanagement.farmer_management.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.farmermanagement.farmer_management.database.model.AddFarmer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Upendra on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "farmer_management";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create farmer table
        db.execSQL(AddFarmer.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + AddFarmer.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertFarmer(AddFarmer farmer) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(AddFarmer.COLUMN_FARMER_NAME, farmer.getFarmerName());
        values.put(AddFarmer.COLUMN_FATHER_NAME, farmer.getFatherName());
        values.put(AddFarmer.COLUMN_VILLAGE, farmer.getVillage());
        values.put(AddFarmer.COLUMN_MOBILE, farmer.getMobile());

        // insert row
        long id = db.insert(AddFarmer.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public AddFarmer getFarmer(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(AddFarmer.TABLE_NAME,
                new String[]{
                AddFarmer.COLUMN_ID,
                AddFarmer.COLUMN_FARMER_NAME,
                AddFarmer.COLUMN_FATHER_NAME,
                AddFarmer.COLUMN_VILLAGE,
                AddFarmer.COLUMN_MOBILE,
                AddFarmer.COLUMN_TIMESTAMP},
                AddFarmer.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        AddFarmer farmer = new AddFarmer(
                cursor.getInt(cursor.getColumnIndex(AddFarmer.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_FARMER_NAME)),
                cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_FATHER_NAME)),
                cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_VILLAGE)),
                cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_MOBILE)),
                cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return farmer;
    }

    public List<AddFarmer> getAllFarmers() {
        List<AddFarmer> farmers = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + AddFarmer.TABLE_NAME + " ORDER BY " +
                AddFarmer.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddFarmer farmer = new AddFarmer();
                farmer.setId(cursor.getInt(cursor.getColumnIndex(AddFarmer.COLUMN_ID)));
                farmer.setFarmerName(cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_FARMER_NAME)));
                farmer.setFatherName(cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_FATHER_NAME)));
                farmer.setVillage(cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_VILLAGE)));
                farmer.setMobile(cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_MOBILE)));
                farmer.setTimestamp(cursor.getString(cursor.getColumnIndex(AddFarmer.COLUMN_TIMESTAMP)));

                farmers.add(farmer);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return farmers;
    }

    public int getFarmerCount() {
        String countQuery = "SELECT  * FROM " + AddFarmer.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateFarmer(AddFarmer farmer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AddFarmer.COLUMN_FARMER_NAME, farmer.getFarmerName());
        values.put(AddFarmer.COLUMN_FATHER_NAME, farmer.getFatherName());
        values.put(AddFarmer.COLUMN_VILLAGE, farmer.getVillage());
        values.put(AddFarmer.COLUMN_MOBILE, farmer.getMobile());

        // updating row
        return db.update(AddFarmer.TABLE_NAME, values, AddFarmer.COLUMN_ID + " = ?",
                new String[]{String.valueOf(farmer.getId())});
    }

    public void deleteFarmer(AddFarmer farmer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(AddFarmer.TABLE_NAME, AddFarmer.COLUMN_ID + " = ?",
        new String[]{String.valueOf(farmer.getId())});
        db.close();
    }
}
