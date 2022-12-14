package com.stankloardindustries.socialmedia.Model;

public class NotificationModel {

    int nestedProfile;
    String nestedNotification, time;

    public NotificationModel(int nestedProfile, String nestedNotification, String time) {
        this.nestedProfile = nestedProfile;
        this.nestedNotification = nestedNotification;
        this.time = time;
    }

    public int getNestedProfile() {
        return nestedProfile;
    }

    public void setNestedProfile(int nestedProfile) {
        this.nestedProfile = nestedProfile;
    }

    public String getNestedNotification() {
        return nestedNotification;
    }

    public void setNestedNotification(String nestedNotification) {
        this.nestedNotification = nestedNotification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
