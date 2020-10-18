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

public class TermsCustomCursorAdapter extends CursorAdapter {
    public Context context;

    public TermsCustomCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.terms_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView startDate = (TextView) view.findViewById(R.id.startDate);
        TextView endDate = (TextView) view.findViewById(R.id.endDate);
        CardView cardView = (CardView)  view.findViewById(R.id.termCardview);
        title.setText(cursor.getString(cursor.getColumnIndexOrThrow("termTitle")));
        startDate.setText(cursor.getString(cursor.getColumnIndexOrThrow("startDate")));
        endDate.setText(cursor.getString(cursor.getColumnIndexOrThrow("endDate")));
        cardView.setTag(cursor.getString(cursor.getColumnIndexOrThrow("_id"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("termTitle"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("startDate"))
                +","+cursor.getString(cursor.getColumnIndexOrThrow("endDate")));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TermsCustomCursorAdapter.this.context,CoursesActivity.class);
                intent.putExtra("termData",v.getTag().toString());
                TermsCustomCursorAdapter.this.context.startActivity(intent);
            }
        });
    }
}
