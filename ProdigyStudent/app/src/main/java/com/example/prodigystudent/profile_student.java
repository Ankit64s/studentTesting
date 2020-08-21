package com.example.prodigystudent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class profile_student extends AppCompatActivity {
Button studentlogout,viewprofile,homework;
Button attendance,viewnotices;
Button viewpdfs,viewimages,viewlinks;
    private FirebaseAuth mAuth;
    String passed,passedid,passedname;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student);
        mAuth=FirebaseAuth.getInstance();
        passed = getIntent().getStringExtra("mobile");
        passedid=getIntent().getStringExtra("id");
        passedname=getIntent().getStringExtra("name");


        String var=getstringdata();
        //sharedPreferences.getString("username","");

        welcome=findViewById(R.id.text1);
        welcome.setText(welcome.getText().toString()+" "+var);
        studentlogout=findViewById(R.id.studentlogout);
        viewprofile=findViewById(R.id.viewprofile);
        homework=findViewById(R.id.homework);
        attendance=findViewById(R.id.attendance);
        viewnotices=findViewById(R.id.viewnotices);
        viewpdfs=findViewById(R.id.viewpdfs);
        viewimages=findViewById(R.id.viewimages);
        viewlinks=findViewById(R.id.viewlinks);

        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,Personal_Profile.class);
                startActivity(i);
            }
        });

        homework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,homework.class);
                startActivity(i);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,attendance.class);
                startActivity(i);
            }
        });

        viewnotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,notices.class);
                startActivity(i);
            }
        });

        viewpdfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,viewpdf.class);
                startActivity(i);
            }
        });

        viewimages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,viewimage.class);
                startActivity(i);
            }
        });

        viewlinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(profile_student.this,viewlink.class);
                startActivity(i);
            }
        });

        studentlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                Intent i=new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
    }
    private String getstringdata() {
        String var1;
        SharedPreferences sharedPreferences=getSharedPreferences("application", Context.MODE_PRIVATE);
        var1=sharedPreferences.getString("Name","");
        //Toast.makeText(this, var1, Toast.LENGTH_SHORT).show();
        return var1;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),first.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
