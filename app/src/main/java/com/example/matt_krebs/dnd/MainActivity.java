package com.example.matt_krebs.dnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;

import java.util.List;
import java.util.ArrayList;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String val= "";

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
                //helloTextView.setText(newStr);
                //databaseReference.setValue(newStr);
                writeNewUser(databaseReference,"123","Matt","krebsmatt@ymail.com");

               //helloTextView.setText(databaseReference.child("users").child("123").child("name").getKey());

                List<String> arr = new ArrayList<String>();

                arr.add("Test1");
                arr.add("Test2");
                arr.add("Test3");

                databaseReference.child("test").setValue(arr);

                DatabaseReference test = databaseReference;

                for (int x = 0;x<3;x ++) {
                    test = databaseReference.child("test").child(String.valueOf(x));
                }

                test.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        val += dataSnapshot.getValue(String.class);
                        helloTextView.setText(val);
                        val += ", ";
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                helloTextView.setText(val);
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
