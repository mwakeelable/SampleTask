package com.etisalat.sampletask.presenter;

import com.etisalat.sampletask.bases.BasePresenter;
import com.etisalat.sampletask.bases.BasePresenterListener;
import com.etisalat.sampletask.model.Item;
import com.etisalat.sampletask.model.Menu;
import com.etisalat.sampletask.network.ApiClient;
import com.etisalat.sampletask.network.ApiService;
import com.etisalat.sampletask.views.FoodsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class FoodsPresenter extends BasePresenter {
    private FoodsView foodsView;
    private ApiClient client;

    public FoodsPresenter(BasePresenterListener listener, FoodsView view) {
        super(listener);
        this.foodsView = view;
        if (this.client == null) {
            this.client = new ApiClient();
        }
    }

    public void getMenu(){
        client.getWebServices().getMenu().enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                Menu menu = response.body();
                if (menu != null && menu.items != null) {
                    List<Item> result = menu.getItems();
                    foodsView.getFoodList(result);
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                try {
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
