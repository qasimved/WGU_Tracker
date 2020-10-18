package com.motoomaster.wgutracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditCourseActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView startDate,endDate;
    private String startDateStr,endDateStr;
    private Button startDateBtn,endDateBtn,add;
    private int year, month, day;
    private EditText title,status,mentorName,mentorPhoneNo,mentorEmail,mentorAddress;
    private String termId,courseId,courseData,termData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);
        title = (EditText) findViewById(R.id.title);
        status = (EditText) findViewById(R.id.status);
        mentorName = (EditText) findViewById(R.id.mentorName);
        mentorPhoneNo = (EditText) findViewById(R.id.mentorPhoneNo);
        mentorEmail = (EditText) findViewById(R.id.mentorEmail);
        mentorAddress = (EditText) findViewById(R.id.mentorAddress);
        startDate = (TextView) findViewById(R.id.startDate);
        endDate = (TextView) findViewById(R.id.endDate);
        startDateBtn = (Button) findViewById(R.id.startDateBtn);
        endDateBtn = (Button) findViewById(R.id.endDateBtn);
        add = (Button) findViewById(R.id.add);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month++;

        startDateStr = day+"/"+month+"/"+year;
        endDateStr = day+"/"+month+"/"+year;


        Intent intent = getIntent();
        courseData = intent.getStringExtra("courseData");
//        termData = intent.getStringExtra("courseData");


        String[] courseDateSplitted = courseData.split(",");
//        String[] termDateSplitted = termData.split(",");
//        termId = termDateSplitted[1];


        setTitle("Edit Course");


        title.setText(courseDateSplitted[1]);
        status.setText(courseDateSplitted[4]);
        mentorName.setText(courseDateSplitted[5]);
        mentorPhoneNo.setText(courseDateSplitted[6]);
        mentorEmail.setText(courseDateSplitted[7]);
        startDate.setText(courseDateSplitted[2]);
        endDate.setText(courseDateSplitted[3]);
        mentorAddress.setText(courseDateSplitted[8]);

        courseId = courseDateSplitted[0];


        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditCourseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {


                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                monthOfYear++;
                                startDateStr =  dayOfMonth+"/"+monthOfYear+"/"+year;
                                startDate.setText(startDateStr);

                            }
                        }, year, month, day);
                datePickerDialog.show();




            }
        });

        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditCourseActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                monthOfYear++;
                                endDateStr =  dayOfMonth+"/"+monthOfYear+"/"+year;
                                endDate.setText(endDateStr);

                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    DBHelper dbHelper = new DBHelper(EditCourseActivity.this);
                    dbHelper.updateCourse(EditCourseActivity.this.title.getText().toString().trim(),
                            EditCourseActivity.this.startDateStr,
                            EditCourseActivity.this.endDateStr,
                            EditCourseActivity.this.status.getText().toString().trim(),
                            EditCourseActivity.this.mentorName.getText().toString().trim(),
                            EditCourseActivity.this.mentorPhoneNo.getText().toString().trim(),
                            EditCourseActivity.this.mentorEmail.getText().toString().trim(),
                            EditCourseActivity.this.mentorAddress.getText().toString().trim(),
                            courseId);
                    Toast.makeText(EditCourseActivity.this,"Course edited successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditCourseActivity.this,Home.class);
                    startActivity(intent);
                    finish();

            }
        });



    }
}
