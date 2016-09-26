package com.spc.android.ptcm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.spc.android.ptcm.database.PTCMDbSchema.CustomerTable;
import com.spc.android.ptcm.database.PTCMDbSchema.SessionTable;

/**
 * Created by Keith on 9/25/2016.
 */
public class PTCMBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ptcm.db";

    private static final String CREATE_CUSTOMER_TABLE = "create table " + CustomerTable.TABLE_NAME + "(" + "_id integer primary key autoincrement, " +
            CustomerTable.Columns.CUSTOMER_UUID + ", " +
            CustomerTable.Columns.CUSTOMER_NAME + ", " +
            CustomerTable.Columns.CUSTOMER_EMAIL + ", " +
            CustomerTable.Columns.CUSTOMER_BILLING_ADDRESS + ", " +
            CustomerTable.Columns.CUSTOMER_SESSION_ARRAY +  ")";

    private static final String CREATE_SESSION_TABLE = "create table " + SessionTable.TABLE_NAME + "(" + "_id integer primary key autoincrement, " +
            SessionTable.Columns.CUSTOMER_UUID + ", " +
            SessionTable.Columns.SESSION_UUID + ", " +
            SessionTable.Columns.SESSION_NUMBER + ", " +
            SessionTable.Columns.SESSION_START_TIME + ", " +
            SessionTable.Columns.SESSION_END_TIME + ", " +
            SessionTable.Columns.SESSION_IS_FINISHED +  ")";

    public static final String CREATE_USER_TABLE = "todo";


    public PTCMBaseHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_SESSION_TABLE);
        //db.execSQL(CREATE_USER_TABLE);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
