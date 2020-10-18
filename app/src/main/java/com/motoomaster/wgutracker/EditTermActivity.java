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

public class EditTermActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView startDate,endDate;
    private String startDateStr,endDateStr,termId,termData;
    private Button startDateBtn,endDateBtn,add;
    private int year, month, day;
    private EditText title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);
        setTitle("Edit Term");

        Intent intent = getIntent();

        title = (EditText) findViewById(R.id.title);

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


        String termData = intent.getStringExtra("termData");

        String[] termDataSplitted = termData.split(",");

        termId = termDataSplitted[0];

        title.setText(termDataSplitted[1]);
        startDate.setText(termDataSplitted[2]);
        endDate.setText(termDataSplitted[3]);


        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTermActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditTermActivity.this,
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

                    DBHelper dbHelper = new DBHelper(EditTermActivity.this);
                    dbHelper.updateTerm(title.getText().toString().trim(),startDateStr,endDateStr,termId);
                    Toast.makeText(EditTermActivity.this,"Term Edited successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditTermActivity.this,Home.class));
                    finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditTermActivity.this,Home.class));
        finish();
    }
}
