package com.example.ph19127_mob1032_assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ph19127_mob1032_assignment.R;
import com.example.ph19127_mob1032_assignment.DAO.*;
import com.example.ph19127_mob1032_assignment.model.*;
import com.example.ph19127_mob1032_assignment.adapter.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    private Spinner spnLop;
    private EditText txtMaSV, txtTenSV, txtBirthDay;
    private Button btnAddSV, btnUpdateSV, btnRemoveSV, btnClearFormSV;
    private ListView lvSV;

    //TODO chuyển list<T> locol sang global, còn list<Student>
    private List<ClassStudent> classList = new ArrayList<>();
    private List<String> className = new ArrayList<>();

    private StudentDAO studentDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_management);
        spnLop = findViewById(R.id.spnLop);

        txtMaSV = findViewById(R.id.txtMaSV);
        txtTenSV = findViewById(R.id.txtTenSV);
        txtBirthDay = findViewById(R.id.txtBirthday);

        lvSV = findViewById(R.id.lvSV);

        btnAddSV = findViewById(R.id.btnAddSV);
        btnUpdateSV = findViewById(R.id.btnUpdateSV);
        btnRemoveSV = findViewById(R.id.btnRemoveSV);
        btnClearFormSV = findViewById(R.id.btnClearFormSV);


        /*
        * xử lí đưa danh sách lớp từ DB lên spinner view
        * */
        studentDAO = new StudentDAO(this);
        classList = new ClassDAO(this).getAllClasses();

        className.clear();
        className.add("Chọn lớp");
        for(ClassStudent classStudent : classList) {
            String name = classStudent.getMaLop();
            className.add(name);
        }
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, className);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnLop.setAdapter(adapterSpinner);
        /*
        * dùng sự kiện text changed để xử lí khi nhập ngày sinh dd//MM/yyyy
        *
        * */
        txtBirthDay.addTextChangedListener(new TextWatcher() {
            private String s_dateCurrent = "";
            private String s_dateFormat = "DDMMYYYY";
            private Calendar myCalendar = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(s_dateCurrent)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = s_dateCurrent.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + s_dateFormat.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        myCalendar.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        myCalendar.set(Calendar.YEAR, year);

                        day = (day > myCalendar.getActualMaximum(Calendar.DATE))? myCalendar.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    s_dateCurrent = clean;
                    txtBirthDay.setText(s_dateCurrent);
                    txtBirthDay.setSelection(sel < s_dateCurrent.length() ? sel : s_dateCurrent.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


        /*
        *
        * dùng DatePickerDialog để chọn ngày sinh, nhưng không hợp lí
        * *//*
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCarlendar.set(Calendar.YEAR, year);
                myCarlendar.set(Calendar.MONTH, month);
                myCarlendar.set(Calendar.YEAR, dayOfMonth);
                String formatDate = "dd/MM/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatDate, Locale.US);
                txtBirthDay.setText(simpleDateFormat.format(myCarlendar.getTime()));
            }
        };
        txtBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddStudentActivity.this
                        ,date
                        ,myCarlendar.get(Calendar.YEAR)
                        ,myCarlendar.get(Calendar.MONTH)
                        ,myCarlendar.get(Calendar.DAY_OF_MONTH))
                            .show();
            }
        });
*/

        btnAddSV.setOnClickListener(view -> addSinhVien());
        btnUpdateSV.setOnClickListener(view -> updateSV());
        btnRemoveSV.setOnClickListener(view -> removeSV());
        btnClearFormSV.setOnClickListener(view -> clearFormSV());
        lvSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) lvSV.getItemAtPosition(position);
                txtMaSV.setText(student.getId());
                txtTenSV.setText(student.getTenSV());
                txtBirthDay.setText(student.getNgaySinh());
                List<Student> list = new ArrayList<>();
                list = studentDAO.getAllStudents();
                int indexOfClass = className.indexOf(list.get(list.indexOf(student)).getLop());
                spnLop.setSelection(indexOfClass);
            }
        });
        loadListView();
    }

    private void clearFormSV() {
        spnLop.setSelection(0);
        txtMaSV.setText("");
        txtTenSV.setText("");
        txtBirthDay.setText("");
    }

    private void removeSV() {
        if (studentDAO.deleteClass(txtMaSV.getText().toString()) <= 0) {
            Toast.makeText(getApplicationContext(), "xóa thông tin sinh viên thất bại"
                    , Toast.LENGTH_SHORT).show();
        } else {
            loadListView();
            clearFormSV();
            Toast.makeText(getApplicationContext(), "xóa thông tin sinh viên thành công"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSV() {
        try {
            if (spnLop.getSelectedItemPosition() == 0) throw new IllegalAccessException();
            Student student = new Student(txtTenSV.getText().toString(),
                    spnLop.getSelectedItem().toString(),
                    txtBirthDay.getText().toString(),
                    txtMaSV.getText().toString());
            if (studentDAO.updateClass(student, txtMaSV.getText().toString()) <= 0) {
                Toast.makeText(getApplicationContext(), "Sửa thông tin sinh viên thất bại"
                        , Toast.LENGTH_SHORT).show();
            } else {
                loadListView();
                clearFormSV();
                Toast.makeText(getApplicationContext(), "Sửa thông tin sinh viên thành công"
                        , Toast.LENGTH_SHORT).show();
            }
        } catch (IllegalAccessException e) {
            Toast.makeText(getApplicationContext(), "vui lòng chọn lớp"
                    , Toast.LENGTH_SHORT).show();
        }

    }

    private void addSinhVien() {
        //TODO replace try-catch
        if (spnLop.getSelectedItemPosition() != 0) {
            Student student = new Student(txtTenSV.getText().toString(),
                    spnLop.getSelectedItem().toString(),
                    txtBirthDay.getText().toString(),
                    txtMaSV.getText().toString());
            if (studentDAO.insertClass(student) <= 0) {
                Toast.makeText(getApplicationContext(), "Thêm sinh viên thất bại"
                        , Toast.LENGTH_SHORT).show();
            } else {
                loadListView();
                clearFormSV();
                Toast.makeText(getApplicationContext(), "Thêm sinh viên thành công"
                        , Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "vui lòng chọn lớp"
                    , Toast.LENGTH_SHORT).show();
        }
    }
    private void loadListView() {
        List<Student> list = new ArrayList<>();
        list = studentDAO.getAllStudents();
        for(Student s : list) {
            Log.println(Log.DEBUG,s.toString(),"abc");
        }
        StudentAdapter studentAdapter = new StudentAdapter(AddStudentActivity.this, R.layout.item_student, list);
        lvSV.setAdapter(studentAdapter);
    }
}