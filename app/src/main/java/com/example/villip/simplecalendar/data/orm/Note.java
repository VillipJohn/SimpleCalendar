package com.example.villip.simplecalendar.data.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "notes")
public class Note {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String date;
    @DatabaseField
    private String notes_type;
    @DatabaseField
    private String notes_text;
    @DatabaseField
    private String alarm;

    public Note() {
        // ORMLite needs a no-arg constructor
    }

    public Note(String date, String notes_type, String notes_text, String alarm) {
        //super();
        this.date = date;
        this.notes_type = notes_type;
        this.notes_text = notes_text;
        this.alarm = alarm;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id +
                ", notes_type='" + notes_type + '\'' +
                ", notes_text='" + notes_text + '\'' +
                ", alarm='" + alarm + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes_type() {
        return notes_type;
    }

    public void setNotes_type(String notes_type) {
        this.notes_type = notes_type;
    }

    public String getNotes_text() {
        return notes_text;
    }

    public void setNotes_text(String notes_text) {
        this.notes_text = notes_text;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }
}