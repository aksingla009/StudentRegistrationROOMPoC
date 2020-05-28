package com.db.studentregistrationroompoc.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.db.studentregistrationroompoc.room.dao.StudentDAO;
import com.db.studentregistrationroompoc.room.entity.StudentModelEntity;

@Database(entities = {StudentModelEntity.class}, version = 1)
public abstract class StudentDB extends RoomDatabase {

    public abstract StudentDAO getStudentDAO();
}
