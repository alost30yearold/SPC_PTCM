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

    private List<Customer> mCustomers;

    public static CustomerLab get(Context context) {
        if (sCustomerLab == null) {
            sCustomerLab = new CustomerLab(context);
        }
        return sCustomerLab;
    }

    private CustomerLab(Context context) {
        mCustomers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Customer customer = new Customer();
            customer.setCustomerName("Customer #" + i);
            customer.setCustomer(i % 2 == 0); //everyotherone
            mCustomers.add(customer);

        }
    }
    public void addCustomer(Customer c){
        mCustomers.add(c);
    }

    public List<Customer> getCustomers() {
        return mCustomers;
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
}


