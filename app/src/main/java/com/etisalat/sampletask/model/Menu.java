package com.etisalat.sampletask.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Wakeel on 29-Jun-18.
 */

@Root(name = "menu", strict = false)
public class Menu {
    @ElementList(inline = true)
    public List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
