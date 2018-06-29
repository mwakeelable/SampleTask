package com.etisalat.sampletask.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseFragment;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.presenter.MenuPresenter;

import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class MenuFragment extends BaseFragment implements MenuView {
    MenuPresenter menuPresenter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        menuPresenter.getMenu();
        initViews(view);
        return view;
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
        adapter = new MenuAdapter(getActivity(), itemList);
        menuList.setAdapter(adapter);
        for (Item item : itemList) {
            Log.d("RETROFIT", item.getId() + "\n"
                    + " - Alpha2:  " + item.getName() + " \n"
                    + " - Alpha3: " + item.getCost());
        }
    }
}
