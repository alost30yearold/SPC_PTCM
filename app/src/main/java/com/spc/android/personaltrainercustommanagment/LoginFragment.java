package com.spc.android.personaltrainercustommanagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Keith on 9/5/2016.
 */
public class LoginFragment extends Fragment {

    private String mUserloggedIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mUserloggedIn = LoginActivity.UserLoginTask.

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState){
        //return inflater.inflate(R.layout.login_fragment, container, false);
        View v = inflater.inflate(R.layout.login_fragment, container, false);
        return v;
    }

}
