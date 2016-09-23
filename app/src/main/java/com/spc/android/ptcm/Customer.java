package com.spc.android.ptcm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class Customer {
    private String mCustomerName;
    private String mBillingAddress;
    private String mCustomerEmail;
    private int mCustomerWeight;
    private int mCustomerHeight;
    private ArrayList<Session> mCustomerSessions;
    private boolean isCustomer;
    private UUID mId;

    public Customer() {
        mId = UUID.randomUUID();
        mCustomerEmail = "E-Mail";
        mBillingAddress = "Billing address";
        mCustomerName = "Customer Named";

        mCustomerSessions = new ArrayList<>();
        mCustomerSessions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Session session = new Session();
            session.setSessionName("Session # " + i +" "+this.getCustomerName());
            session.setFinished(i % 2 == 0); //everyotherone
            mCustomerSessions.add(session);

        }
        isCustomer = true;
    }


    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
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

    public int getCustomerWeight() {
        return mCustomerWeight;
    }

    public void setCustomerWeight(int customerWeight) {
        mCustomerWeight = customerWeight;
    }

    public int getCustomerHeight() {
        return mCustomerHeight;
    }

    public void setCustomerHeight(int customerHeight) {
        mCustomerHeight = customerHeight;
    }
}
