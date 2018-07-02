package com.etisalat.sampletask.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.etisalat.sampletask.model.Item;

import java.util.List;

/**
 * Created by Wakeel on 02-Jul-18.
 */

public class ItemRepository {
    private final MenuDao menuDao;

    public ItemRepository(Context context) {
        menuDao = DatabaseCreator.getMenuDatabase(context).menuDatabase();
    }

    public void addItem(Item item) {
        long rec = menuDao.insertItem(item);
        Log.d("db insert ", "added " + rec);
    }

    public void updateItem(Item item) {
        menuDao.updateItem(item);
    }

    public void deleteItem(Item item) {
        menuDao.deleteItem(item);
    }

    public LiveData<List<Item>> getAllItems() {
        return menuDao.getAllItems();
    }
}
