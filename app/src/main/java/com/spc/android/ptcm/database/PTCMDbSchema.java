package com.spc.android.ptcm.database;

/**
 * Created by Keith on 9/25/2016.
 */
public class PTCMDbSchema {
    public static final class CustomerTable {
        public static final String TABLE_NAME = "customers";

        public static final class Columns {
            public static final String CUSTOMER_UUID = "uuid";
            public static final String CUSTOMER_NAME = "name";
            public static final String CUSTOMER_BILLING_ADDRESS = "address";
            public static final String CUSTOMER_EMAIL = "email";
            public static final String CUSTOMER_SESSION_ARRAY = "customer_sessions";

        }
    }
    public static final class SessionTable {
        public static final String TABLE_NAME = "customerSessions";

        public static final class Columns {
            public static final String CUSTOMER_UUID = "customer_uuid";
            public static final String SESSION_UUID = "uuid";
            public static final String SESSION_NUMBER = "number";
            public static final String SESSION_START_TIME = "start";
            public static final String SESSION_END_TIME = "end";
            public static final String SESSION_IS_FINISHED = "isFinished";
            //TO DO get rid of UUID for sessions

        }
    }
    public static final class UserTable {
        public static final String TABLE_NAME = "users";

        public static final class Columns {
            public static final String USER_UUID = "uuid";
            public static final String USER_NAME = "user";
            public static final String USER_PASSWORD = "password";


        }
    }
}
