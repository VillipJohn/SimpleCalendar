package com.example.villip.simplecalendar.ui.recyclerView;

/**
 * Created by villip on 07.06.2016.
 */
public class ItemObject {
    private int date;
    private boolean dayToday;
    private boolean dayWithNote;

    public ItemObject(int date, boolean dayToday, boolean dayWithNote) {
        this.date = date;
        this.dayToday = dayToday;
        this.dayWithNote = dayWithNote;
    }

    public int getDate() {
        return date;
    }

    public boolean isDayWithNote() {
        return dayWithNote;
    }

    public boolean isDayToday() {
        return dayToday;
    }
}
