package com.spc.android.ptcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Keith on 9/12/2016.
 */
public class SessionListActivity extends SingleFragmentActivity {
    private static final String TAG = "SessionListActivity";
    private static final String EXTRA_CUSTOMER_ID = "customer_id";
    private static final String EXTRA_CUSTOMER_LIST = "customer_list";


    public static Intent newIntent(Context packageContext, UUID customer_id, ArrayList<Session> customer_list){

        Intent intent = new Intent(packageContext, SessionListActivity.class);
        intent.putExtra(EXTRA_CUSTOMER_LIST, customer_list);
        intent.putExtra(EXTRA_CUSTOMER_ID, customer_id);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        ArrayList<Session> customer_list = (ArrayList<Session>) getIntent().getSerializableExtra(EXTRA_CUSTOMER_LIST);
        UUID customer_id = (UUID) getIntent().getSerializableExtra(EXTRA_CUSTOMER_ID);
        //String loggedUser = (String) getIntent().getSerializableExtra(EXTRA_LOGGED_USER);
        //return new CrimeFragment();
        return SessionListFragment.newInstance(customer_id, customer_list);
        //return new CustomerListFragment();
    }


}
