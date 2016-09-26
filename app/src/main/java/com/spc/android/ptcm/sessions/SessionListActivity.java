package com.spc.android.ptcm.sessions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.spc.android.ptcm.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by Keith on 9/12/2016.
 */
public class SessionListActivity extends SingleFragmentActivity {
    private static final String TAG = "SessionListActivity";
    private static final String EXTRA_CUSTOMER_ID = "customer_id";
    private static final String EXTRA_CUSTOMER_NAME = "customer_name";


    public static Intent newIntent(Context packageContext, UUID customer_id, String customerName){

        Intent intent = new Intent(packageContext, SessionListActivity.class);
        intent.putExtra(EXTRA_CUSTOMER_NAME, customerName);
        intent.putExtra(EXTRA_CUSTOMER_ID, customer_id);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        //ArrayList<Session> customer_list = (ArrayList<Session>) getIntent().getSerializableExtra(EXTRA_CUSTOMER_LIST);
        UUID customer_id = (UUID) getIntent().getSerializableExtra(EXTRA_CUSTOMER_ID);
        String customer_name = (String) getIntent().getSerializableExtra(EXTRA_CUSTOMER_NAME);
        //return new CrimeFragment();
        return SessionListFragment.newInstance(customer_id,customer_name);
        //return new CustomerListFragment();
    }


}
