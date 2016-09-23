package com.spc.android.ptcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget


import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Keith on 9/4/2016.
 */
public class CustomerFragment extends Fragment {
    private static final String TAG = "CustomerFragment";

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final String ARG_LOGGED_USER = "logged_user";

    private Customer mCustomer;
    private TextView mTitleField;
    private EditText mCustomerNameField;
    private EditText mCustomerBillingAddressField;
    private EditText mCustomerEmailField;
    private EditText mCustomerHeight;
    private EditText mCustomerWeight;
    private Button mCustomerInfoButton;
    private Button mCustomerSessionsButton;
    private Button mPayButton;

    public static CustomerFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
       // args.putSerializable(ARG_LOGGED_USER, crimeId);

        CustomerFragment fragment = new CustomerFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        //String loggedUser = (String) getArguments().getSerializable(ARG_LOGGED_USER);

        mCustomer = CustomerLab.get(getActivity()).getCustomer(crimeId);
        Log.d(TAG, "customers created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);

        TextView mLoggedUser = (TextView) v.findViewById(R.id.logged_in_user_text);
        //mLoggedUser.setText(getActivity());
        mTitleField = (TextView) v.findViewById(R.id.customer_name_title);
        mTitleField.setText(mCustomer.getCustomerName());

        mCustomerNameField = (EditText) v.findViewById(R.id.customer_name);
        mCustomerNameField.setText(mCustomer.getCustomerName());
        mCustomerNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setCustomerName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerBillingAddressField = (EditText) v.findViewById(R.id.customer_billing_address);
        mCustomerBillingAddressField.setText(mCustomer.getBillingAddress());
        mCustomerBillingAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setBillingAddress(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerEmailField = (EditText) v.findViewById(R.id.customer_email);
        mCustomerEmailField.setText(mCustomer.getCustomerEmail());
        mCustomerEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setCustomerEmail(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerHeight = (EditText) v.findViewById(R.id.customer_height);
        mCustomerHeight.setText(Integer.toString(mCustomer.getCustomerHeight()));
        mCustomerHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setCustomerHeight(Integer.parseInt(s.toString()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerWeight = (EditText) v.findViewById(R.id.customer_weight);
        mCustomerWeight.setText(Integer.toString(mCustomer.getCustomerWeight()));
        mCustomerWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCustomer.setCustomerWeight(Integer.parseInt(s.toString()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mCustomerInfoButton = (Button) v.findViewById(R.id.customer_info_button);
        mCustomerInfoButton.setText(R.string.customer_info_button_text);
        Log.d(TAG, "right after that " + mCustomer.getCustomerName());
        //mCustomerInfoButton.setEnabled(false);
        mCustomerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
            }
        });

        mCustomerSessionsButton = (Button) v.findViewById(R.id.sessions_button);
        mCustomerSessionsButton.setText(R.string.sessions_button_text);
        mCustomerSessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UUID customerID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
                ArrayList<Session> customerList = mCustomer.getCustomerSessions();
                Log.d(TAG, "right after session clicked after customerlist made " + mCustomer.getCustomerName());

                Intent intent = SessionListActivity.newIntent(getActivity(), customerID,customerList);
                Log.d(TAG, "right after session clicked after intet made " + mCustomer.getCustomerName());
                startActivity(intent);
            }

        });

//        mCustomerSessionsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //Set the crimes solved property
//                //mCustomer.setSolved(isChecked);
//            }
//        });
        mPayButton = (Button) v.findViewById(R.id.pay_button);
        //mPayButton.setText(R.string.customer_info_button_text);
        Log.d(TAG, "right after that " + mCustomer.getCustomerName());
        //mCustomerInfoButton.setEnabled(false);
        mPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PayActivity.newIntent(getActivity(), mCustomer.getId());
                Log.d(TAG, "right after pay clicked after intet made " + mCustomer.getCustomerName());
                startActivity(intent);
            }
        });


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "right after that pause " + mCustomer.getCustomerName());

    }
    protected void feildTextlistener(EditText feild, int layout_text, Customer customer, View v){
        feild = (EditText) v.findViewById(layout_text);
        feild.setText("");




    }
}
