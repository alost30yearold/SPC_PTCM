package com.spc.android.ptcm.customers;

import com.spc.android.ptcm.sessions.Session;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class Customer {
    private String mCustomerName;
    private String mBillingAddress;
    private String mCustomerEmail;
    private ArrayList<Session> mCustomerSessions;
    private UUID mCustomerId;

    public Customer() {
        this.mCustomerId = UUID.randomUUID();
        this.mCustomerEmail = "E-Mail";
        this.mBillingAddress = "Billing address";
        this.mCustomerName = "Customer Named";
        this.mCustomerSessions = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Session session = new Session(mCustomerId);
//            session.setSessionNumber(i);
//            session.setFinished(i % 2 == 0); //everyotherone
//            mCustomerSessions.add(session);
//
//        }
    }
    public Customer(UUID customerId){
        this.mCustomerId = customerId;
        this.mCustomerEmail = "E-Mail";
        this.mBillingAddress = "Billing address";
        this.mCustomerName = "Customer Named";
        this.mCustomerSessions = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            Session session = new Session(mCustomerId);
//            session.setSessionNumber(i);
//            session.setFinished(i % 2 == 0); //everyotherone
//            mCustomerSessions.add(session);
//
//        }

    }


    public UUID getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(UUID customerId) {
        mCustomerId = customerId;
    }

    public void setCustomer(boolean customer) {
    }

    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
    }

    public String getBillingAddress() {
        return mBillingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        mBillingAddress = billingAddress;
    }

    public String getCustomerEmail() {
        return mCustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        mCustomerEmail = customerEmail;
    }

    public ArrayList<Session> getCustomerSessions() {
        return mCustomerSessions;
    }

    public void setCustomerSessions(ArrayList<Session> customerSessions) {
        mCustomerSessions = customerSessions;
    }

    //this is very broken !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void setCustomerSessions(String what) {
        mCustomerSessions = new ArrayList<>();
    }

    public String getPhotoFilename(){
        return "IMG_"+getCustomerId().toString()+ ".jpg";
    }
}
