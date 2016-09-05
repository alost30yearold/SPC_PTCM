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

    private Button mLogin;

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);

       // View view = inflater.inflate(R.layout.fragment_crime_list, container, false);


       // FragmentManager fm = getSupportFragmentManager();
       // Fragment fragment = fm.findFragmentById(R.id.fragment_container);

//        if(fragment == null){
//            fragment = new blankest();//createFragment();
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }

        /*
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //LoginActivity fuck = new LoginActivity();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }*/
       // });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
