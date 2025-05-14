package com.example.cultivationdiary_test2.Data.Database.Diary;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diary {

    @PrimaryKey(autoGenerate = true)
    public int diary_id;
    public String text;
    public String CreateDate;

    public Diary(String text, String CreateDate) {
        this.text = text;
        this.CreateDate = CreateDate;
    }

    public int getDiary_id() {return diary_id;}
    public void setDiary_id(int newId) {this.diary_id = newId;}
    public String getText() {return text;}
    public void setText(String newText) {this.text = newText;}
    public String getCreateDate() {return CreateDate;}
    public void setDate(String newDate) {this.CreateDate = newDate;}

}
