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

    private static final String ARG_SESSION_ID = "session_id";
    private static final String DIALOG_DATE = "DialogDate";

    private Session mSession;
    private TextView mSessionTitleField;
    private EditText mStartTimeField;
    private EditText mEndTimeField;
    private Button mSaveSessionButton;
   // private Button mCustomerSessionsButton;

    public static SessionFragment newInstance(UUID sessionId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_SESSION_ID, sessionId);

        SessionFragment fragment = new SessionFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        //UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID sessionId = (UUID) getArguments().getSerializable(ARG_SESSION_ID);

        //mSession = CustomerLab.get(getActivity()).getSession(sessionId);
        Log.d(TAG, "Sessions created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Session view start before view");
        View v = inflater.inflate(R.layout.session_fragment, container, false);
        Log.d(TAG, "Session view start");
        mSessionTitleField = (TextView) v.findViewById(R.id.session_name_title);
       // mSessionTitleField.setText(mSession.getSessionName());
        Log.d(TAG, "Session  TextCleared");
        mStartTimeField = (EditText) v.findViewById(R.id.start_time);
        //mStartTimeField.setText(mSession.getSessionName());
        Log.d(TAG, "Session view set TextCleared");
        mStartTimeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSession.setSessionTimeStart(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Log.d(TAG, "Session view after TextWatcher");
        mSaveSessionButton = (Button) v.findViewById(R.id.save_session_button);
        mSaveSessionButton.setText(R.string.save_session_button_text);
        Log.d(TAG, "right after that ");//+mSession.getSessionName());
        //mCustomerInfoButton.setEnabled(false);
        mSaveSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager manager = getFragmentManager();




//                DatePickerFragment dialog = new DatePickerFragment();
//                DatePickerFragment dialog = DatePickerFragment.newInstance(mCustomer.getDate());
//
//                dialog.show(manager, DIALOG_DATE);
//                dialog.show(manager, DIALOG_DATE);
            }
        });

//        mCustomerSessionsButton = (Button) v.findViewById(R.id.sessions_button);
//
//        mCustomerSessionsButton.setText(R.string.sessions_button_text);
//
//        mCustomerSessionsButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//                //DatePickerFragment dialog = new DatePickerFragment();
//                //DatePickerFragment dialog = DatePickerFragment.newInstance(mCustomer.getDate());
//
//                //dialog.show(manager, DIALOG_DATE);
//                //dialog.show(manager, DIALOG_DATE);
//            }
//
//        });

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
