package com.spc.android.ptcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Keith on 9/5/2016.
 */
public class CustomerListFragment extends Fragment {

    private static final String TAG = "CustomerListFragment";

    private RecyclerView mCustomerRecyclerView;
    private CustomerAdapter mAdapter;

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
        inflater.inflate(R.menu.menu_login,menu);
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        //return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menu){

        int messageResId=0;

        switch (menu.getItemId()){
            case R.id.login_menu_item:
                 Log.d(TAG, "login selected");
                //getActivity().setContentView(R.layout.activity_fragment);
                //if (getView().findViewById(R.id.fragment_container) != null) {
                     Log.d(TAG, "frag cont != null ");
                    // if (savedInstanceState != null) {
                    //    Log.d(TAG, "saved I S != null ");

                    // return;
                    // }
                   // LoginFragment loginFragment = new LoginFragment();
                   // FragmentManager fm = getFragmentManager();
                    //fm.beginTransaction().add(R.id.fragment_container, loginFragment).commit();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

               // }
                //login();
                 Log.d(TAG, "login frag after");
                return true;
            case R.id.logout_menu_item:
                 Log.d(TAG, "logout selected");
                messageResId = R.string.logout;
                Toast.makeText(getActivity(), messageResId, Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "fucking fuck");// + loginFragment.getArguments());
                // mUser = new User();
                //setContentView(R.layout.activity_main);
                //logout();
                return true;
            default:
                return super.onOptionsItemSelected(menu);

        }

    }
    private void updateUI(){
        CustomerLab customerLab = CustomerLab.get(getActivity());
        List<Customer> customers = customerLab.getCustomers();

        if(mAdapter == null) {
            mAdapter = new CustomerAdapter(customers);
            mCustomerRecyclerView.setAdapter(mAdapter);
        } else {
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

            // mTitleTextView = (TextView) itemView;

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (ImageButton) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }
        public void bindCustomer(Customer customer){
            mCustomer = customer;
            mTitleTextView.setText(mCustomer.getCustomerName());
            //mDateTextView.setText(mCustomer.getId().toString());
            //mSolvedCheckBox.setChecked(mCustomer.isSolved());
        }
        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(), mCustomer.getCustomerName() + " clicked!", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(), CrimeActivity.class);
             Intent intent = CustomerActivity.newIntent(getActivity(), mCustomer.getId());
            //Intent intent = CustomerPagerActivity.newIntent(getActivity(), mCustomer.getId());
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
    }

}