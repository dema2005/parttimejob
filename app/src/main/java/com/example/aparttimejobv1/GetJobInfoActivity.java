package com.example.aparttimejobv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetJobInfoActivity extends AppCompatActivity {
    private EditText companyNameET;
    private EditText titleET;
    private EditText locationET;
    private EditText descriptionET;

    private Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_job_info);


        companyNameET = findViewById(R.id.comp_name_get);
        titleET = findViewById(R.id.title_get);
        locationET = findViewById(R.id.location_get);
        descriptionET = findViewById(R.id.description_get);
        okBtn = findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName = companyNameET.getText().toString();
                String locationComapny = locationET.getText().toString();
                String jobTitleReqiure = titleET.getText().toString();
                String jobDescription = descriptionET.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("companyName",companyName);
                intent.putExtra("location",locationComapny);
                intent.putExtra("jobReq",jobTitleReqiure);
                intent.putExtra("description",jobDescription);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }
}