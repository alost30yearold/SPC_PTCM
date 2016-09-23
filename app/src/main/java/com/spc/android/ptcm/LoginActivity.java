package com.spc.android.ptcm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.UUID;

/**
 * Created by Keith on 9/11/2016.
 */
public class LoginActivity extends SingleFragmentActivity {
    private static final String TAG = "LoginActivity";
    // public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
    private static final String EXTRA_LIST_ID = "com.bignerdranch.android.criminalintent.list_id";
    private static final String EXTRA_LOGIN_ID = "user_id_thingy";
    private static final String EXTRA_LOGIN_PASS = "user_pass_thingy";
    public String loggedInUser;

    public static Intent newIntent(Context packageContext, UUID listID){
        Intent intent = new Intent(packageContext, CustomerActivity.class);
        intent.putExtra(EXTRA_LIST_ID, listID);
        return intent;
    }

    @Override
    protected Fragment createFragment(){
        Log.d(TAG, "createFragmentCalled");
        String userName = (String) getIntent().getSerializableExtra(EXTRA_LOGIN_ID);
        String userPassWord = (String) getIntent().getSerializableExtra(EXTRA_LOGIN_PASS);
        UUID listID = (UUID) getIntent().getSerializableExtra(EXTRA_LIST_ID);
        //return new CrimeFragment();
        return LoginFragment.newInstance(userName,userPassWord, listID);
    }



}
