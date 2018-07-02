package com.etisalat.sampletask.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Wakeel on 29-Jun-18.
 */

@Entity
@Root(name = "item")
public class Item {
    @Element(name = "id")
    @PrimaryKey
    @NotNull
    private String id;

    @Element(name = "name")
    private String name;

    @Element(name = "cost")
    private String cost;

    @Element(name = "description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
