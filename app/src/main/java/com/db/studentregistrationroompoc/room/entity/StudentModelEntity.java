package com.db.studentregistrationroompoc.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentModelEntity {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int studentID;

    @ColumnInfo(name = "name")
    private String studentName;

    @ColumnInfo(name = "emailID")
    private String studentEmailID;

    @ColumnInfo(name = "countryName")
    private String countryName;

    @ColumnInfo(name = "registeredTime")
    private String studentRegisteredTime;


    @Ignore
    public StudentModelEntity() {
    }

    public StudentModelEntity(int studentID, String studentName, String studentEmailID, String countryName, String studentRegisteredTime) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmailID = studentEmailID;
        this.countryName = countryName;
        this.studentRegisteredTime = studentRegisteredTime;
    }


    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmailID() {
        return studentEmailID;
    }

    public void setStudentEmailID(String studentEmailID) {
        this.studentEmailID = studentEmailID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStudentRegisteredTime() {
        return studentRegisteredTime;
    }

    public void setStudentRegisteredTime(String studentRegisteredTime) {
        this.studentRegisteredTime = studentRegisteredTime;
    }
}
