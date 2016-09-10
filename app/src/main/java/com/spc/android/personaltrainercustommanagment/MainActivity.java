package com.spc.android.personaltrainercustommanagment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
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
