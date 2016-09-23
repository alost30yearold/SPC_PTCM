package com.spc.android.ptcm;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String LOGIN_DATA_KEY = "loginDataKey";

    LoginFragment loginFragment = new LoginFragment();

    private User mUser;

//    public static class LoginData implements Serializable{
//
//        public String userName;
//        public int userId;
//        public List userList;
//
//
//        public LoginData(){
//
//        }
//
//        public LoginData(String userName, int userId , List userList){
//            this.userName = userName;
//            this.userId = userId;
//            this.userList = userList;
//
//        }
//    }



   // private Button mLoginButton;


    Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onsavedStared");
        savedInstanceState.putSerializable(LOGIN_DATA_KEY, mUser);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){

        int messageResId=0;

        switch (menu.getItemId()){
            case R.id.login_menu_item:
                Log.d(TAG, "login selected");
                setContentView(R.layout.activity_fragment);
                if (findViewById(R.id.fragment_container) != null) {
                    Log.d(TAG, "frag cont != null ");
                    if (savedInstanceState != null) {
                        Log.d(TAG, "saved I S != null ");

                       // return;
                    }
                    //LoginFragment loginFragment = new LoginFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction().add(R.id.fragment_container, loginFragment).commit();

                }
                //login();
                Log.d(TAG, "login frag after");
                return true;
            case R.id.logout_menu_item:
                Log.d(TAG, "logout selected");
                messageResId = R.string.logout;
                Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "testing test");// + loginFragment.getArguments());
                mUser = new User();
                setContentView(R.layout.activity_main);
                //logout();
                return true;
            default:
                return super.onOptionsItemSelected(menu);

        }

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
            String userName = "Keith";
            int userID = 32;


          //  Map<Integer,String> properties  = new HashMap<>();
          //  properties.put(5, userName);
            //properties.get(5); // returns userName
            //same shit as Bundle

            Bundle myBundle = new Bundle();

           // myBundle.putSerializable(LOGIN_DATA_KEY, new LoginData(userName, userID, null));

           // myBundle.putString("user",userName);
          //  myBundle.putInt("id", userID);

            LoginFragment loginFragment = new LoginFragment();

            loginFragment.setArguments(myBundle);

            //loginFragment.setArguments(getIntent().getExtras());

            FragmentManager fm = getSupportFragmentManager();
            // Fragment fragment = fm.findFragmentById(R.id.login_frag);

            fm.beginTransaction().add(R.id.fragment_container, loginFragment).commit();

        }

    }
}
