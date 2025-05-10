package com.example.cultivationdiary_test2.Data.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diary {

    @PrimaryKey(autoGenerate = true)
    public int Diary_id;
    public String text;
    public String CreateDate;

    public Diary(String text, String CreateDate) {
        this.text = text;
        this.CreateDate = CreateDate;
    }

    public int getDiary_id() {return Diary_id;}
    public void setDiary_id(int newId) {this.Diary_id = newId;}
    public String getText() {return text;}
    public void setText(String newText) {this.text = newText;}
    public String getDate() {return CreateDate;}
    public void setDate(String newDate) {this.CreateDate = newDate;}

}
