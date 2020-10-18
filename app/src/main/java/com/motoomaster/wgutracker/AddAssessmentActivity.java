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

public class AddAssessmentActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView startDate,endDate;
    private String startDateStr,endDateStr;
    private Button startDateBtn,endDateBtn,add;
    private int year, month, day;
    private EditText title,objective,performance,notes;
    private String courseId,courseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        title = (EditText) findViewById(R.id.title);
        objective = (EditText) findViewById(R.id.objective);
        performance = (EditText) findViewById(R.id.performance);
        notes = (EditText) findViewById(R.id.notes);
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
        courseData = intent.getStringExtra("courseData");

        String[] termDateSplitted = courseData.split(",");

        setTitle("Add Course for "+termDateSplitted[1]);

        courseId = termDateSplitted[0];


        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddAssessmentActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddAssessmentActivity.this,
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
                if(AddAssessmentActivity.this.title.getText().toString().trim().equals("")){
                    Toast.makeText(AddAssessmentActivity.this,"Fill the fields first",Toast.LENGTH_LONG).show();
                }
                else{
                    DBHelper dbHelper = new DBHelper(AddAssessmentActivity.this);
                    dbHelper.addAssessment(AddAssessmentActivity.this.title.getText().toString().trim(),
                            AddAssessmentActivity.this.startDateStr,
                            AddAssessmentActivity.this.endDateStr,
                            AddAssessmentActivity.this.objective.getText().toString().trim(),
                            AddAssessmentActivity.this.performance.getText().toString().trim(),
                            AddAssessmentActivity.this.notes.getText().toString().trim(),
                            Integer.valueOf(courseId));
                    Toast.makeText(AddAssessmentActivity.this,"Assessment added successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddAssessmentActivity.this,AssessmentsActivity.class);
                    intent.putExtra("courseData",courseData);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}
