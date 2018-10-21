package com.example.matt_krebs.dnd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
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


    private Button newCharBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chargen);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference();

        DatabaseReference test = databaseReference;

        questions = findViewById(R.id.questionTextView);

        String textViewString = characterObject.getQuestion(characterObject.getCurrentQuestionIndex());
        questions.setText(textViewString);

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
                String textViewString = characterObject.getQuestion(characterObject.getCurrentQuestionIndex());
                characterObject.setCurrentQuestionIndex(characterObject.getCurrentQuestionIndex()+1);
                questions.setText(textViewString);
            }
        });
    }
}
