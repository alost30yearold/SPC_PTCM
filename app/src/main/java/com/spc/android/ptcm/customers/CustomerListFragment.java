package com.spc.android.ptcm.customers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.spc.android.ptcm.login.LoginActivity;
import com.spc.android.ptcm.R;

import java.util.List;

/**
 * Created by Keith on 9/5/2016.
 */
public class CustomerListFragment extends Fragment {

    private static final String TAG = "CustomerListFragment";

    private static final String ARG_LOGGED_USER = "logged_user";

    private RecyclerView mCustomerRecyclerView;
    private CustomerAdapter mAdapter;
    private String mLogged_user;

    public static CustomerListFragment newInstance(String logged_user) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOGGED_USER, logged_user);


        CustomerListFragment fragment = new CustomerListFragment();
        fragment.setArguments(args);
        return fragment;

    }

        @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);

        mCustomerRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCustomerRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        updateUI();

        return view;

    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_customer,menu);
        //getMenuInflater().inflate(R.menu.menu_customer, menu);
        //return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){

        int messageResId=0;

        switch (menu.getItemId()){
            case R.id.login_menu_item:

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                Log.d(TAG, "login frag after");
                return true;
            case R.id.logout_menu_item:
                 Log.d(TAG, "logout selected");
                messageResId = R.string.logout;
                Toast.makeText(getActivity(), messageResId, Toast.LENGTH_SHORT).show();
                getArguments().putSerializable(ARG_LOGGED_USER, null);
                mLogged_user = null;
                updateUI();
                Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent3);
                return true;
            case R.id.menu_item_new_customer:
                Customer customer = new Customer();
                CustomerLab.get(getActivity()).addCustomer(customer);
                Intent intent1 = CustomerActivity.newIntent(getActivity(), customer.getCustomerId());
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(menu);

        }

    }
    private void updateUI(){
        mLogged_user = (String) getArguments().getSerializable(ARG_LOGGED_USER);
        //String subtitle = getString(R.string.logged_in_as, mLogged_user);
        Log.d(TAG, "updateUi : logged " + mLogged_user);
        AppCompatActivity activity =(AppCompatActivity) getActivity();
        if(mLogged_user != null) {
            activity.getSupportActionBar().setSubtitle(getString(R.string.logged_in_as) + mLogged_user);
        }
        else{
            activity.getSupportActionBar().setSubtitle(getString(R.string.please_login));
        }

        CustomerLab customerLab = CustomerLab.get(getActivity());
        List<Customer> customers = customerLab.getCustomers();



        if(mAdapter == null) {
            mAdapter = new CustomerAdapter(customers);
            mCustomerRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCustomers(customers);
            mAdapter.notifyDataSetChanged();
        }


    }
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageButton mSolvedCheckBox;
        private Customer mCustomer;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            //CardView cv = (CardView)itemView.findViewById(R.id.cv);

            // mTitleTextView = (TextView) itemView;

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            //mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            //mSolvedCheckBox = (ImageButton) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }
        public void bindCustomer(Customer customer){
            mCustomer = customer;
            mTitleTextView.setText(mCustomer.getCustomerName());
            //mDateTextView.setText(mCustomer.getCustomerId().toString());
            //mSolvedCheckBox.setChecked(mCustomer.isSolved());
        }
        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(), mCustomer.getCustomerName() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(), CrimeActivity.class);
             Intent intent = CustomerActivity.newIntent(getActivity(), mCustomer.getCustomerId());
            //Intent intent = CustomerPagerActivity.newIntent(getActivity(), mCustomer.getCustomerId());
            startActivity(intent);
        }


    }
    private class CustomerAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Customer> mCustomers;

        public CustomerAdapter(List<Customer> customers){
            mCustomers = customers;
        }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            View view = layoutInflater.inflate(R.layout.list_item_customer, parent, false);
            return new CrimeHolder(view);
        }
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Customer customer = mCustomers.get(position);
            //holder.mTitleTextView.setText(customer.getTitle());
            holder.bindCustomer(customer);
        }
        @Override
        public int getItemCount(){
            return mCustomers.size();
        }

        public void setCustomers(List<Customer> customers){
            mCustomers = customers;
        }
    }

}