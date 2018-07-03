package com.etisalat.sampletask.views.menu;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.bases.BaseActivity;
import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.database.ItemViewModel;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.presenter.MenuPresenter;
import com.etisalat.sampletask.views.camera.CameraActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MenuActivity extends BaseActivity implements MenuView {
    private MenuPresenter presenter;
    TextView txtTimeStamp, txtAppName;
    private ItemViewModel itemViewModel;
    MenuFragment menuFragment = new MenuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.foodsContainer, menuFragment)
                .commit();
        FloatingActionButton refreshFab = findViewById(R.id.refreshFAB);
        FloatingActionButton cameraFab = findViewById(R.id.cameraFAB);
        txtTimeStamp = findViewById(R.id.txtTime);
        txtAppName = findViewById(R.id.txtAppName);
        txtAppName.setText(getResources().getString(R.string.app_name));
        refreshFab.setOnClickListener(view -> presenter.getMenu());
        cameraFab.setOnClickListener(view -> openCameraActivity());
    }

    private void openCameraActivity() {
        Intent cameraActivity = new Intent(MenuActivity.this, CameraActivity.class);
        startActivity(cameraActivity);
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
        //Sort List
        Collections.sort(itemList, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {
                String s1 = item.getName();
                String s2 = t1.getName();
                return s1.compareToIgnoreCase(s2);
            }
        });
        //Add list in DB
        for (Item item : itemList) {
            item.setTimeStamp(getTime());
            itemViewModel.updateItem(item);
        }
        txtTimeStamp.setText("last update: " + getTime());
        menuFragment.adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(
                findViewById(R.id.foodsContainer),
                message,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    public String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
}
