package com.etisalat.sampletask.views.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etisalat.sampletask.R;
import com.etisalat.sampletask.model.Item;

import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<Item> itemList;

    class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameTXT, costTXT, descTXT;

        MenuViewHolder(View view) {
            super(view);
            nameTXT = view.findViewById(R.id.nameTxt);
            costTXT = view.findViewById(R.id.costTxt);
            descTXT = view.findViewById(R.id.descriptionTxt);
        }
    }

    public MenuAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MenuViewHolder holder, final int position) {
        Item item = itemList.get(position);
        holder.nameTXT.setText(item.getName());
        holder.costTXT.setText(item.getCost() + "$");
        holder.descTXT.setText(item.getDescription());

    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(itemList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
