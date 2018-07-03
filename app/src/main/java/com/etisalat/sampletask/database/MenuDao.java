package com.etisalat.sampletask.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.etisalat.sampletask.model.Item;

import java.util.List;

/**
 * Created by Wakeel on 02-Jul-18.
 */

@Dao
public interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertItem(Item item);

    @Update
    void updateItem(Item item);

    @Query("SELECT * FROM item")
    LiveData<List<Item>> getAllItems();
}
