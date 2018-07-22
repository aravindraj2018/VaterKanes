package com.example.aravindraj.vaterkanes.DataBaseHelperClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aravindraj.vaterkanes.model.NewCustomerListData;

import java.util.ArrayList;

public class DataBaseHelperClass extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "MasterDatabase_db";

    // Tables names
    private static final String TABLE_NEW_CUSTOMER = "table_new_customer";


    // Common column names
    private static final String KEY_ID = "id";

    // column names for new customer table
    private static final String NEW_CUSTOMER_NAME = "new_customer_name";
    private static final String NEW_CUSTOMER_PHONE_NO = "new_customer_phone_no";
    private static final String NEW_CUSTOMER_ADDRESS = "new_customer_address";

    // Query for new customer
    private static final String CREATE_NEW_CUSTOMER_TABLE =  "CREATE TABLE "
            + TABLE_NEW_CUSTOMER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + NEW_CUSTOMER_NAME
            + " TEXT," + NEW_CUSTOMER_PHONE_NO + " INTEGER," + NEW_CUSTOMER_ADDRESS
            + " TEXT"+")";

    // query for update customer table // query is pending
    private static final String UPDATE_CUSTOMER_TABLE = "UPDATE TABLE_NEW_CUSTOMER ";

    // sqlite database reference
    SQLiteDatabase db;

    public DataBaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table for new customer
        db.execSQL(CREATE_NEW_CUSTOMER_TABLE);

    }

    public void closeDatabase() {
        if (db != null) {
            db.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEW_CUSTOMER);

        // Create tables again
        onCreate(db);

    }

    // insertion of new record in database in new customer table
    public void insertNewCustomer(String customerName,String  phoneNumber,String address){

        // get writable database as we want to write data
         db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NEW_CUSTOMER_NAME, customerName);
        values.put(NEW_CUSTOMER_PHONE_NO,phoneNumber);
        values.put(NEW_CUSTOMER_ADDRESS,address);

        // insert row
         db.insert(TABLE_NEW_CUSTOMER, null, values);
        // close db connection
        db.close();
        closeDatabase();
    }

    // reading data from new customer table
    public ArrayList<NewCustomerListData> getNewCustomerListData(){
        NewCustomerListData newCustomerListData = null;
        ArrayList<NewCustomerListData> newCustomerListData1 = new ArrayList<>();
        // get readable database as we are not inserting anything
         db = this.getReadableDatabase();

        // query for the new customer data
        String new_customer_query ="SELECT "+KEY_ID+","+NEW_CUSTOMER_NAME+","+NEW_CUSTOMER_PHONE_NO+"," +
                ""+NEW_CUSTOMER_ADDRESS+" FROM "+TABLE_NEW_CUSTOMER+"";

        // getting as list from the model class

        Cursor cursor = db.rawQuery(new_customer_query,null);
        int counter = cursor.getCount();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            newCustomerListData1.add(new NewCustomerListData(cursor.getInt(0),
                    cursor.getString(1),cursor.getString(2),
                    cursor.getString(3)));
            cursor.moveToNext();
        }
        // close the db connection
        cursor.close();
        closeDatabase();
        return newCustomerListData1;
    }

    public void UpdateDatabase(ArrayList<NewCustomerListData> newCustomerListData){
        // getting total arraylist from the exsisting customer adapter

        newCustomerListData = new ArrayList<>();
        String update_Customer_name;
        String update_Customer_phoneNumber;
        String update_Customer_address;


        for(int i=0; i<newCustomerListData.size(); i++){
             update_Customer_name = newCustomerListData.get(i).getCustomer_name();
            update_Customer_phoneNumber = newCustomerListData.get(i).getCustomer_phoneno();
            update_Customer_address = newCustomerListData.get(i).getCustomer_phoneno();
            // instance of the database
            db = this.getWritableDatabase();
            // setting values for the updated arraylist
            ContentValues values = new ContentValues();
            values.put(NEW_CUSTOMER_NAME, update_Customer_name);
            values.put(NEW_CUSTOMER_PHONE_NO,update_Customer_phoneNumber);
            values.put(NEW_CUSTOMER_ADDRESS,update_Customer_address);

            // update the lastest customer arraylist in the database
        }
    }

    public void deleteSpecificIld(int selected_position_table_id){
        // query for delete the specific record for existing customer
        db = this.getWritableDatabase();
        db.delete("table_new_customer",  "ID =?", new String[]{String.valueOf(selected_position_table_id)});
        closeDatabase();
    }
}
