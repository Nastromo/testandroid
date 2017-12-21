package com.face_location.facelocation.model.NewPostEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sponsor {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
