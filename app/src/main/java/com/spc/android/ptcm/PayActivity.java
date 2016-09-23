package com.spc.android.ptcm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.UUID;

public class PayActivity extends SingleFragmentActivity {

    private static final String TAG = "CustomerActivity";
    private static final String EXTRA_CRIME_ID = "customer_id_yeah";
    private static final String EXTRA_LOGGED_USER = "logged_user";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, PayActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);

        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return PayFragment.newInstance(crimeId, "poop");
    }
}
