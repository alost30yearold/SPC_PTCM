package com.spc.android.ptcm.customers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.spc.android.ptcm.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by Keith on 9/12/2016.
 */
public class CustomerActivity extends SingleFragmentActivity {
    private static final String TAG = "CustomerActivity";
    private static final String EXTRA_CRIME_ID = "customer_id_yeah";
    private static final String EXTRA_LOGGED_USER = "logged_user";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, CustomerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        //intent.putExtra(EXTRA_LOGGED_USER, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        //String loggedUser = (String) getIntent().getSerializableExtra(EXTRA_LOGGED_USER);
        //return new CrimeFragment();
        return CustomerFragment.newInstance(crimeId);
    }

}

