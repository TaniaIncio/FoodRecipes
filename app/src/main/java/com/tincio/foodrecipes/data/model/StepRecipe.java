package com.tincio.foodrecipes.data.model;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by juan on 30/05/2017.
 */

@Entity(primaryKeys = {"id", "idRecipe"})
public class StepRecipe implements Parcelable {

    private int id;
    private String urlPlayer;
    private String instruction;
    private String name;
    private int order;
    private String image;
    private int idRecipe;

    public StepRecipe(){}

    protected StepRecipe(Parcel in) {
        id = in.readInt();
        urlPlayer = in.readString();
        instruction = in.readString();
        name = in.readString();
        order = in.readInt();
        image = in.readString();
        idRecipe = in.readInt();
    }

    public static final Creator<StepRecipe> CREATOR = new Creator<StepRecipe>() {
        @Override
        public StepRecipe createFromParcel(Parcel in) {
            return new StepRecipe(in);
        }

        @Override
        public StepRecipe[] newArray(int size) {
            return new StepRecipe[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(urlPlayer);
        dest.writeString(instruction);
        dest.writeInt(id);
        dest.writeInt(idRecipe);
    }
}
