package com.tincio.foodrecipes.data.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 28/05/2017.
 */

public class StepResponse {

    @SerializedName("url_player")
    @Expose
    private String urlPlayer;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String _class;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_recipe")
    @Expose
    private Integer idRecipe;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("__meta")
    @Expose
    private String meta;

    public String getUrlPlayer() {
        return urlPlayer;
    }

    public void setUrlPlayer(String urlPlayer) {
        this.urlPlayer = urlPlayer;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
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

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

}
