package com.example.matt_krebs.dnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        final Button button = findViewById(R.id.runBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newStr = "TEST";

                final TextView helloTextView = (TextView) findViewById(R.id.textField);
                helloTextView.setText(newStr);

                databaseReference.setValue(newStr);
            }
        });


    }
}