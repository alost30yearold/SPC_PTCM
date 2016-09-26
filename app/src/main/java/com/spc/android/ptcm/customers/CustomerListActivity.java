package com.spc.android.ptcm.customers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.spc.android.ptcm.SingleFragmentActivity;

/**
 * Created by Keith on 9/12/2016.
 */
public class CustomerListActivity extends SingleFragmentActivity {
    private static final String TAG = "CustomerListActivity";
    private static final String EXTRA_CRIME_ID = "customer_id_yeah";
    private static final String EXTRA_LOGGED_USER = "logged_user";

    public static Intent newIntent(Context packageContext, String logged_user){
        Intent intent = new Intent(packageContext, CustomerListActivity.class);
        intent.putExtra(EXTRA_LOGGED_USER, logged_user);

        return intent;
    }


    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        String logger_user = (String) getIntent().getSerializableExtra(EXTRA_LOGGED_USER);
        //String loggedUser = (String) getIntent().getSerializableExtra(EXTRA_LOGGED_USER);
        //return new CrimeFragment();
        return CustomerListFragment.newInstance(logger_user);
        //return new CustomerListFragment();
    }



}
