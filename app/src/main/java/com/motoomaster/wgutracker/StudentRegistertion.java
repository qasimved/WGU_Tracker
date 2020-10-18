package com.motoomaster.wgutracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentRegistertion extends AppCompatActivity {
    public String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registertion);
        setTitle("Registration");



        final Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=((EditText)findViewById(R.id.name)).getText().toString();
                email=((EditText)findViewById(R.id.email)).getText().toString();

                if(name.trim().equals("")||email.trim().equals("")){

                    Toast.makeText(StudentRegistertion.this,"Fill the Fields First",Toast.LENGTH_LONG).show();
                }
                else{
                    SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance(StudentRegistertion.this);
                    sharedPreferencesManager.setStringVar("name",name);
                    sharedPreferencesManager.setStringVar("email",email);

                    DBHelper dbHelper = new DBHelper(StudentRegistertion.this);

                    startActivity(new Intent(StudentRegistertion.this,Home.class));
                    finish();
                }

            }
        });
    }
}
