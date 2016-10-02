package com.spc.android.ptcm.customers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget


import com.spc.android.ptcm.PayActivity;
import com.spc.android.ptcm.PictureUtils;
import com.spc.android.ptcm.R;
import com.spc.android.ptcm.sessions.Session;
import com.spc.android.ptcm.sessions.SessionListActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Keith on 9/4/2016.
 */
public class CustomerFragment extends Fragment {
    private static final String TAG = "CustomerFragment";

    private static final String ARG_CUSTOMER_ID = "customer_id";
    private static final String ARG_CUSTOMER_NAME = "customer_name";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_LOGGED_USER = "logged_user";

    private static final int REQUEST_PHOTO = 2;

    private Customer mCustomer;
    private File mPhotoFile;
    private TextView mTitleField;
    private EditText mCustomerNameField;
    private EditText mCustomerBillingAddressField;
    private EditText mCustomerEmailField;
    private Button mCustomerInfoButton;
    private Button mCustomerSessionsButton;
    private Button mPayButton;
    private Button mCustomerSaveButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

//    private String mCustomerNameSaved;
//    private String mCustomerBillingAddressSaved;
//    private String mCustomerEmailSaved;

    public static CustomerFragment newInstance(UUID customerUUID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CUSTOMER_ID, customerUUID);

        // args.putSerializable(ARG_LOGGED_USER, crimeId);

        CustomerFragment fragment = new CustomerFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID customerId = (UUID) getArguments().getSerializable(ARG_CUSTOMER_ID);
        //String loggedUser = (String) getArguments().getSerializable(ARG_LOGGED_USER);

        mCustomer = CustomerLab.get(getActivity()).getCustomer(customerId);
        mPhotoFile = CustomerLab.get(getActivity()).getPhotoFile(mCustomer);
        Log.d(TAG, "customers created" + mCustomer);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "right after that pause " + mCustomer.getCustomerName());

        CustomerLab.get(getActivity()).updateCustomer(mCustomer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_customer, container, false);

        //TextView mLoggedUser = (TextView) v.findViewById(R.id.log);
        //mLoggedUser.setText(getActivity());
        mTitleField = (TextView) v.findViewById(R.id.customer_name_title);
        mTitleField.setText(mCustomer.getCustomerName());

        mCustomerNameField = (EditText) v.findViewById(R.id.customer_name);
        mCustomerNameField.setText(mCustomer.getCustomerName());
        mCustomerNameField.setEnabled(false);
        mCustomerNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mCustomerNameSaved = s.toString();
                mCustomer.setCustomerName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerBillingAddressField = (EditText) v.findViewById(R.id.customer_billing_address);
        mCustomerBillingAddressField.setText(mCustomer.getBillingAddress());
        mCustomerBillingAddressField.setEnabled(false);
        mCustomerBillingAddressField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mCustomerBillingAddressSaved = s.toString();
                mCustomer.setBillingAddress(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCustomerEmailField = (EditText) v.findViewById(R.id.customer_email);
        mCustomerEmailField.setText(mCustomer.getCustomerEmail());
        mCustomerEmailField.setEnabled(false);
        mCustomerEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mCustomerEmailSaved = s.toString();
                mCustomer.setCustomerEmail(s.toString());

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
                mCustomerNameField.setEnabled(true);
                mCustomerBillingAddressField.setEnabled(true);
                mCustomerEmailField.setEnabled(true);
                mCustomerSaveButton.setVisibility(View.VISIBLE);
            }
        });
        mCustomerSaveButton = (Button) v.findViewById(R.id.save_customer_button);
        mCustomerSaveButton.setText("Save Customer Info");
        mCustomerSaveButton.setVisibility(View.INVISIBLE);
        mCustomerSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomerNameField.setEnabled(false);
                mCustomerBillingAddressField.setEnabled(false);
                mCustomerEmailField.setEnabled(false);
                mCustomerSaveButton.setVisibility(View.INVISIBLE);

            }
        });

        mCustomerSessionsButton = (Button) v.findViewById(R.id.sessions_button);
        mCustomerSessionsButton.setText(R.string.sessions_button_text);
        mCustomerSessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UUID customerID = (UUID) getArguments().getSerializable(ARG_CUSTOMER_ID);
                ArrayList<Session> customerList = mCustomer.getCustomerSessions();
                Log.d(TAG, "right after session clicked after customerlist made " + mCustomer.getCustomerName());

                Intent intent = SessionListActivity.newIntent(getActivity(), customerID, mCustomer.getCustomerName());
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
                Intent intent = PayActivity.newIntent(getActivity(), mCustomer.getCustomerId());
                Log.d(TAG, "right after pay clicked after intet made " + mCustomer.getCustomerName());
                startActivity(intent);
            }
        });

        PackageManager packageManager = getActivity().getPackageManager();

        mPhotoButton = (ImageButton) v.findViewById(R.id.customer_camera);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        Log.d(TAG, "can take photo " + canTakePhoto);
        Log.d(TAG, "can take photo " );
        mPhotoButton.setEnabled(canTakePhoto);
        if (canTakePhoto)

        {
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        }


        mPhotoButton.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(View v) {

                                                startActivityForResult(captureImage, REQUEST_PHOTO);
                                            }
                                        }

        );
        mPhotoView = (ImageView) v.findViewById(R.id.customer_photo);

        updatePhotoView();


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_PHOTO) {
            updatePhotoView();
        }
}

    public void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }

    protected void feildTextlistener(EditText feild, int layout_text, Customer customer, View v) {
        feild = (EditText) v.findViewById(layout_text);
        feild.setText("");

    }

}
