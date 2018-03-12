package com.mahanlei.model;

public class Seat {
    private int showId;
    private int stadiumId;
    private int seatRow;
    private int seatColumn;
    private double price;
    private int state;

    public Seat(int showId, int stadiumId, int seatRow, int seatColumn, double price) {
        this.showId = showId;
        this.stadiumId = stadiumId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.price = price;
        this.state=0;
    }
    public Seat(int showId, int stadiumId, int seatRow, int seatColumn,int state) {
        this.showId = showId;
        this.stadiumId = stadiumId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.price = 0.0;
        this.state=state;
    }
    public Seat(){

    }

    public Seat(int showId, int stadiumId, int seatRow, int seatColumn, double price, int state) {
        this.showId = showId;
        this.stadiumId = stadiumId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.price = price;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
