package com.example.ph19127_mob1032_assignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ph19127_mob1032_assignment.DAO.*;
import androidx.annotation.Nullable;

public class SQliteHelperQLSV extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "QuanLiSinhVien";
    public static final int VERSION = 1;

    public static final String CLASS_TABLE = "Lop";
    public static final String CLASS_ID = "IDLop";
    public static final String CLASS_NAME = "TenLop";

    public static final String STUDENT_TABLE = "SinhVien";
    public static final String STUDENT_ID = "IDSinhVien";
    public static final String STUDENT_NAME = "TenSinhVien";
    public static final String STUDENT_CLASS = "TenLop";
    public static final String STUDENT_BIRTHDAY = "NgaySinhSinhVien";


    public SQliteHelperQLSV(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
        //create table lop
        String CREATE_TABLE_CLASS = "CREATE TABLE " + CLASS_TABLE + "(" +
                "" + CLASS_ID + " NVARCHAR(10) NOT NULL PRIMARY KEY ," +
                "" + CLASS_NAME + " NVARCHAR(20) NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(CREATE_TABLE_CLASS);
        //create table sinh vien
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + STUDENT_TABLE + "(" +
                "" + STUDENT_ID + " NVARCHAR(10) NOT NULL PRIMARY KEY ," +
                "" + STUDENT_NAME + " NVARCHAR(50) NOT NULL ," +
                "" + STUDENT_CLASS + " NVARCHAR(10) NOT NULL ," +
                "" + STUDENT_BIRTHDAY + " NVARCHAR(50) NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CLASS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
    }
}
