package com.spc.android.ptcm.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.spc.android.ptcm.customers.Customer;

import com.spc.android.ptcm.database.PTCMDbSchema.CustomerTable;

import java.util.UUID;

/**
 * Created by Keith on 9/25/2016.
 */
public class CustomerCursorWrapper extends CursorWrapper {
    public CustomerCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Customer getCustomer(){
        String uuidString = getString(getColumnIndex(CustomerTable.Columns.CUSTOMER_UUID));
        String name = getString(getColumnIndex(CustomerTable.Columns.CUSTOMER_NAME));
        String email = getString(getColumnIndex(CustomerTable.Columns.CUSTOMER_EMAIL));
        String billingAddress = getString(getColumnIndex(CustomerTable.Columns.CUSTOMER_BILLING_ADDRESS));
       // ArrayList<Session> sessions = //getArrayList(getColumnIndex(CustomerTable.Columns.CUSTOMER_SESSION_ARRAY));
        String sessions = getString(getColumnIndex(CustomerTable.Columns.CUSTOMER_SESSION_ARRAY));

        Customer customer = new Customer(UUID.fromString(uuidString));
        customer.setCustomerName(name);
        customer.setCustomerEmail(email);
        customer.setBillingAddress(billingAddress);
        ///VERY BROKEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        customer.setCustomerSessions(sessions);

        return customer;
        //return null;
    }
//    public Session getSession(){
//        String customerUUID = getString(getColumnIndex(SessionTable.Columns.CUSTOMER_UUID));
//        String sessionUUID = getString(getColumnIndex(SessionTable.Columns.SESSION_UUID));
//        int number = getInt(getColumnIndex(SessionTable.Columns.SESSION_NUMBER));
//        String startTime = getString(getColumnIndex(SessionTable.Columns.SESSION_START_TIME));
//        String endTime = getString(getColumnIndex(SessionTable.Columns.SESSION_END_TIME));
//        int isFinished = getInt(getColumnIndex(SessionTable.Columns.SESSION_IS_FINISHED));
//
//        Session session = new Session(UUID.fromString(customerUUID));
//        session.setSessionUUID(UUID.fromString(sessionUUID));
//        session.setSessionNumber(number);
//        session.setSessionTimeStart(startTime);
//        session.setSessionTimeEnd(endTime);
//        session.setFinished(isFinished != 0);
//
//        return session;
//
//    }
}
