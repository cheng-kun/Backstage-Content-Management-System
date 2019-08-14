package com.company.backstagecontentmanagementsystem.domain;

import java.util.Date;

public class Category {
    private int catId;
    private String name;
    private Date createdAt;
    private int sequence;
    private User user;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId=" + catId +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", sequence=" + sequence +
                ", user=" + user +
                '}';
    }
}
