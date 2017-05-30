package com.tincio.foodrecipes.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by juan on 30/05/2017.
 */

@Entity
public class StepRecipe {
    @PrimaryKey
    private int id;
    private String urlPlayer;
    private String instruction;
    private String name;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlPlayer() {
        return urlPlayer;
    }

    public void setUrlPlayer(String urlPlayer) {
        this.urlPlayer = urlPlayer;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
