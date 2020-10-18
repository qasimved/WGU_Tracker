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
import android.widget.Toast;

public class CoursesCustomCursorAdapter extends CursorAdapter {
    public Context context;

    public CoursesCustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.courses_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextview = (TextView) view.findViewById(R.id.title);
        TextView startDateTextview = (TextView) view.findViewById(R.id.startDate);
        TextView endDateTextview = (TextView) view.findViewById(R.id.endDate);
        TextView mentorNameTextview = (TextView) view.findViewById(R.id.mentorName);
        CardView cardView = (CardView)  view.findViewById(R.id.courseCardview);
        titleTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("courseTitle")));
        startDateTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("startDate")));
        endDateTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("endDate")));
        mentorNameTextview.setText(cursor.getString(cursor.getColumnIndexOrThrow("mentorName")));
        String courseTitle = cursor.getString(cursor.getColumnIndexOrThrow("courseTitle"));
        String startDate = cursor.getString(cursor.getColumnIndexOrThrow("startDate"));
        String endDate = cursor.getString(cursor.getColumnIndexOrThrow("endDate"));
        String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
        String mentorName = cursor.getString(cursor.getColumnIndexOrThrow("mentorName"));
        String mentorPhoneNo = cursor.getString(cursor.getColumnIndexOrThrow("mentorPhoneNo"));
        String mentorEmail = cursor.getString(cursor.getColumnIndexOrThrow("mentorEmail"));
        String mentorAddress = cursor.getString(cursor.getColumnIndexOrThrow("mentorAddress"));
        cardView.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id"))
                +","+courseTitle+","+startDate+","+endDate+","+status+","+mentorName+","+mentorPhoneNo+","+mentorEmail+","+mentorAddress);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoursesCustomCursorAdapter.this.context,"id "+v.getTag().toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CoursesCustomCursorAdapter.this.context,AssessmentsActivity.class);
                intent.putExtra("courseData",v.getTag().toString());
                CoursesCustomCursorAdapter.this.context.startActivity(intent);
            }
        });
    }
}
