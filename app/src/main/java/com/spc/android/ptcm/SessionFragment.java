package com.spc.android.ptcm;

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


import java.util.UUID;

/**
 * Created by Keith on 9/4/2016.
 */
public class SessionFragment extends Fragment {
    private static final String TAG = "SessionFragment";

    private static final String ARG_CRIME_ID = "session_id";
    private static final String DIALOG_DATE = "DialogDate";

    private Customer mCustomer;
    private TextView mTitleField;
    private EditText mCustomerNameField;
    private Button mCustomerInfoButton;
    private Button mCustomerSessionsButton;

    public static SessionFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        SessionFragment fragment = new SessionFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);

        mCustomer = CustomerLab.get(getActivity()).getCustomer(crimeId);
        Log.d(TAG, "customers created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);

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

        mCustomerInfoButton = (Button) v.findViewById(R.id.customer_info_button);
        mCustomerInfoButton.setText(R.string.customer_info_button_text);
        Log.d(TAG, "right after that shit"+mCustomer.getCustomerName());
        //mCustomerInfoButton.setEnabled(false);
        mCustomerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();




                //DatePickerFragment dialog = new DatePickerFragment();
                //DatePickerFragment dialog = DatePickerFragment.newInstance(mCustomer.getDate());

                //dialog.show(manager, DIALOG_DATE);
                //dialog.show(manager, DIALOG_DATE);
            }
        });

        mCustomerSessionsButton = (Button) v.findViewById(R.id.sessions_button);

        mCustomerSessionsButton.setText(R.string.sessions_button_text);

        mCustomerSessionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                //DatePickerFragment dialog = DatePickerFragment.newInstance(mCustomer.getDate());

                //dialog.show(manager, DIALOG_DATE);
                //dialog.show(manager, DIALOG_DATE);
            }

        });

//        mCustomerSessionsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //Set the crimes solved property
//                //mCustomer.setSolved(isChecked);
//            }
//        });

        return v;
    }

}
