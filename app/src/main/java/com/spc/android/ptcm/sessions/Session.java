package com.spc.android.ptcm.sessions;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class Session implements Serializable {
    private UUID mCustomerUUID;
    private int mSessionNumber;
    private Date mSessionDateStart;
    private Date mSessionDateEnd;
    private String mSessionTimeStart;
    private String mSessionTimeEnd;
    private boolean isFinished;
    private UUID mSessionId;

//    public Session(){
//        this.mCustomerUUID = UUID.randomUUID();
//        this.mSessionNumber = 1;
//        this.mSessionId = UUID.randomUUID();
//        this.mSessionTimeStart = "start time";
//        this. mSessionTimeEnd = "end time";
////      sessionTimeStart = new Date();
////      SessionTimeEnd = new Date();
//        isFinished = true;
//    }
    public Session(UUID customerUUID){
        this.mCustomerUUID = customerUUID;
        this.mSessionNumber = 0;
        this.mSessionId = UUID.randomUUID();
        this.mSessionDateStart = new Date();
        this.mSessionDateEnd = new Date();
        this.mSessionTimeStart = "start time";
        this.mSessionTimeEnd = "end time";
        this.isFinished = true;


    }

    public UUID getSessionUUID() {
        return mSessionId;
    }

    public void setSessionUUID(UUID id) {
        mSessionId = id;
    }

    public int getSessionNumber() {
        return mSessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        mSessionNumber = sessionNumber;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setSessionTimeStart(String sessionTimeStart) {
        this.mSessionTimeStart = sessionTimeStart;
    }

    public void setSessionTimeEnd(String sessionTimeEnd) {
        mSessionTimeEnd = sessionTimeEnd;
    }

    public UUID getCustomerUUID() {
        return mCustomerUUID;
    }

    public void setCustomerUUID(UUID customerUUID) {
        mCustomerUUID = customerUUID;
    }

    public String getSessionTimeStart() {
        return mSessionTimeStart;
    }

    public String getSessionTimeEnd() {
        return mSessionTimeEnd;
    }

    public Date getSessionDateStart() {
        return mSessionDateStart;
    }

    public void setSessionDateStart(Date sessionDateStart) {
        this.mSessionDateStart = sessionDateStart;
    }

    public Date getSessionDateEnd() {
        return mSessionDateEnd;
    }

    public void setSessionDateEnd(Date sessionDateEnd) {
        this.mSessionDateEnd = sessionDateEnd;
    }

}
