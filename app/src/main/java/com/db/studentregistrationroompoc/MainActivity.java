package com.db.studentregistrationroompoc;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.db.studentregistrationroompoc.adapter.StudentsRVAdapter;
import com.db.studentregistrationroompoc.room.StudentDB;
import com.db.studentregistrationroompoc.room.entity.StudentModelEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StudentDB myDB;
    private ArrayList<StudentModelEntity> students;
    private StudentsRVAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.registeredStudentsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        studentAdapter = new StudentsRVAdapter(this);
        recyclerView.setAdapter(studentAdapter);


        myDB = Room.databaseBuilder(getApplicationContext(),StudentDB.class,"student_db").allowMainThreadQueries().build();

        loadData();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                StudentModelEntity studentToDlete = students.get(viewHolder.getAdapterPosition());
                deleteStudent(studentToDlete);

            }
        }).attachToRecyclerView(recyclerView);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivityForResult(intent,1);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                String name = data.getStringExtra("NAME");
                String email = data.getStringExtra("EMAIL");
                String country = data.getStringExtra("COUNTRY");
                String time = data.getStringExtra("TIME");

                StudentModelEntity studentObj = new StudentModelEntity();
                studentObj.setStudentName(name);
                studentObj.setStudentEmailID(email);
                studentObj.setCountryName(country);
                studentObj.setStudentRegisteredTime(time);

                addStudent(studentObj);

            }
        }
    }

    private void loadData() {
        new GetAllStudentsAsyncTask().execute();

    }

    private class GetAllStudentsAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            students = (ArrayList<StudentModelEntity>) myDB.getStudentDAO().getAllStudents();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            studentAdapter.setStudentsList(students);
        }
    }


    private class DeleteStudentAsyncTask extends AsyncTask<StudentModelEntity,Void,Void>{

        @Override
        protected Void doInBackground(StudentModelEntity... studentModelEntities) {
            myDB.getStudentDAO().deleteStudent(studentModelEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    private void deleteStudent(StudentModelEntity student){
        new DeleteStudentAsyncTask().execute(student);
    }


    private class AddStudentAsyncTask extends AsyncTask<StudentModelEntity,Void,Void>{

        @Override
        protected Void doInBackground(StudentModelEntity... studentModelEntities) {
            myDB.getStudentDAO().addStudent(studentModelEntities[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    private void addStudent(StudentModelEntity student){
        new AddStudentAsyncTask().execute(student);
    }

    /*public void addEditStudent(boolean isupdate,StudentModelEntity studentObj, int position){


    }*/

}
