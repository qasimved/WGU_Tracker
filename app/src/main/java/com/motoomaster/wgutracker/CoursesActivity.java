package com.motoomaster.wgutracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity {
    private TextView startDateTerm,endDateTerm;
    public ListView allCoursesListview;
    private String termId,termData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        allCoursesListview = (ListView) findViewById(R.id.coursesListview);

        startDateTerm = (TextView) findViewById(R.id.startDate);
        endDateTerm = (TextView) findViewById(R.id.endDate);

        Intent intent = getIntent();
         termData = intent.getStringExtra("termData");

        String[] termDateSplitted = termData.split(",");
        startDateTerm.setText(termDateSplitted[2]);
        endDateTerm.setText(termDateSplitted[3]);

        setTitle(termDateSplitted[1]);

        termId = termDateSplitted[0];


        updateCourseListview();




        Button addNewCourse  = (Button) findViewById(R.id.addNewCourse);
        addNewCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoursesActivity.this,AddNewCourseActivity.class);
                intent.putExtra("termData",termData);
                startActivity(intent);
                finish();
            }
        });

        Button editTerm  = (Button) findViewById(R.id.edit);
        editTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoursesActivity.this,EditTermActivity.class);
                intent.putExtra("termData",termData);
                startActivity(intent);
                finish();
            }
        });


        Button delete  = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(CoursesActivity.this)
                        .setTitle("Really?")
                        .setMessage("Are you sure you want to Delete? Course will be deleted")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                DBHelper dbHelper = new DBHelper(CoursesActivity.this);
                                dbHelper.deleteTerm(termId);

                            }
                        }).create().show();



            }
        });
    }

    private void updateCourseListview() {
        DBHelper dbHelper = new DBHelper(this);
        Cursor allCoursesCursor = dbHelper.getALLCoursesOfTerm(termId);
        CoursesCustomCursorAdapter todoAdapter = new CoursesCustomCursorAdapter(this, allCoursesCursor);
        allCoursesListview.setAdapter(todoAdapter);
    }
}
