package com.example.app.Contracts;
import android.provider.BaseColumns;

/**
 * Created by Nino on 17/02/14.
 */
public final class UserDataContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public UserDataContract(){}

    public static abstract class UserDataEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_NAME_NULLABLE = "null";
    }
}
