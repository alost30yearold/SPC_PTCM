package com.spc.android.personaltrainercustommanagment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private static final String LOGIN_DATA_KEY = "loginDataKey";

//    OnLoginSelectedListener mListener;
//    private String mUserloggedIn;
//    private TextView mLoggedInUser;
    private User mUser = new User();

    private Bundle mBundle = new Bundle();

    private EditText mUserField;
    private EditText mPasswordField;
    private Button mLoginButton;

    private int messageResId = 0;

    public static LoginFragment newInstance(UUID userId){
        Bundle args = new Bundle();
        args.putSerializable(LOGIN_DATA_KEY, userId);

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;

    }

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);


     //  mUserloggedIn = getString(R.string.user_name);
       Log.d(TAG, "onCreate called lihfg, ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState){
       // MainActivity.LoginData loginData = (MainActivity.LoginData) getArguments().getSerializable(MainActivity.LOGIN_DATA_KEY);
        Log.d(TAG, "onCreateView called, ");//+ loginData.userName);
        View v = inflater.inflate(R.layout.my_login, container, false);

        mUserField = (EditText) v.findViewById(R.id.user_text);
        //mUserField.setText(R.string.user_name);
        mUserField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setUserName(s.toString());
                //mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPasswordField = (EditText) v.findViewById(R.id.password_text);
        //mPasswordField.setText(R.string.password);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUser.setUserPassword(s.toString());
                //mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mLoginButton = (Button) v.findViewById(R.id.my_login_button);
        Log.d(TAG, "right be fore button, "+ mUser.getUserName() + mUser.getUserPassword());
        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String mUserN = "JDoe";
                String mUserP = "password1";
                //check if entered credentials match our stuff
                 if (mUser.getUserName().equals(mUserN) && mUser.getUserPassword().equals(mUserP)) {
                    messageResId = R.string.login_success;
                    Toast.makeText(getView().getContext(), messageResId, Toast.LENGTH_SHORT).show();
                     mBundle.putSerializable(LOGIN_DATA_KEY, mUser);
                     getActivity().setContentView(R.layout.activity_main);
                    // Intent intent = MainActivity.newIntent(getActivity(), mUser.getId());
                    //getActivity().

                }
                else {
                    messageResId = R.string.login_fail;
                    Toast.makeText(getView().getContext(), messageResId, Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "right after button, "+ mUser.getUserName() + mUser.getUserPassword());
            }
        });


        Log.d(TAG, "after methods called,"); //+ mUser.getUserName());
        return v;   //inflater.inflate(R.layout.my_login, container, false);

    }

}
