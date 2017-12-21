package com.face_location.facelocation.model.NewPostEvent;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subscriber {

    @SerializedName("user")
    @Expose
    private User_____ user;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("activated")
    @Expose
    private Boolean activated;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public User_____ getUser() {
        return user;
    }

    public void setUser(User_____ user) {
        this.user = user;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
