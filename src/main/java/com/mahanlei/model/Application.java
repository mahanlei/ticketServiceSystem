package com.mahanlei.model;

public class Application {
    private int aid;
    private int stadiumId;
    private int state;//申请通过为1，尚未通过为0
    private int type;//注册申请为1，修改申请为2
    private String staName;
    private String address;
    private int seatRows;
    private int seatColumns;

    public Application(int aid, int stadiumId, int state, int type, String staName, String address, int seatRows, int seatColumns) {
        this.aid = aid;
        this.stadiumId = stadiumId;
        this.state = state;
        this.type = type;
        this.staName = staName;
        this.address = address;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
    }

    public Application(int stadiumId, int state, int type, String staName) {
        this.stadiumId = stadiumId;
        this.state = state;
        this.type = type;
        this.staName = staName;
    }

    public Application(int stadiumId, int state, int type, String staName, String address, int seatRows, int seatColumns) {
        this.stadiumId = stadiumId;
        this.state = state;
        this.type = type;
        this.staName = staName;
        this.address = address;
        this.seatRows = seatRows;
        this.seatColumns = seatColumns;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
