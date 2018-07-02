package com.etisalat.sampletask.database;


import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseCreator {

    private static MenuDatabase menuDatabase;
    private static final Object LOCK = new Object();

    public synchronized static MenuDatabase getMenuDatabase(Context context) {
        if (menuDatabase == null) {
            synchronized (LOCK) {
                if (menuDatabase == null) {
                    menuDatabase = Room.databaseBuilder(context, MenuDatabase.class, "menu db").build();
                }
            }
        }
        return menuDatabase;
    }
}
