package com.example.matt_krebs.dnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.util.Log;

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
                //writeNewUser(databaseReference,"123","Matt","krebsmatt@ymail.com");

                //CharacterObject character = new CharacterObject("GUY");

                //databaseReference.child("newCharacter").setValue(character);

                DatabaseReference test = databaseReference;


                test = databaseReference.child("newCharacter");


                test.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CharacterObject characterObject = dataSnapshot.getValue(CharacterObject.class);
                        Log.e("Race",characterObject.Race);
                        helloTextView.setText(characterObject.Race);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
