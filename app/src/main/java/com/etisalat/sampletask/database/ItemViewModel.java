package com.etisalat.sampletask.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.etisalat.sampletask.model.Item;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Wakeel on 02-Jul-18.
 */

public class ItemViewModel extends AndroidViewModel {
    private ItemRepository itemRepository = new ItemRepository(this.getApplication());
    private final Executor executor = Executors.newFixedThreadPool(2);
    private final MediatorLiveData<List<Item>> items = new MediatorLiveData<>();

    public ItemViewModel(@NonNull Application application){
        super(application);
    }

    public void addItem(final Item item){
        executor.execute(() -> {
            itemRepository.addItem(item);
        });
    }
    public void updateItem(Item item){
        executor.execute(() -> {
            itemRepository.updateItem(item);
        });
    }
    public void deleteItem(Item item){
        executor.execute(() -> {
            itemRepository.deleteItem(item);
        });
    }
    public LiveData<List<Item>> getAllItems(){
        return itemRepository.getAllItems();
    }
}
