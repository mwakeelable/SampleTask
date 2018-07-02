package com.etisalat.sampletask.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.etisalat.sampletask.model.Item;

/**
 * Created by Wakeel on 02-Jul-18.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class MenuDatabase extends RoomDatabase {
    public abstract MenuDao menuDatabase();
}
