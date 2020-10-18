package com.motoomaster.wgutracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewCourseActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView startDate,endDate;
    private String startDateStr,endDateStr;
    private Button startDateBtn,endDateBtn,add;
    private int year, month, day;
    private EditText title,status,mentorName,mentorPhoneNo,mentorEmail,mentorAddress;
    private String termId,termData;

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

        startDate.setText(startDateStr);
        endDate.setText(endDateStr);

        Intent intent = getIntent();
        termData = intent.getStringExtra("termData");

        String[] termDateSplitted = termData.split(",");

        setTitle("Add Course for "+termDateSplitted[1]);

        termId = termDateSplitted[0];


        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewCourseActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewCourseActivity.this,
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
                if(AddNewCourseActivity.this.title.getText().toString().trim().equals("")){
                    Toast.makeText(AddNewCourseActivity.this,"Fill the fields first",Toast.LENGTH_LONG).show();
                }
                else{
                    DBHelper dbHelper = new DBHelper(AddNewCourseActivity.this);
                    dbHelper.addCourse(AddNewCourseActivity.this.title.getText().toString().trim(),
                            AddNewCourseActivity.this.startDateStr,
                            AddNewCourseActivity.this.endDateStr,
                            AddNewCourseActivity.this.status.getText().toString().trim(),
                            AddNewCourseActivity.this.mentorName.getText().toString().trim(),
                            AddNewCourseActivity.this.mentorPhoneNo.getText().toString().trim(),
                            AddNewCourseActivity.this.mentorEmail.getText().toString().trim(),
                            AddNewCourseActivity.this.mentorAddress.getText().toString().trim(),
                            Integer.valueOf(termId));
                    Toast.makeText(AddNewCourseActivity.this,"Course added successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddNewCourseActivity.this,CoursesActivity.class);
                    intent.putExtra("termData",termData);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}
