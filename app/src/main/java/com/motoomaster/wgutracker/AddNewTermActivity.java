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

public class AddNewTermActivity extends AppCompatActivity {
    private Calendar calendar;
    private TextView startDate,endDate;
    private String startDateStr,endDateStr;
    private Button startDateBtn,endDateBtn,add;
    private int year, month, day;
    private EditText title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_term);
        setTitle("Add New Term");

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

        startDate.setText(startDateStr);
        endDate.setText(endDateStr);


        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewTermActivity.this,
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewTermActivity.this,
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
                if(AddNewTermActivity.this.title.getText().toString().trim().equals("")){
                    Toast.makeText(AddNewTermActivity.this,"Add Term Title first",Toast.LENGTH_LONG).show();
                }
                else{
                    DBHelper dbHelper = new DBHelper(AddNewTermActivity.this);
                    dbHelper.addTerm(title.getText().toString().trim(),startDateStr,endDateStr);
                    Toast.makeText(AddNewTermActivity.this,"Term added successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(AddNewTermActivity.this,Home.class));
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddNewTermActivity.this,Home.class));
        finish();
    }
}
