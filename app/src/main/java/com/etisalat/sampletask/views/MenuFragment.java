package com.etisalat.sampletask.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.database.ItemViewModel;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.presenter.MenuPresenter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class MenuFragment extends BaseFragment implements MenuView {
    MenuPresenter menuPresenter;
    View view;
    private ItemViewModel itemViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        initViews(view);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        getAllItems();
        return view;
    }

    public void getAllItems() {
        LiveData<List<Item>> allItems = itemViewModel.getAllItems();
        observerItemListResults(allItems);
    }

    private void observerItemListResults(LiveData<List<Item>> itemsLive) {
        itemsLive.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                if (items == null) {
                    return;
                }
                if (items.size() > 0) {
                    adapter = new MenuAdapter(getActivity(), items);
                    menuList.setAdapter(adapter);
                } else {
                    menuPresenter.getMenu();
                }
            }
        });
    }

    public void addItem(Item item) {
        itemViewModel.addItem(item);
    }

    RecyclerView menuList;
    MenuAdapter adapter;
    LinearLayoutManager mLayoutManager;

    private void initViews(View view) {
        menuList = view.findViewById(R.id.menuRecyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        menuList.setLayoutManager(mLayoutManager);
        menuList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected BasePresenter setupPresenter() {
        menuPresenter = new MenuPresenter(this, this);
        return menuPresenter;
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    @Override
    public void getFoodList(List<Item> itemList) {
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {
                String s1 = item.getName();
                String s2 = t1.getName();
                return s1.compareToIgnoreCase(s2);
            }
        });

        for (Item item : itemList) {
            addItem(item);
        }

        adapter = new MenuAdapter(getActivity(), itemList);
        menuList.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(view.findViewById(R.id.menuContainer), "Welcome to AndroidHive", Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
