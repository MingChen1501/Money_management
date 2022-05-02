package com.example.ph19127_mob1032_assignment.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph19127_mob1032_assignment.database.SQliteHelperQLSV;
import com.example.ph19127_mob1032_assignment.model.*;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public SQliteHelperQLSV dbHelper;
    //public SQLiteDatabase db;
    //private Context context;
    public StudentDAO(Context context) {
        //this.context = context;
        dbHelper = new SQliteHelperQLSV(context);
        //db = dbHelper.getWritableDatabase();
    }
    public long insertClass(Student p_Student) {
        long res = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQliteHelperQLSV.STUDENT_ID, p_Student.getId());
        contentValues.put(SQliteHelperQLSV.STUDENT_NAME, p_Student.getTenSV());
        contentValues.put(SQliteHelperQLSV.STUDENT_CLASS, p_Student.getLop());
        contentValues.put(SQliteHelperQLSV.STUDENT_BIRTHDAY, p_Student.getNgaySinh());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.insert(SQliteHelperQLSV.STUDENT_TABLE, null, contentValues);
        db.close();
        return res;
    }
    public long updateClass(Student p_Student, String p_idStudent) {
        long res = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQliteHelperQLSV.STUDENT_NAME, p_Student.getTenSV());
        contentValues.put(SQliteHelperQLSV.STUDENT_CLASS, p_Student.getLop());
        contentValues.put(SQliteHelperQLSV.STUDENT_BIRTHDAY, p_Student.getNgaySinh());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.update(SQliteHelperQLSV.STUDENT_TABLE
                , contentValues
                , SQliteHelperQLSV.STUDENT_ID + "= ?"
                , new String[] {p_idStudent});
        db.close();
        return res;
    }
    public long deleteClass(String p_idStudent) {
        long res = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        res = db.delete(SQliteHelperQLSV.STUDENT_TABLE
                , SQliteHelperQLSV.STUDENT_ID + "= ?"
                , new String[] {p_idStudent});
        db.close();
        return res;
    }
    @SuppressLint("Range")
    public List<Student> getAllStudents() {
        // ClassStudent != java.lang.ClassStudent
        List<Student> students = new ArrayList<>();
        students.clear();
        String Querry = "SELECT * FROM " + SQliteHelperQLSV.STUDENT_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        try (Cursor cursor = sqLiteDatabase.rawQuery(Querry, null)) {
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        @SuppressLint("Range") Student temp_Student = new Student();
                        //dùng setter để tránh nhầm
                        temp_Student.setId(cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.STUDENT_ID)));
                        temp_Student.setLop(cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.STUDENT_CLASS)));
                        temp_Student.setNgaySinh(cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.STUDENT_BIRTHDAY)));
                        temp_Student.setTenSV(cursor.getString(cursor.getColumnIndex(SQliteHelperQLSV.STUDENT_NAME)));
                        students.add(temp_Student);
                        cursor.moveToNext();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqLiteDatabase.close();
        }
        return students;
    }
}
