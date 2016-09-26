package com.spc.android.ptcm;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Keith on 9/11/2016.
 */
public class User implements Serializable {

    private String mUserName;
    private String mUserPassword;
    private UUID mUserId;

    public User(){
        mUserId = UUID.randomUUID();
        mUserName = "notNull";
        mUserPassword = "notNull";
    }

    public UUID getId() {
        return mUserId;
    }

    public void setId(UUID id) {
        mUserId = id;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        this.mUserPassword = userPassword;
    }



}
