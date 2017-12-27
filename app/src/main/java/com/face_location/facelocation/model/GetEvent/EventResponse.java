package com.face_location.facelocation.model.GetEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("seats")
    @Expose
    private Integer seats;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("isPrivate")
    @Expose
    private Boolean isPrivate;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("announcements")
    @Expose
    private List<Announcement> announcements = null;
    @SerializedName("subscribers")
    @Expose
    private List<Subscriber> subscribers = null;
    @SerializedName("locations")
    @Expose
    private List<Location> locations = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("sponsors")
    @Expose
    private List<Object> sponsors = null;
    @SerializedName("schedules")
    @Expose
    private List<Object> schedules = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("files")
    @Expose
    private List<File> files = null;
    @SerializedName("cover")
    @Expose
    private Cover_ cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public List<Object> getSponsors() {
        return sponsors;
    }

    public void setSponsors(List<Object> sponsors) {
        this.sponsors = sponsors;
    }

    public List<Object> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Object> schedules) {
        this.schedules = schedules;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Cover_ getCover() {
        return cover;
    }

    public void setCover(Cover_ cover) {
        this.cover = cover;
    }

}

