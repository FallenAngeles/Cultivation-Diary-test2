package com.example.cultivationdiary_test2.Data.Database.Project;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Project {

    @PrimaryKey(autoGenerate = true)
    public int id_project;
    public String CRdatetime_prj;
    public String name_project;
    //public String project_color;
    public int project_icon;
    public String project_description;
    //public String project_list;
    //public String project_deadline;

    public Project(String CRdatetime_prj, String name_project, String project_description, int icon) {
        this.CRdatetime_prj = CRdatetime_prj;
        this.name_project = name_project;
        this.project_description = project_description;
        this.project_icon = icon;
    }

    public int getId_project() {return id_project;}
    public void setId_project(int newId) {this.id_project = newId;}
    public String getCRdatetime_prj() {return CRdatetime_prj;}
    public String getName_project() {return name_project;}
    public String getProject_description() {return project_description;}
    public int getIcon() {return project_icon;}

}
