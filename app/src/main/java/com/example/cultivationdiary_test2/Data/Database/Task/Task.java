package com.example.cultivationdiary_test2.Data.Database.Task;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.cultivationdiary_test2.Data.Database.Project.Project;

@Entity(foreignKeys =
    @ForeignKey(entity = Project.class, parentColumns = "id_project", childColumns = "id_group"))
public class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_task")
    public int id_task;
    public int id_group;
    public String name_task;
    public boolean execution;

    public Task(int id_group, String name_task, boolean execution) {
        this.id_group = id_group;
        this.name_task = name_task;
        this.execution = execution;
    }

    public int getId_group() {return id_group;}
    public void setId_group(int newId) {this.id_group = newId;}

    public String getName_task() {return name_task;}
    public void setName_task(String name) {this.name_task = name;}

    public boolean getExecution() {return execution;}
    public void setExecution(boolean execut) {this.execution = execut;}

}
