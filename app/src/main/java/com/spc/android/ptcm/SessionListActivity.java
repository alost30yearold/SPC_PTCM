package com.spc.android.ptcm;

import android.support.v4.app.Fragment;

/**
 * Created by Keith on 9/12/2016.
 */
public class SessionListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new SessionListFragment();
    }

}
