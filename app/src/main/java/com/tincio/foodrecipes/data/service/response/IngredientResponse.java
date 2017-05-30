package com.tincio.foodrecipes.data.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by juan on 28/05/2017.
 */

public class IngredientResponse {

    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("count")
    @Expose
    private String count;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("___class")
    @Expose
    private String _class;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ownerId")
    @Expose
    private Object ownerId;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("objectId")
    @Expose
    private String objectId;
    @SerializedName("id_recipe")
    @Expose
    private Integer idRecipe;
    @SerializedName("__meta")
    @Expose
    private String meta;

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public Object getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Object ownerId) {
        this.ownerId = ownerId;
    }

    public Object getUpdated() {
        return updated;
    }

    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(Integer idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }
}
