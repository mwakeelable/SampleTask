package com.etisalat.sampletask.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.model.Menu;
import com.etisalat.sampletask.presenter.FoodsPresenter;

import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class FoodsFragment extends BaseFragment implements FoodsView {
    FoodsPresenter foodsPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods, container, false);
        foodsPresenter.getMenu();
        return view;
    }

    @Override
    protected BasePresenter setupPresenter() {
        foodsPresenter = new FoodsPresenter(this, this);
        return foodsPresenter;
    }

    @Override
    public void getFoodList(List<Item> itemList) {
        for (Item item : itemList) {
            Log.d("RETROFIT", item.getId() + "\n"
                    + " - Alpha2:  " + item.getName() + " \n"
                    + " - Alpha3: " + item.getCost());
        }
    }
}
