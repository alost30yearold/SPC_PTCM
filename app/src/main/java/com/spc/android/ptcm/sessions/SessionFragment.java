package com.spc.android.ptcm.sessions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget


import com.spc.android.ptcm.customers.CustomerLab;
import com.spc.android.ptcm.R;
import com.spc.android.ptcm.sessions.dateandtime.DatePickerFragment;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Keith on 9/4/2016.
 */
public class SessionFragment extends Fragment {
    private static final String TAG = "SessionFragment";

    private static final String ARG_SESSION_ID = "session_id";
    private static final String DIALOG_START_DATE = "DialogStartDate";
    private static final String DIALOG_END_DATE = "DialogEndDate";


    private static final int REQUEST_DATE = 0;

    private Session mSession;
    private TextView mSessionTitleField;
    private EditText mStartTimeField;
    private EditText mEndTimeField;
    private Button mSaveSessionButton;
    private Button mStartTimeButton;
    private Button mEndTimeButton;
    private CheckBox mIsSessionFinishedCheckBox;

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
        Log.d(TAG, "be fore Sessions created"+sessionId);
        mSession = CustomerLab.get(getActivity()).getSession(sessionId);
        Log.d(TAG, "Sessions created"+mSession);
    }
    @Override
    public void onPause(){
        super.onPause();
        //Log.d(TAG, "right after that pause " + mSession.getSessionUUID());

        CustomerLab.get(getActivity()).updateSession(mSession);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        View v = inflater.inflate(R.layout.fragment_session, container, false);

        mSessionTitleField = (TextView) v.findViewById(R.id.session_name_title);
        mSessionTitleField.setText("Session");

        mStartTimeButton = (Button) v.findViewById(R.id.edit_start_button);
        mStartTimeButton.setText(mSession.getSessionTimeStart());
        mStartTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager manager = getFragmentManager();

                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mSession.getSessionDateStart());

                dialog.setTargetFragment(SessionFragment.this,REQUEST_DATE);
                //dialog.show(manager, DIALOG_DATE);
                dialog.show(manager, DIALOG_START_DATE);
            }
        });
        mEndTimeButton = (Button) v.findViewById(R.id.edit_end_button);
        mEndTimeButton.setText(mSession.getSessionTimeEnd());
        mEndTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager manager = getFragmentManager();

                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mSession.getSessionDateEnd());

                //dialog.show(manager, DIALOG_DATE);
                dialog.show(manager, DIALOG_END_DATE);
            }
        });

//        mStartTimeField = (EditText) v.findViewById(R.id.start_time);
//        //mStartTimeField.setText(mSession.getSessionNumber());
//
//        mStartTimeField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //mSession.setSessionTimeStart(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        mEndTimeField = (EditText) v.findViewById(R.id.end_time);
//        //mStartTimeField.setText(mSession.getSessionNumber());
//
//        mEndTimeField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                 mSession.setSessionTimeEnd(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        Log.d(TAG, "Session view after TextWatcher");
        mSaveSessionButton = (Button) v.findViewById(R.id.save_session_button);
        mSaveSessionButton.setText(R.string.save_session_button_text);
        //-------------------------------------------------------------------------------------------------Log.d(TAG, "right after that "+mSession.getSessionNumber());
        //mCustomerInfoButton.setEnabled(false);
        mSaveSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mSession.setSessionDateStart(date);
            updateDate();
        }
    }

    private void updateDate() {
        mStartTimeButton.setText(mSession.getSessionDateStart().toString());
    }

}
