package com.spc.android.ptcm;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class CustomerLab {
    private static final String TAG = "CustomerLab";

    private static CustomerLab sCustomerLab;

    private ArrayList<Customer> mCustomers;
    private List<Session> mSessions;

    public static CustomerLab get(Context context) {
        if (sCustomerLab == null) {
            sCustomerLab = new CustomerLab(context);
        }
        return sCustomerLab;
    }

    private CustomerLab(Context context) {
        mCustomers = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            Customer customer = new Customer();
//            customer.setCustomerName("Customer #" + i);
//            customer.setCustomer(i % 2 == 0); //everyotherone
//
//            mSessions = customer.getCustomerSessions();
//            mCustomers.add(customer);
//
//        }
       //mSessions = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Session session = new Session();
//            session.setSessionName("Session #" + i);
//            session.setFinished(i % 2 == 0); //everyotherone
//            mSessions.add(session);
//
//        }
    }
    public void addCustomer(Customer c){
        mCustomers.add(c);
       // mSessions = c.getCustomerSessions();
    }
    public void addSession(Session s){
        mSessions.add(s);
    }

    public List<Customer> getCustomers() {
        return mCustomers;
    }
    public List<Session> getSessions() {
        return mSessions;
    }

    public Customer getCustomer(UUID id) {
        for (Customer customer : mCustomers) {
            if (customer.getId().equals(id)) {
                Log.d(TAG, "customer created");
                return customer;
            }
        }
        return null;
    }
    public Session getSession(UUID id) {
        for (Session session : mSessions) {
            if (session.getId().equals(id)) {
                Log.d(TAG, "session created");
                return session;
            }
        }
        return null;
    }
}


