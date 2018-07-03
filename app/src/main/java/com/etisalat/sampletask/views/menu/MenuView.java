package com.etisalat.sampletask.views.menu;

import com.etisalat.sampletask.model.Item;

import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public interface MenuView {
    void showProgress(boolean show);

    void getFoodList(List<Item> itemList);

    void showError(String message);
}
