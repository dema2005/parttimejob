package com.example.aparttimejobv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailET;
    private EditText userNameET;
    private EditText passwordET;
    private TextView submitTV;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailET = findViewById(R.id.email_signup);
        userNameET = findViewById(R.id.username_signup);
        passwordET = findViewById(R.id.passward_signup);
        submitTV = findViewById(R.id.submit);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String userName = userNameET.getText().toString();
                User user = new User(userName,password,email);
                Log.d("AAAAA",user.toString());
                databaseReference.child(user.getUserName()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Writing To DataBase","Done");
                        finish();
                    }
                });
            }
        });





    }
}