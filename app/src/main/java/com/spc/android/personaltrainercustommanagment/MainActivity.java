package com.spc.android.personaltrainercustommanagment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mLoginButton;


    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

    }
    public void onClick(View view) {
        Log.d(TAG, "onClick() called");
        setContentView(R.layout.activity_fragment);

        if (findViewById(R.id.fragment_container) != null) {
            Log.d(TAG, "frag cont != null ");
            if (savedInstanceState != null) {
                Log.d(TAG, "saved I S != null ");
                return;
            }
            LoginFragment loginFragment = new LoginFragment();

            loginFragment.setArguments(getIntent().getExtras());

            FragmentManager fm = getSupportFragmentManager();
            // Fragment fragment = fm.findFragmentById(R.id.login_frag);

            fm.beginTransaction().add(R.id.fragment_container, loginFragment).commit();

        }

    }
}
