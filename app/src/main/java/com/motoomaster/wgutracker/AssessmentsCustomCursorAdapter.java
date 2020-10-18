package com.motoomaster.wgutracker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AssessmentsCustomCursorAdapter extends CursorAdapter {
    public Context context;

    public AssessmentsCustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.assessments_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextview = (TextView) view.findViewById(R.id.title);
        TextView startDateTextview = (TextView) view.findViewById(R.id.startDate);
        TextView endDateTextview = (TextView) view.findViewById(R.id.endDate);
        TextView objectiveTextview = (TextView) view.findViewById(R.id.objective);
        TextView performanceTextview = (TextView) view.findViewById(R.id.performance);
        TextView notesTextview = (TextView) view.findViewById(R.id.notes);
        CardView cardView = (CardView)  view.findViewById(R.id.assessmentCardview);
        titleTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("assessmentTitle")));
        startDateTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("startDate")));
        endDateTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("endDate")));
        notesTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("notes")));
        performanceTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("performance")));
        objectiveTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("objective")));
        cardView.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("assessmentTitle"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("startDate"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("endDate"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("objective"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("performance"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("notes")));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentsCustomCursorAdapter.this.context,AssessmentDetailActivity.class);
                intent.putExtra("assessmentData",v.getTag().toString());
                AssessmentsCustomCursorAdapter.this.context.startActivity(intent);

            }
        });
    }
}

