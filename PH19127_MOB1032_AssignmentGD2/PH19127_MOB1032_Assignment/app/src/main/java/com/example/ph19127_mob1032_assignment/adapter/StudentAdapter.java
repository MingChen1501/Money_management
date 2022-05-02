package com.example.ph19127_mob1032_assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ph19127_mob1032_assignment.R;
import com.example.ph19127_mob1032_assignment.model.*;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student>  objects;
    private LayoutInflater inflater;
    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_student, null);
            holder.tvStudentID = (TextView) convertView.findViewById(R.id.tvStudentId);
            holder.tvStudentName = (TextView) convertView.findViewById(R.id.tvStudentName);
            holder.tvStudentClass = (TextView) convertView.findViewById(R.id.tvStudentClass);
            holder.tvStudentBirthDay = (TextView) convertView.findViewById(R.id.tvStudentBirthday);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student student = objects.get(position);
        holder.tvStudentID.setText(student.getId());
        holder.tvStudentName.setText(student.getTenSV());
        holder.tvStudentClass.setText(student.getLop());
        holder.tvStudentBirthDay.setText(student.getNgaySinh());
        return convertView;
    }
    public class ViewHolder {
        TextView tvStudentID, tvStudentName, tvStudentClass, tvStudentBirthDay;
    }
}
