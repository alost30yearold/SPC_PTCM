package com.spc.android.ptcm;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Keith on 9/12/2016.
 */
public class CustomerListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CustomerListFragment();
    }

}
