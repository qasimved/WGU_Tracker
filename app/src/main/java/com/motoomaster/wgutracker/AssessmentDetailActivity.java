package com.motoomaster.wgutracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AssessmentDetailActivity extends AppCompatActivity {
    private TextView title,startDate,endtDate,objective,performance,notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);

        title =(TextView) findViewById(R.id.title);
        startDate =(TextView) findViewById(R.id.startDate);
        endtDate =(TextView) findViewById(R.id.endtDate);
        objective =(TextView) findViewById(R.id.objective);
        performance =(TextView) findViewById(R.id.performance);
        notes =(TextView) findViewById(R.id.notes);

        Intent intent = getIntent();

        String[] assessmentDataSpplited = intent.getStringExtra("assessmentData").split(",");

        title.setText(assessmentDataSpplited[1]);
        startDate.setText(assessmentDataSpplited[2]);
        endtDate.setText(assessmentDataSpplited[3]);
        objective.setText(assessmentDataSpplited[4]);
        performance.setText(assessmentDataSpplited[5]);
        notes.setText(assessmentDataSpplited[6]);



    }
}
