package com.motoomaster.wgutracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class HomeFragment extends Fragment {
    public ListView allTermsListview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
        allTermsListview = (ListView) getView().findViewById(R.id.allTermsListview);


        updateTermListview();


        Button addNewTerm  = (Button) getView().findViewById(R.id.addNewTerm);
        addNewTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddNewTermActivity.class));
                getActivity().finish();
            }
        });
    }

    public void updateTermListview(){
        DBHelper dbHelper = new DBHelper(getContext());
        Cursor allTermCursor = dbHelper.getAllTerms();
        TermsCustomCursorAdapter todoAdapter = new TermsCustomCursorAdapter(getContext(), allTermCursor);
        allTermsListview.setAdapter(todoAdapter);

    }
}