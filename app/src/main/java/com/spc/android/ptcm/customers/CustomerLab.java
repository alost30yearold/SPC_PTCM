package com.spc.android.ptcm.customers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.spc.android.ptcm.database.PTCMBaseHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.spc.android.ptcm.database.CustomerCursorWrapper;
import com.spc.android.ptcm.database.PTCMDbSchema.CustomerTable;
import com.spc.android.ptcm.database.PTCMDbSchema.SessionTable;
import com.spc.android.ptcm.database.SessionCursorWrapper;
import com.spc.android.ptcm.sessions.Session;

/**
 * Created by Keith on 9/5/2016.
 */
public class CustomerLab {
    private static final String TAG = "CustomerLab";

    private static CustomerLab sCustomerLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    //private ArrayList<Customer> mCustomers;
    //private List<Session> mSessions;

    public static CustomerLab get(Context context) {
        if (sCustomerLab == null) {
            sCustomerLab = new CustomerLab(context);
        }
        return sCustomerLab;
    }

    private CustomerLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PTCMBaseHelper(mContext).getWritableDatabase();
    }
    public void addCustomer(Customer c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CustomerTable.TABLE_NAME, null, values);

       // mCustomers.add(c);
       // mSessions = c.getCustomerSessions();
    }

    public void addSession(Session s){
        ContentValues values = getContentValuesS(s);
        mDatabase.insert(SessionTable.TABLE_NAME, null, values);
    }

    public List<Customer> getCustomers()
    {
        List<Customer> customers = new ArrayList<>();

        CustomerCursorWrapper cursor = queryCustomers(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                customers.add(cursor.getCustomer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Log.d(TAG, "customers created-----");
        return customers;
        //return new ArrayList<>();
    }
    public List<Session> getSessions(UUID customer_id) {
        String customerId = customer_id.toString();
        Log.d(TAG, "sessions created");
        List<Session> sessions = new ArrayList<>();

        SessionCursorWrapper cursor = querySessions(SessionTable.Columns.CUSTOMER_UUID+" =?",new String[]{customer_id.toString()});

        //SessionCursorWrapper cursor = querySessionsFor(customer_id);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Log.d(TAG, "add session loop");
                sessions.add(cursor.getSession());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Log.d(TAG, "sessions created-----");
        return sessions;
    }

    public Customer getCustomer(UUID id) {
        CustomerCursorWrapper cursor = queryCustomers(CustomerTable.Columns.CUSTOMER_UUID + " = ?", new String[]{id.toString()});
        try{
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCustomer();
        } finally {
            cursor.close();
        }
        //return null;
    }
    public Session getSession(UUID id) {
        SessionCursorWrapper cursor = querySessions(SessionTable.Columns.SESSION_UUID + " = ?", new String[]{id.toString()});

        try{
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getSession();
        } finally {
            cursor.close();
        }
        //return null;
    }
    public File getPhotoFile(Customer customer){
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(externalFilesDir == null){
            return null;
        }
        return new File(externalFilesDir, customer.getPhotoFilename());

    }
    public void updateCustomer(Customer customer){
        String uuidString = customer.getCustomerId().toString();
        ContentValues values = getContentValues(customer);

        mDatabase.update(CustomerTable.TABLE_NAME, values, CustomerTable.Columns.CUSTOMER_UUID + " = ?", new String[]{uuidString});
    }
    public void updateSession(Session session){
        String uuidString = session.getCustomerUUID().toString();
        ContentValues values = getContentValuesS(session);

        mDatabase.update(SessionTable.TABLE_NAME, values, SessionTable.Columns.CUSTOMER_UUID + " = ?", new String[]{uuidString});
    }
    private static ContentValues getContentValues(Customer customer){
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Columns.CUSTOMER_UUID, customer.getCustomerId().toString());
        values.put(CustomerTable.Columns.CUSTOMER_BILLING_ADDRESS, customer.getBillingAddress());
        values.put(CustomerTable.Columns.CUSTOMER_EMAIL, customer.getCustomerEmail());
        values.put(CustomerTable.Columns.CUSTOMER_NAME, customer.getCustomerName());
        values.put(CustomerTable.Columns.CUSTOMER_SESSION_ARRAY, customer.getCustomerSessions().toString());

        return values;
    }
    private static ContentValues getContentValuesS(Session session){
        ContentValues values = new ContentValues();
        values.put(SessionTable.Columns.CUSTOMER_UUID, session.getCustomerUUID().toString());
        values.put(SessionTable.Columns.SESSION_UUID,session.getSessionUUID().toString());
        values.put(SessionTable.Columns.SESSION_NUMBER, session.getSessionNumber());
        values.put(SessionTable.Columns.SESSION_START_TIME, session.getSessionDateStart().toString());
        values.put(SessionTable.Columns.SESSION_END_TIME, session.getSessionDateEnd().toString());
        values.put(SessionTable.Columns.SESSION_IS_FINISHED, session.isFinished()? 1:0);

        return values;
    }

    private CustomerCursorWrapper queryCustomers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CustomerTable.TABLE_NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, // having
                null // orderBy
        );
        return new CustomerCursorWrapper(cursor);
    }
    private SessionCursorWrapper querySessions(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                SessionTable.TABLE_NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, // having
                null // orderBy
        );
        return new SessionCursorWrapper(cursor);
    }
    private SessionCursorWrapper querySessionsFor(UUID customerID) {
        String customer_id = customerID.toString();

        String selectQuery = "SELECT * FROM " + SessionTable.TABLE_NAME + " WHERE "+ SessionTable.Columns.CUSTOMER_UUID + " = "+ "fick";



        //Cursor cursor = mDatabase.rawQuery(selectQuery, null);
        Cursor cursor = mDatabase.query(
                SessionTable.TABLE_NAME,
                null, // Columns - null selects all columns
                null, //where
                null, // where arg
                null, //groupBy
                null, // having
                null // orderBy
        );
        return new SessionCursorWrapper(cursor);
    }

}


