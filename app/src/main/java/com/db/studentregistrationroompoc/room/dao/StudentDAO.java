package com.db.studentregistrationroompoc.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.db.studentregistrationroompoc.room.entity.StudentModelEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    public void addStudent(StudentModelEntity studentModelEntity);

    @Delete
    public void deleteStudent(StudentModelEntity studentModelEntity);

    @Update
    public void updateStudentDetails(StudentModelEntity studentModelEntity);

    @Query("select * from student_table")
    public List<StudentModelEntity> getAllStudents();

    @Query("select * from student_table where id ==:id")
    public StudentModelEntity getParticularStudent(long id);
}
