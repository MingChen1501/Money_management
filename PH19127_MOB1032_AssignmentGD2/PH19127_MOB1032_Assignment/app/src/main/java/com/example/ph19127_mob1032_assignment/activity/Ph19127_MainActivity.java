package com.example.ph19127_mob1032_assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.ph19127_mob1032_assignment.R;

public class Ph19127_MainActivity extends AppCompatActivity {
    Button btnAddClass;
    //Button btnListClass;
    Button btnStudentManagement;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddClass = findViewById(R.id.btnAddClass);
        //btnListClass = findViewById(R.id.btnListClass);
        btnStudentManagement = findViewById(R.id.btnStudentManagement);
        btnAddClass.setOnClickListener(view -> {
            //TODO add event onclick
            intent = new Intent(Ph19127_MainActivity.this, AddClassActivity.class);
            startActivity(intent);
        });/*
        btnListClass.setOnClickListener(view -> {
            //TODO add event onclick
            intent = new Intent(Ph19127_MainActivity.this, ListClassActivity.class);
            startActivity(intent);
        });*/
        btnStudentManagement.setOnClickListener(view -> {
            //TODO add event onclick
            intent = new Intent(Ph19127_MainActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });
    }
}