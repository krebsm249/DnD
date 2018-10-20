package com.example.matt_krebs.dnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;

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
                String newStr = "BALLS MATT KULLING";

                final TextView helloTextView = (TextView) findViewById(R.id.textField);
                helloTextView.setText(newStr);
                //databaseReference.setValue(newStr);
                writeNewUser(databaseReference,"123","Matt","krebsmatt@ymail.com");
            }
        });

        final Button newCharBtn = findViewById(R.id.newCharacterBtn);
        newCharBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, chargen.class));
                Intent intent = new Intent (MainActivity.this, chargen.class);
                startActivity(intent);
            }
        });
    }


    private void writeNewUser(DatabaseReference ref, String userId, String name, String email) {
        User user = new User(name, email);

        ref.child("users").child(userId).setValue(user);
    }
}
