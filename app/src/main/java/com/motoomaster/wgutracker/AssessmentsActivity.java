package com.motoomaster.wgutracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AssessmentsActivity extends AppCompatActivity {
    private TextView startDateCourse,endDateCourse,status,mentorName,mentorPhoneNo,mentorEmail;
    public ListView allAssessmentListview;
    private String courseId,courseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);

        startDateCourse = (TextView) findViewById(R.id.startDate);
        endDateCourse = (TextView) findViewById(R.id.endDate);
        status = (TextView) findViewById(R.id.status);
        mentorName = (TextView) findViewById(R.id.mentorName);
        mentorPhoneNo = (TextView) findViewById(R.id.mentorPhoneNo);
        mentorEmail = (TextView) findViewById(R.id.mentorEmail);

        allAssessmentListview = (ListView) findViewById(R.id.allAssessmentListview);

        Intent intent = getIntent();
        courseData = intent.getStringExtra("courseData");

        String[] courseDataSplitted = courseData.split(",");


        startDateCourse.setText(courseDataSplitted[2]);
        endDateCourse.setText(courseDataSplitted[3]);
        status.setText(courseDataSplitted[4]);
        mentorName.setText(courseDataSplitted[5]);
        mentorPhoneNo.setText(courseDataSplitted[6]);
        mentorEmail.setText(courseDataSplitted[7]);

        courseId = courseDataSplitted[0];

        setTitle("Assessments for "+courseDataSplitted[1]);


        updateAssessmentListview();


        Button addNewCourse  = (Button) findViewById(R.id.addNewAssessment);
        addNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AssessmentsActivity.this,"Clicked",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AssessmentsActivity.this,AddAssessmentActivity.class);
                intent.putExtra("courseData",courseData);
                startActivity(intent);
                finish();
            }
        });

        Button editTerm  = (Button) findViewById(R.id.edit);
        editTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentsActivity.this,EditCourseActivity.class);
                intent.putExtra("courseData",courseData);
                startActivity(intent);
                finish();
            }
        });

        Button delete  = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(AssessmentsActivity.this)
                        .setTitle("Really?")
                        .setMessage("Are you sure you want to Delete? Course will be deleted")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {

                                DBHelper dbHelper = new DBHelper(AssessmentsActivity.this);
                                dbHelper.deleteCourse(courseId);

                            }
                        }).create().show();



            }
        });

    }

    private void updateAssessmentListview() {
        DBHelper dbHelper = new DBHelper(this);
        Cursor allCoursesCursor = dbHelper.getALLAssessmentsOfCourse(courseId);
        AssessmentsCustomCursorAdapter todoAdapter = new AssessmentsCustomCursorAdapter(this, allCoursesCursor);
        allAssessmentListview.setAdapter(todoAdapter);
    }
}
