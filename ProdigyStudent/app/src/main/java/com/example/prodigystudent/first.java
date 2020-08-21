package com.example.prodigystudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class first extends AppCompatActivity {
Button studentmenu,privacy,tnc;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mAuth= FirebaseAuth.getInstance();
        studentmenu=findViewById(R.id.studentmenu);
        privacy=findViewById(R.id.privacy);
        tnc=findViewById(R.id.tnc);

        studentmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()==null){
                    Intent i=new Intent(getApplicationContext(),login.class);
                    startActivity(i);
                    //finish();
                }
                else {
                    Intent i=new Intent(getApplicationContext(),profile_student.class);
                    startActivity(i);
                    //finish();
                }
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),privacy.class);
                startActivity(intent);
            }
        });

        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),tnc.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(first.this).setIcon(R.drawable.ic_launcher_background)
                .setMessage("Do You Really Want To Close The App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",null).show();
    }
}
