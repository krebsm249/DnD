package com.example.matt_krebs.dnd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Dictionary;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class chargen extends AppCompatActivity{

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<String> raceArray = new ArrayList<String>();

    private int currentQuestionIndex=0;

    CharacterObject characterObject = new CharacterObject();
    TextView questions;

    private Dictionary<String,String> questionDict = new Hashtable<String, String>();

    private Button newCharBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargen);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        DatabaseReference test = databaseReference;

        questions = findViewById(R.id.questionTextView);

        questions.setText("What is your character name?");

        getQuestions();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,raceArray);
        MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner)findViewById(R.id.android_material_design_spinner);
        betterSpinner.setAdapter(adapter);

        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Test"," selected spinner "+ raceArray.get(position));
            }
        });

        newCharBtn = findViewById(R.id.nextBtn);
        newCharBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String textViewString = questionDict.get(Integer.toString(characterObject.getCurrentQuestionIndex()));
                characterObject.setCurrentQuestionIndex(characterObject.getCurrentQuestionIndex()+1);
                questions.setText(textViewString);
            }
        });
    }

    private void updateTextView(String newText) {
        questions.setText(newText);
    }

    private void getQuestions(){
        for (int x = 0;x<3;x++) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(x));

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //raceArray.add(dataSnapshot.getValue(String.class));
                    questionDict.put(dataSnapshot.getKey(),dataSnapshot.getValue(String.class).toString());
                    Log.e(dataSnapshot.getKey(),dataSnapshot.getValue(String.class).toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
