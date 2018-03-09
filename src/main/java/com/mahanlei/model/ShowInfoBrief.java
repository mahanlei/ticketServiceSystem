package com.mahanlei.model;

import com.mahanlei.Util.ShowType;


public class ShowInfoBrief {
    private  int showId;
    private String name;
    private ShowType type;
    private String picture;

    public ShowInfoBrief() {

    }

    public ShowInfoBrief(int showId, String name, ShowType type, String picture) {
        this.showId = showId;
        this.name = name;
        this.type = type;
        this.picture = picture;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShowType getType() {
        return type;
    }

    public void setType(ShowType type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
