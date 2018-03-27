package com.mahanlei.model;

public class StadiumInfo {
    private int stadiumId;
    private String staName;
    private String address;
    private int seatRows;
    private int seatColumns;
    private int state;
    private String staID;

    public StadiumInfo(int stadiumId, String staName, String address, int seatRows, int seatColumns, int state) {
        this.stadiumId = stadiumId;
        this.staName = staName;
        this.address = address;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
        this.state = state;

    }

    public StadiumInfo(int stadiumId, String staName, String address, int seatRows, int seatColumns, int state, String staID) {
        this.stadiumId = stadiumId;
        this.staName = staName;
        this.address = address;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
        this.state = state;
        this.staID = staID;
    }

    public StadiumInfo(String staName, String address, int seatRows, int seatColumns, int state) {
        this.staName = staName;
        this.address = address;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
        this.state = state;
    }

    public StadiumInfo() {
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStaID() {
        return staID;
    }

    public void setStaID(String staID) {
        this.staID = staID;
    }
}
