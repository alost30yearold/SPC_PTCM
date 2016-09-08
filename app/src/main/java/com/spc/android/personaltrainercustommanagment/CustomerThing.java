//package com.spc.android.personaltrainercustommanagment;
//
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * Created by Keith on 9/5/2016.
// */
//public class CustomerThing {
//    private static CustomerThing sCustomerThing;
//
//    private List<Customer> mCustomers;
//
//    public static CustomerThing get(Context context) {
//        if (sCustomerThing == null) {
//            sCustomerThing = new CustomerThing(context);
//        }
//        return sCustomerThing;
//    }
//
//    private CustomerThing(Context context) {
//        mCustomers = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            Customer customer = new Customer();
//            customer.setCustomerName("Crime #" + i);
//            customer.setCustomer(i % 2 == 0); //everyotherone
//            mCustomers.add(customer);
//
//        }
//    }
//
//    public List<Customer> getCustomers() {
//        return mCustomers;
//    }
//
//    public Customer getCustomer(UUID id) {
//        for (Customer customer : mCustomers) {
//            if (customer.getId().equals(id)) {
//                return customer;
//            }
//        }
//        return null;
//    }
//}
//
//
