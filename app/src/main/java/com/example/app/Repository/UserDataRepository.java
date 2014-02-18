package com.example.app.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app.Contracts.UserDataContract;

/**
 * Created by nkhodabandeh on 18/02/14.
 */
public class UserDataRepository extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserData.db";

    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserDataContract.UserDataEntry.TABLE_NAME + "("+
                    UserDataContract.UserDataEntry.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY,"+
                    UserDataContract.UserDataEntry.COLUMN_NAME_USERNAME +" "+ TEXT_TYPE + COMMA_SEP +
                    UserDataContract.UserDataEntry.COLUMN_NAME_FIRSTNAME +" "+ TEXT_TYPE + COMMA_SEP +
                    UserDataContract.UserDataEntry.COLUMN_NAME_LASTNAME +" "+ TEXT_TYPE + COMMA_SEP +
                    UserDataContract.UserDataEntry.COLUMN_NAME_AGE +" "+ TEXT_TYPE + COMMA_SEP +
                    UserDataContract.UserDataEntry.COLUMN_IMAGE +" "+ TEXT_TYPE +")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+ UserDataContract.UserDataEntry.TABLE_NAME;


    public UserDataRepository(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
