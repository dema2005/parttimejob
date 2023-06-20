package com.example.aparttimejobv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobsActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 823;

    private DatabaseReference databaseReference;
    private List<JobInfo> jobList;
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    private Button add;
    private SharedPreferences spf;

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        spf = getSharedPreferences("user.data",MODE_PRIVATE);
        userName = spf.getString("userName","");
        jobList = new ArrayList<JobInfo>();
        listView = findViewById(R.id.list_view);
        listViewAdapter = new ListViewAdapter(this,R.layout.job_item,jobList);
        listView.setAdapter(listViewAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference("Jobs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobList.clear();
                for( DataSnapshot user : snapshot.getChildren()){
                    for(DataSnapshot job : user.getChildren() ){
                        JobInfo jobInfo = job.getValue(JobInfo.class);
                        Log.d("AAAAAAAAA",jobInfo.toString());
                        jobList.add(jobInfo);
                    }
                }
                listViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add = findViewById(R.id.add_job);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JobsActivity.this,GetJobInfoActivity.class);
                startActivityForResult(i,REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String companyName = data.getStringExtra("companyName");
        String locationComapny = data.getStringExtra("location");
        String jobTitleReqiure = data.getStringExtra("jobReq");
        String jobDescription = data.getStringExtra("description");


        JobInfo jobInfo = new JobInfo(companyName,locationComapny,jobTitleReqiure,jobDescription);
        Log.d("UUUUU",jobInfo.toString()+" "+userName);
        databaseReference.child(userName).push().setValue(jobInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("AAAA","data updated");
            }
        });
    }
}