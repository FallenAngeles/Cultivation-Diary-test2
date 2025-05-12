package com.example.cultivationdiary_test2.Adapter.Caledar;

import java.io.Serializable;

public class ViewDay implements Serializable {

    private String Day;
    private String Month;
    private String DayOfWeek;
    private Float Alpha;
    private String fullDate;
    private boolean hasDiary = false;

    public ViewDay(String Day, String Month, String DayOfWeek, Float Alpha, String fullDate) {
        this.Day = Day;
        this.Month = Month;
        this.DayOfWeek = DayOfWeek;
        this.Alpha = Alpha;
        this.fullDate = fullDate;
    }

    public String getDay() {return this.Day;}
    public void setDay(String NewDay) {this.Day = NewDay;}
    public String getMonth() {return this.Month;}
    public void  setMonth(String NewMonth) {this.Month = NewMonth;}
    public String getDayOfWeek() {return this.DayOfWeek;}
    public void setDayOfWeek(String NewDayOfWeek) {this.DayOfWeek = NewDayOfWeek;}
    public Float getAlpha() {return this.Alpha;}
    public void setAlpha(Float NewAlpha) {this.Alpha = NewAlpha;}
    public boolean getHasDiary() {return hasDiary;}
    public void setHasDiary(boolean hasDiary) {this.hasDiary = hasDiary;}
    public String getFullDate() {return fullDate;}
    public void setFullDate(String fullDate) {this.fullDate = fullDate;}
}
