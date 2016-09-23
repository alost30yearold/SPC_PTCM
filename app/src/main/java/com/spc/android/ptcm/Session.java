package com.spc.android.ptcm;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Keith on 9/5/2016.
 */
public class Session implements Serializable {
    private String mSessionName;
//    private Date sessionTimeStart;
//    private Date SessionTimeEnd;
    private String mSessionTimeStart;
    private String mSessionTimeEnd;
    private boolean isFinished;
    private UUID mId;

    public Session(){
        mSessionName = "test f";
        mId = UUID.randomUUID();
        mSessionTimeStart = "start time";
        mSessionTimeEnd = "end time";

//        sessionTimeStart = new Date();
//        SessionTimeEnd = new Date();
        isFinished = true;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getSessionName() {
        return mSessionName;
    }

    public void setSessionName(String sessionName) {
        mSessionName = sessionName;
    }

//    public Date getSessionTimeStart() {
//        return sessionTimeStart;
//    }
//
//    public void setSessionTimeStart(Date sessionTimeStart) {
//        this.sessionTimeStart = sessionTimeStart;
//    }
//
//    public Date getSessionTimeEnd() {
//        return SessionTimeEnd;
//    }
//
//    public void setSessionTimeEnd(Date sessionTimeEnd) {
//        SessionTimeEnd = sessionTimeEnd;
//    }

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
}
