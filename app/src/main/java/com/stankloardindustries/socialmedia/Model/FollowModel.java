package com.stankloardindustries.socialmedia.Model;

public class FollowModel {
    private String follwedBy;
    private long follwedAt;

    public FollowModel() {
    }

    public String getFollwedBy() {
        return follwedBy;
    }

    public void setFollwedBy(String follwedBy) {
        this.follwedBy = follwedBy;
    }

    public long getFollwedAt() {
        return follwedAt;
    }

    public void setFollwedAt(long follwedAt) {
        this.follwedAt = follwedAt;
    }
}
