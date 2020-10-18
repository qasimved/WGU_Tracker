package com.motoomaster.wgutracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WGUTracker.db";
    private static final int DATABASE_VERSION = 1;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TERM = "CREATE TABLE Term (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                                    "termTitle TEXT, " +
                                                                    "startDate TEXT, " +
                                                                    "endDate TEXT)";

        String CREATE_TABLE_COURSE = "CREATE TABLE Courses (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "courseTitle TEXT, " +
                "startDate TEXT, " +
                "endDate TEXT, " +
                "status TEXT, " +
                "mentorName TEXT, " +
                "mentorPhoneNo TEXT, " +
                "mentorEmail TEXT, " +
                "mentorAddress TEXT, " +
                "termId INTEGER)";

        String CREATE_TABLE_ASSESSMENTS = "CREATE TABLE Assessments (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "assessmentTitle TEXT, " +
                "startDate TEXT, " +
                "endDate TEXT, " +
                "objective TEXT, " +
                "performance TEXT, " +
                "notes TEXT, " +
                "courseId INTEGER)";

        db.execSQL(CREATE_TABLE_TERM);
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_ASSESSMENTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Term");
        db.execSQL("DROP TABLE IF EXISTS Courses");
        db.execSQL("DROP TABLE IF EXISTS Assessments");
        onCreate(db);

    }


    public boolean addTerm(String termTitle, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("termTitle", termTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        db.insert("Term", null, contentValues);
        return true;
    }

    public boolean addCourse(String courseTitle, String startDate, String endDate, String status, String mentorName, String mentorPhoneNo, String mentorEmail, String mentorAddress, int termId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseTitle", courseTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        contentValues.put("status", status);
        contentValues.put("mentorName", mentorName);
        contentValues.put("mentorPhoneNo", mentorPhoneNo);
        contentValues.put("mentorEmail", mentorEmail);
        contentValues.put("mentorAddress", mentorAddress);
        contentValues.put("termId", termId);
        db.insert("Courses", null, contentValues);
        return true;
    }

    public boolean addAssessment(String assessmentTitle, String startDate, String endDate, String objective, String performance, String notes, int courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("assessmentTitle", assessmentTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        contentValues.put("objective", objective);
        contentValues.put("performance", performance);
        contentValues.put("notes", notes);
        contentValues.put("courseId", courseId);
        db.insert("Assessments", null, contentValues);
        return true;
    }

    public int numberOfRows(String databaseTable) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, databaseTable);
        return numRows;
    }

    public Cursor getAllTerms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM Term", null );
        return res;
    }

    public Cursor getALLCoursesOfTerm(String termId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM Courses WHERE termId =?", new String[] {termId});
        return res;
    }

    public Cursor getALLAssessmentsOfCourse(String courseId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM Assessments WHERE courseId =?", new String[] {courseId});
        return res;
    }

    public void updateTerm(String termTitle, String startDate, String endDate, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("termTitle", termTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        db.update("Term", contentValues, "_id="+id, null);

    }
    public void updateCourse(String courseTitle, String startDate, String endDate, String status, String mentorName, String mentorPhoneNo, String mentorEmail, String mentorAddress, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseTitle", courseTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("endDate", endDate);
        contentValues.put("status", status);
        contentValues.put("mentorName", mentorName);
        contentValues.put("mentorPhoneNo", mentorPhoneNo);
        contentValues.put("mentorEmail", mentorEmail);
        contentValues.put("mentorAddress", mentorAddress);
        db.update("Courses", contentValues, "_id="+id, null);

    }

    public void deleteTerm(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Term", "_id" + "=" + id, null);

    }

    public void deleteCourse(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Courses", "_id" + "=" + id, null);

    }

}
