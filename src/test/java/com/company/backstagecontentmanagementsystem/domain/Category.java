package com.company.backstagecontentmanagementsystem.domain;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 2564819842148317353L;

    private int catId;

    private String name;

    private String createdAt;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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

    public Category() {
    }

    public Category(int catId) {
        this.catId = catId;
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
