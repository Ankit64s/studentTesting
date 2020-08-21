package com.example.prodigystudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewlink extends AppCompatActivity {
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<link_upload> uploadList;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlink);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList = new ArrayList<>();

        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(link_constants.DATABASE_PATH_UPLOADS);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    link_upload upload = postSnapshot.getValue(link_upload.class);
                    uploadList.add(upload);
                }

                linkAdapter adapter=new linkAdapter(viewlink.this,uploadList);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(viewlink.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
