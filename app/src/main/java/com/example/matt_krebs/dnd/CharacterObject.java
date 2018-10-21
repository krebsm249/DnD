package com.example.matt_krebs.dnd;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CharacterObject {
    public String Name="";
    public String Race="";
    public int Age,Strength,Agility,Intelligence,Wisdom,Charisma,Constitution, currentQuestionIndex = 0;

    private String questionString = "1";

    CharacterObject() {}

    CharacterObject(String nm) {
        this.Name = nm;
    }

    public String getQuestion(int questionKey) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(questionKey));


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //raceArray.add(dataSnapshot.getValue(String.class));
               questionString= (dataSnapshot.getValue(String.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                questionString = "ERROR";
            }
        });
        return questionString;
    }

    public int getCurrentQuestionIndex() {return currentQuestionIndex;}
    public void setCurrentQuestionIndex(int index) {currentQuestionIndex=index;}
}
