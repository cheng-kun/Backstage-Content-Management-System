package com.company.backstagecontentmanagementsystem.domain;

import java.util.Date;

public class Store {
    private int id;
    private String name;
    private String logo;
    private String address;
    private String category;
    private Date createdTime;
    private String description;
    private String phone;
    private double avgPrice;
    private String saleFrom;
    private String saleTo;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

    public double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(double avgPrice) {
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", address='" + address + '\'' +
                ", category='" + category + '\'' +
                ", createdTime=" + createdTime +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", avgPrice=" + avgPrice +
                ", saleFrom='" + saleFrom + '\'' +
                ", saleTo='" + saleTo +
                ", user='" + user +
                '}';
    }
}