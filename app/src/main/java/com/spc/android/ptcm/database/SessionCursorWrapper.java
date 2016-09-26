package com.spc.android.ptcm.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.spc.android.ptcm.sessions.Session;
import com.spc.android.ptcm.database.PTCMDbSchema.SessionTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Keith on 9/25/2016.
 */
public class SessionCursorWrapper extends CursorWrapper {
    public SessionCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Session getSession(){
        String customerUUID = getString(getColumnIndex(SessionTable.Columns.CUSTOMER_UUID));
        String sessionUUID = getString(getColumnIndex(SessionTable.Columns.SESSION_UUID));
        int number = getInt(getColumnIndex(SessionTable.Columns.SESSION_NUMBER));
        String startTime = getString(getColumnIndex(SessionTable.Columns.SESSION_START_TIME));
        String endTime = getString(getColumnIndex(SessionTable.Columns.SESSION_END_TIME));
        int isFinished = getInt(getColumnIndex(SessionTable.Columns.SESSION_IS_FINISHED));

        Session session = new Session(UUID.fromString(customerUUID));
        session.setSessionUUID(UUID.fromString(sessionUUID));
        session.setSessionNumber(number);
        session.setSessionTimeStart(startTime);
        session.setSessionTimeEnd(endTime);
        session.setFinished(isFinished != 0);

        return session;

    }
}
