package com.tincio.foodrecipes.data.model;

import android.arch.persistence.room.Entity;

/**
 * Created by juan on 30/05/2017.
 */

@Entity(primaryKeys = {"id", "idRecipe"})
public class StepRecipe {

    private int id;
    private String urlPlayer;
    private String instruction;
    private String name;
    private int order;
    private String image;
    private int idRecipe;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

}
