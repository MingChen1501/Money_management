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

public class ClassAdapter extends ArrayAdapter<ClassStudent> {




    private Context context;
    private int resource;
    private List<ClassStudent>  objects;
    private LayoutInflater inflater;
    public ClassAdapter(@NonNull Context context, int resource, @NonNull List<ClassStudent> objects) {
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
            convertView = inflater.inflate(R.layout.item_class_student, null);
            holder.tvClassID = (TextView) convertView.findViewById(R.id.tvClassId);
            holder.tvClassName = (TextView) convertView.findViewById(R.id.tvClassName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ClassStudent classStudent = objects.get(position);
        holder.tvClassID.setText(classStudent.getMaLop());
        holder.tvClassName.setText(classStudent.getTenLop());
        return convertView;
    }
    public class ViewHolder {
        TextView tvClassID, tvClassName;
    }
}
