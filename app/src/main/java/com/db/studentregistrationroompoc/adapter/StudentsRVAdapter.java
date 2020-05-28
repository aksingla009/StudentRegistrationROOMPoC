package com.db.studentregistrationroompoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.db.studentregistrationroompoc.MainActivity;
import com.db.studentregistrationroompoc.R;
import com.db.studentregistrationroompoc.room.entity.StudentModelEntity;

import java.util.ArrayList;

public class StudentsRVAdapter extends RecyclerView.Adapter<StudentsRVAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<StudentModelEntity> studentsList;
    private MainActivity mainActivityContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTV;
        private TextView emailTV;
        private TextView countryTV;
        private TextView timeTV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            countryTV = itemView.findViewById(R.id.countryTV);
            timeTV = itemView.findViewById(R.id.dateTV);

        }
    }

    public StudentsRVAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setStudentsList(ArrayList<StudentModelEntity> studentsList) {
        this.studentsList = studentsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final StudentModelEntity studentObj = studentsList.get(position);

        holder.nameTV.setText(studentObj.getStudentName());
        holder.emailTV.setText(studentObj.getStudentEmailID());
        holder.countryTV.setText(studentObj.getCountryName());
        holder.timeTV.setText(studentObj.getStudentRegisteredTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Position " + position + " Clicked", Toast.LENGTH_SHORT).show();
//                mainActivityContext.addEditStudent(true, studentObj, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (studentsList != null) {
            return studentsList.size();
        }else{
            return 0;
        }
    }


}
