package com.example.matt_krebs.dnd;

public class CharacterObject {
    public String Name="";
    public String Race="";
    public int Age,Strength,Agility,Intelligence,Wisdom,Charisma,Constitution = 0;

    CharacterObject() {}

    CharacterObject(String nm) {
        this.Name = nm;
    }
}
