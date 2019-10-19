package com.company.backstagecontentmanagementsystem.domain;


import java.io.Serializable;

public class Member implements Serializable {

    private static final long serialVersionUID = 2896396383891306246L;

    private int memberId;

    private String nickname;

    private String gender;

    private String phone;

    private int credit;

    private String cardNo;

    private String createdAt;

    private int orderCount;

    private User user;

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", credit=" + credit +
                ", cardNo='" + cardNo + '\'' +
                ", createdAt=" + createdAt +
                ", orderCount=" + orderCount +
                ", user=" + user +
                '}';
    }
}
