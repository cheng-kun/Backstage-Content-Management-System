package com.company.backstagecontentmanagementsystem.domain;

import java.util.Date;

public class Store {
    private int storeId;
    private String name;
    private String logo = "";
    private String address;
    private String category;
    private Date createdAt;
    private String description;
    private String phone;
    private String avgPrice = "";
    private String saleFrom = "";
    private String saleTo = "";
    private User user;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdTime) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getSaleFrom() {
        return saleFrom;
    }

    public void setSaleFrom(String saleFrom) {
        this.saleFrom = saleFrom;
    }

    public String getSaleTo() {
        return saleTo;
    }

    public void setSaleTo(String saleTo) {
        this.saleTo = saleTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", avgPrice=" + avgPrice +
                ", saleFrom='" + saleFrom + '\'' +
                ", saleTo='" + saleTo +
                ", user='" + user +
                '}';
    }
}