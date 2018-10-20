package com.example.matt_krebs.dnd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class chargen extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<String> raceArray = new ArrayList<String>();

    //private String[] raceArray = new String[3];



    //private Spinner spinner = (Spinner) findViewById(R.id.spinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargen);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);

        DatabaseReference test = databaseReference;

        for (int x = 0;x<3;x ++) {
            test = databaseReference.child("Race").child(String.valueOf(x));


            test.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    raceArray.add(dataSnapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, raceArray);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adp1);
    }
}
