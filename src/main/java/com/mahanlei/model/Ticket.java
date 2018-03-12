package com.mahanlei.model;


import java.util.Date;

public class Ticket {
    private int tid;
    private String mid;
    private int showId;
    private int stadiumId;
    private int seatRow;
    private int seatColumn;
    private int state;

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

    private Date refunedTime;
    private double payPrice;

    public Ticket(String mid, int showId, int stadiumId, int seatRow, int seatColumn) {
        this.tid=0;
        this.mid = mid;
        this.showId = showId;
        this.stadiumId = stadiumId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.state = 0;
        this.refunedTime=null;
        this.payPrice=0.0;
    }

    public Ticket(){

    }

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

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Ticket(int tid, String mid, int showId, int stadiumId, int seatRow, int seatColumn, int state) {

        this.tid = tid;
        this.mid = mid;
        this.showId = showId;
        this.stadiumId = stadiumId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.state = state;
    }
}
