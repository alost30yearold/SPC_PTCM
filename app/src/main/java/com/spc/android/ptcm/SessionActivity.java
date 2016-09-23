package com.spc.android.ptcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Keith on 9/12/2016.
 */
public class SessionActivity extends SingleFragmentActivity{
    private static final String TAG = "SessionActivity";
    // public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private static final String EXTRA_SESSION_ID = "session_id";

    public static Intent newIntent(Context packageContext, UUID sessionId){
        Intent intent = new Intent(packageContext, SessionActivity.class);
        intent.putExtra(EXTRA_SESSION_ID, sessionId);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragment Called s");
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_SESSION_ID);
        //return new CrimeFragment();
        return SessionFragment.newInstance(crimeId);
    }

}

