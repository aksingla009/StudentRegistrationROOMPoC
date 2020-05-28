package com.db.studentregistrationroompoc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddStudentActivity extends AppCompatActivity {

    private String name;
    private String email;
    private String country;
    private String timeStamp;

    private EditText nameET, emailET, countryET;

    private Intent intent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_registration_laayout);

        intent = getIntent();


        Button submitBtn = findViewById(R.id.submitBtn);
        nameET = findViewById(R.id.nameEt);
        emailET = findViewById(R.id.emailEt);
        countryET = findViewById(R.id.countryEt);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });


    }

    private void submitData() {

        if (TextUtils.isEmpty(nameET.getText())) {
            Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
        } else {
            name = nameET.getText().toString().trim();
            email = emailET.getText().toString().trim();
            country = countryET.getText().toString().trim();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM YYYY");
            timeStamp = simpleDateFormat.format(new Date());


            intent.putExtra("NAME", name);
            intent.putExtra("EMAIL", email);
            intent.putExtra("COUNTRY", country);
            intent.putExtra("TIME", timeStamp);

            setResult(RESULT_OK, intent);
            finish();
        }

    }
}
