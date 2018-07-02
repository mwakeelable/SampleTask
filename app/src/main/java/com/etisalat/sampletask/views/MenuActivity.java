package com.etisalat.sampletask.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseActivity;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.presenter.MenuPresenter;

import java.util.List;

public class MenuActivity extends BaseActivity implements MenuView {
    private MenuPresenter presenter;
    TextView txtTimeStamp, txtAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.foodsContainer, new MenuFragment())
                .commit();
        FloatingActionButton fab = findViewById(R.id.fab);
        txtTimeStamp = findViewById(R.id.txtTime);
        txtAppName = findViewById(R.id.txtAppName);
        txtAppName.setText(getResources().getString(R.string.app_name));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getMenu();
            }
        });
    }

    @Override
    protected BasePresenter setupPresenter() {
        presenter = new MenuPresenter(this, this);
        return presenter;
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
        //show refreshed list
    }

    @Override
    public void showError(String message) {

    }
}
