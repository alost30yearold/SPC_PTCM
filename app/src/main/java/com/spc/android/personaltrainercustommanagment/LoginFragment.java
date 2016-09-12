package com.spc.android.personaltrainercustommanagment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Keith on 9/5/2016.
 */
public class LoginFragment extends Fragment {

    private String mUserloggedIn;
    private TextView mLoggedInUser;

   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       mLoggedInUser = (TextView) mLoggedInUser.findViewById(R.id.logged_in_user_text);
       mLoggedInUser.setText(mUserloggedIn);

       mUserloggedIn = getString(R.string.user_name);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstnaceState){
        return inflater.inflate(R.layout.login_fragment, container, false);



    }

}
