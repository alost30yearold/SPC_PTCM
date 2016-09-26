package com.spc.android.ptcm.sessions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

import com.spc.android.ptcm.customers.CustomerLab;
import com.spc.android.ptcm.login.LoginActivity;
import com.spc.android.ptcm.R;

import java.util.List;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class SessionListFragment extends Fragment {

    private static final String TAG = "SessionListFragment";

    private static final String ARG_CUSTOMER_ID = "customer_id";
    private static final String ARG_CUSTOMER_NAME = "customer_name";

    private RecyclerView mSessionRecyclerView;
    private SessionAdapter mAdapterS;
    private String mCustomerName;

    public static SessionListFragment newInstance(UUID customerId, String customerName) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CUSTOMER_ID, customerId);
        args.putSerializable(ARG_CUSTOMER_NAME, customerName);


        SessionListFragment fragment = new SessionListFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        mSessionRecyclerView = (RecyclerView) view.findViewById(R.id.session_recycler_view);
        mSessionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_session, menu);
        //getMenuInflater().inflate(R.menu.menu_customer, menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {

        int messageResId = 0;

        switch (menu.getItemId()) {
            case R.id.login_menu_item:
                Log.d(TAG, "login selected");

                Log.d(TAG, "frag cont != null ");

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                Log.d(TAG, "login frag after");
                return true;
            case R.id.logout_menu_item:
                Log.d(TAG, "logout selected");
                messageResId = R.string.logout;
                Toast.makeText(getActivity(), messageResId, Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "testing test");// + loginFragment.getArguments());
                // mUser = new User();
                //setContentView(R.layout.activity_main);
                //logout();
                return true;
            case R.id.menu_item_new_session:
                Session session = new Session((UUID)getArguments().getSerializable(ARG_CUSTOMER_ID));
                Log.d(TAG, "new selected then uuid :" +getArguments().getSerializable(ARG_CUSTOMER_ID));
                CustomerLab.get(getActivity()).addSession(session);
                Intent intent1 = SessionActivity.newIntent(getActivity(), session.getSessionUUID());
                Log.d(TAG, "new selected" +session.getSessionUUID());
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(menu);

        }

    }

    private void updateUI() {
        mCustomerName =(String) getArguments().getSerializable(ARG_CUSTOMER_NAME);
        AppCompatActivity activity =(AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(mCustomerName);


        UUID mCustomerId = (UUID) getArguments().getSerializable(ARG_CUSTOMER_ID);
        CustomerLab sessionLab = CustomerLab.get(getActivity());
        List<Session> sessions = sessionLab.getSessions(mCustomerId);
        //ArrayList<Session> sessions = (ArrayList<Session>) getArguments().getSerializable(ARG_CUSTOMER_LIST);

        if (mAdapterS == null) {
            mAdapterS = new SessionAdapter(sessions);
            mSessionRecyclerView.setAdapter(mAdapterS);
        } else {
            mAdapterS.setSessions(sessions);
            mAdapterS.notifyDataSetChanged();
        }

    }

    private class SessionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //public TextView mTitleTextView;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageButton mSolvedCheckBox2;
        private Session mSession;

        public SessionHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            // mTitleTextView = (TextView) itemView;

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_session_title_text_view);
            //mDateTextView = (TextView) itemView.findViewById(R.id.list_item_session_date_text_view);
            //mSolvedCheckBox2 = (ImageButton) itemView.findViewById(R.id.list_item_session_solved_check_box);
        }

        public void bindSession(Session session) {
            mSession = session;
            String number = Integer.toString(mSession.getSessionNumber());
            mTitleTextView.setText("From: "+mSession.getSessionDateStart().toString()+ "\nTo     : "+mSession.getSessionDateEnd().toString());//mSession.getSessionUUID().toString());

            //mTitleTextView.setText(mSession.getSessionNumber()); DOES NOT WORK NO IDEA WHY
            //mDateTextView.setText(mCustomer.getCustomerId().toString());
            //mSolvedCheckBox.setChecked(mCustomer.isSolved());
        }

        @Override
        public void onClick(View v) {


            Toast.makeText(getActivity(), mSession.getSessionNumber() + " clicked!", Toast.LENGTH_SHORT).show();

            Intent intent = SessionActivity.newIntent(getActivity(), mSession.getSessionUUID());

            startActivity(intent);
        }


    }

    private class SessionAdapter extends RecyclerView.Adapter<SessionHolder> {

        private List<Session> mSessions;

        public SessionAdapter(List<Session> sessions) {
            mSessions = sessions;
        }

        @Override
        public SessionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            //View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            View view = layoutInflater.inflate(R.layout.list_item_session, parent, false);
            return new SessionHolder(view);
        }

        @Override
        public void onBindViewHolder(SessionHolder holder, int position) {
            Session session = mSessions.get(position);
            //holder.mTitleTextView.setText(customer.getTitle());
            holder.bindSession(session);
        }

        @Override
        public int getItemCount() {
            return mSessions.size();
        }

        public void setSessions(List<Session> sessions){
            mSessions = sessions;
        }
    }

}