package com.mahanlei.model;

import java.util.Date;

public class StaTicket {
    private int tid;
    private String mid;

    private String showName;
    private String picture;
    private int seatRow;
    private int seatColumn;
    private Date createdTime;
    private Date refunedTime;
    private double payPrice;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }


    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getRefunedTime() {
        return refunedTime;
    }

    public void setRefunedTime(Date refunedTime) {
        this.refunedTime = refunedTime;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public StaTicket(int tid, String mid, String showName, String picture, int seatRow, int seatColumn, Date createdTime, Date refunedTime, double payPrice) {

        this.tid = tid;
        this.mid = mid;

        this.showName = showName;
        this.picture=picture;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.createdTime = createdTime;
        this.refunedTime = refunedTime;
        this.payPrice = payPrice;
    }
}
