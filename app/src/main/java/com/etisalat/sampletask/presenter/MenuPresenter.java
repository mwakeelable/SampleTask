package com.etisalat.sampletask.presenter;

import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.bases.BasePresenterListener;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.model.Menu;
import com.etisalat.sampletask.network.ApiClient;
import com.etisalat.sampletask.views.MenuView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class MenuPresenter extends BasePresenter {
    private MenuView menuView;
    private ApiClient client;

    public MenuPresenter(BasePresenterListener listener, MenuView view) {
        super(listener);
        this.menuView = view;
        if (this.client == null) {
            this.client = new ApiClient();
        }
    }

    public void getMenu() {
        menuView.showProgress(true);
        client.getWebServices().getMenu().enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                menuView.showProgress(false);
                Menu menu = response.body();
                if (menu != null && menu.items != null) {
                    List<Item> result = menu.getItems();
                    menuView.getFoodList(result);
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                menuView.showProgress(false);
                try {
                    menuView.showError(t.getMessage());
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
