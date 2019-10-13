package com.example.myapplication1.Database;


import android.provider.BaseColumns;

public final class UserProfile {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private UserProfile() {}

        /* Inner class that defines the table contents */
        public static class users implements BaseColumns {
            public static final String TABLE_NAME = "Update Info";
            public static final String COLUMN_1 = "userName";
            public static final String COLUMN_2 = "dateOfBirth";
            public static final String COLUMN_3 = "password";
            public static final String COLUMN_4 = "gender";
        }
    }

