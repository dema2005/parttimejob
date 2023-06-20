package com.example.aparttimejobv1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LoginActivity";
    private static final int REQUEST_CODE = 8745;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private TextView signUpTextView;
    private TextView signInTextView;
    private DatabaseReference databaseReference;
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        userNameEditText = findViewById(R.id.user_name_login);
        passwordEditText = findViewById((R.id.pwd_login));
        signUpTextView = findViewById(R.id.signup_login);
        signInTextView = findViewById(R.id.signin_login);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        spf = getSharedPreferences("user.data",MODE_PRIVATE);
        String userName = spf.getString("userName",null);
        if( userName !=null ){
            goToJobsActivity();
        }
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameEditText.getText().toString();
                String pwd = passwordEditText.getText().toString();
                databaseReference.child(userName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        DataSnapshot dataSnapshot = task.getResult();
                        if( dataSnapshot.exists()){
                            User user = dataSnapshot.getValue(User.class);
                            if(pwd.equals(user.getPassword())){
                                SharedPreferences.Editor editor = spf.edit();
                                editor.putString("userName",userName);
                                editor.commit();
                                goToJobsActivity();
                            }else {
                                Toast.makeText(LoginActivity.this,"Password Error",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"User Name doesn't Exists",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE ){

        }

    }
    public void goToJobsActivity(){
        Intent i = new Intent(this,JobsActivity.class);
        startActivity(i);
    }
}