package com.face_location.facelocation.model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 05.12.17.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "application.db";

    public static final String TABLE_NAME = "server_responses";

    public static final String ID = "ID";
    public static final String USER_ID = "USER_ID";
    public static final String FIRST_LOGIN = "FIRST_LOGIN";
    public static final String USER_EMAIL = "EMAIL";
    public static final String USER_ROLE = "ROLE";
    public static final String USER_STATUS = "STATUS";
    public static final String USER_TOKEN = "TOKEN";
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_LASTNAME = "LASTNAME";
    public static final String USER_PHONE = "PHONE";
    public static final String USER_JOB = "JOB";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_ID + " TEXT, " +
                FIRST_LOGIN + " BOOLEAN, " +
                USER_EMAIL + " TEXT, " +
                USER_ROLE + " TEXT, " +
                USER_STATUS + " TEXT, " +
                USER_TOKEN + " TEXT, " +
                USER_NAME + " TEXT, " +
                USER_LASTNAME + " TEXT, " +
                USER_PHONE + " TEXT, " +
                USER_JOB + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String userId,
                           Boolean firstLogin,
                           String userEmail,
                           String userRole,
                           String userStatus,
                           String userToken,
                           String userName,
                           String userLastname,
                           String userPhone,
                           String userJob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_ID, userId);
        contentValues.put(FIRST_LOGIN, firstLogin);
        contentValues.put(USER_EMAIL, userEmail);
        contentValues.put(USER_ROLE, userRole);
        contentValues.put(USER_STATUS, userStatus);
        contentValues.put(USER_TOKEN, userToken);
        contentValues.put(USER_NAME, userName);
        contentValues.put(USER_LASTNAME, userLastname);
        contentValues.put(USER_PHONE, userPhone);
        contentValues.put(USER_JOB, userJob);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addData(Boolean firstLogin)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRST_LOGIN, firstLogin);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
}
