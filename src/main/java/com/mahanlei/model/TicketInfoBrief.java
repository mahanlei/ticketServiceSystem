package com.mahanlei.model;

import java.util.Date;

public class TicketInfoBrief {
    private String  showName;
    private String  staName;
    private String picture;
    private int seatRow;
    private int seatColumn;
    private Date createdTime;
    private Date refunedTime;
    private double payPrice;
    public TicketInfoBrief(){

    }

    public TicketInfoBrief(String  showName, String staName, String picture,int seatRow, int seatColumn, Date createdTime, double payPrice) {
        this.showName = showName;
        this.staName = staName;
        this.picture=picture;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.createdTime = createdTime;
        this.refunedTime=null;
        this.payPrice = payPrice;
    }

    public TicketInfoBrief(String showName, String staName, String picture,int seatRow, int seatColumn, Date createdTime, Date refunedTime, double payPrice) {
        this.showName = showName;
        this.staName = staName;
        this.picture=picture;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.createdTime = createdTime;
        this.refunedTime = refunedTime;
        this.payPrice = payPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
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
}
