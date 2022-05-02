package com.example.ph19127_mob1032_assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ph19127_mob1032_assignment.R;
import com.example.ph19127_mob1032_assignment.DAO.*;
import com.example.ph19127_mob1032_assignment.model.*;
import com.example.ph19127_mob1032_assignment.adapter.*;


import java.util.ArrayList;
import java.util.List;

public class AddClassActivity extends AppCompatActivity {
    EditText txtMaLop, txtTenLop;
    Button btnThemLop, btnLamMoi, btnSuaLop, btnXoaLop;
    ClassDAO classDAO;
    ListView listView;
    List<ClassStudent> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        txtMaLop = findViewById(R.id.txtMaLop);
        txtTenLop = findViewById(R.id.txtTenLop);
        btnThemLop = findViewById(R.id.btnThemLop);
        btnLamMoi = findViewById(R.id.btnLamMoi);
        btnSuaLop = findViewById(R.id.btnSuaLop);
        btnXoaLop = findViewById(R.id.btnXoaLop);
        listView = findViewById(R.id.listView);


        classDAO = new ClassDAO(AddClassActivity.this);

        btnThemLop.setOnClickListener(view -> themLop());
        btnLamMoi.setOnClickListener(view -> reloadEditText());
        btnSuaLop.setOnClickListener(view -> CapNhatLop());
        btnXoaLop.setOnClickListener(view -> xoaLop());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassStudent classStudent = (ClassStudent) listView.getItemAtPosition(position);
                txtMaLop.setText(classStudent.getMaLop());
                txtTenLop.setText(classStudent.getTenLop());
            }
        });
        xemLop();

    }

    private void reloadEditText() {
        txtMaLop.setText("");
        txtTenLop.setText("");
    }
    private void xemLop() {
        List<ClassStudent> classes = new ArrayList<>();
        classes = classDAO.getAllClasses();
        ClassAdapter classAdapter = new ClassAdapter(AddClassActivity.this,
                R.layout.item_class_student, classes);
        listView.setAdapter(classAdapter);
    }

    private void themLop() {
        ClassStudent aClassStudent = new ClassStudent();
        aClassStudent.setMaLop(txtMaLop.getText().toString());
        aClassStudent.setTenLop(txtTenLop.getText().toString());
        if (classDAO.insertClass(aClassStudent) <= 0){
            Toast.makeText(getApplicationContext(), "Thêm lớp thất bại", Toast.LENGTH_SHORT).show();
        } else {
            xemLop();
            reloadEditText();
            Toast.makeText(getApplicationContext(), "Thêm lớp Thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void CapNhatLop() {
        ClassStudent classStudent = new ClassStudent();
        classStudent.setMaLop(txtMaLop.getText().toString());
        classStudent.setTenLop(txtTenLop.getText().toString());
        if (classDAO.updateClass(classStudent, classStudent.getMaLop()) <= 0) {
            Toast.makeText(getApplicationContext(), "Sửa lớp thất bại", Toast.LENGTH_SHORT).show();
        } else {
            xemLop();
            reloadEditText();
            Toast.makeText(getApplicationContext(), "Sửa lớp thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void xoaLop() {
        String classId = txtMaLop.getText().toString();
        if (classDAO.deleteClass(classId) <= 0) {
            Toast.makeText(getApplicationContext(), "Xóa lớp thất bại", Toast.LENGTH_SHORT).show();
        } else {
            xemLop();
            reloadEditText();
            Toast.makeText(getApplicationContext(), "Xóa lớp thành công", Toast.LENGTH_SHORT).show();
        }
    }
}