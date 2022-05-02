package com.example.ph19127_mob1032_assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.ph19127_mob1032_assignment.database.*;
import com.example.ph19127_mob1032_assignment.model.ClassStudent;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    public SQliteHelperQLSV dbHelper;
    //public SQLiteDatabase db;
    //private Context context;
    public ClassDAO(Context context) {
        //this.context = context;
        dbHelper = new SQliteHelperQLSV(context);
        //db = dbHelper.getWritableDatabase();
    }
    public long insertClass(ClassStudent p_ClassStudent) {
        long res = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQliteHelperQLSV.CLASS_ID, p_ClassStudent.getMaLop());
        contentValues.put(SQliteHelperQLSV.CLASS_NAME, p_ClassStudent.getTenLop());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.insert(SQliteHelperQLSV.CLASS_TABLE, null, contentValues);
        db.close();
        return res;
    }
    public long updateClass(ClassStudent p_classStudent, String p_malop) {
        long res = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQliteHelperQLSV.CLASS_NAME, p_classStudent.getTenLop());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.update(SQliteHelperQLSV.CLASS_TABLE
                , contentValues
                , SQliteHelperQLSV.CLASS_ID + "= ?"
                , new String[] {p_malop});
        db.close();
        return res;
    }
    public long deleteClass(String p_maLop) {
        long res = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.delete(SQliteHelperQLSV.CLASS_TABLE
                , SQliteHelperQLSV.CLASS_ID + "= ?"
                , new String[] {p_maLop});
        db.close();
        return res;
    }
    public List<ClassStudent> getAllClasses() {
        // ClassStudent != java.lang.ClassStudent
        List<ClassStudent> classStudents = new ArrayList<>();
        classStudents.clear();
        String Querry = "SELECT * FROM " + SQliteHelperQLSV.CLASS_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        try (Cursor cursor = sqLiteDatabase.rawQuery(Querry, null)) {
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        @SuppressLint("Range") ClassStudent temp_classStudent = new ClassStudent(
                                cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.CLASS_ID)),
                                cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.CLASS_NAME))
                        );
                        classStudents.add(temp_classStudent);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }
        return classStudents;
    }
}
