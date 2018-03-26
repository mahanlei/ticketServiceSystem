package com.mahanlei.model;

import com.mahanlei.Util.ShowType;

import java.util.Date;

public class ShowInfo {
    private  int showId;
    private String name;
    private int stadiumId;
    private String staName;
   private String address;
   private int seatRows;
   private  int seatColumns;
    private Date startTime;
    private Date endTime;
    private ShowType type;
    private String picture;
    private String description;
    private int showState;

    public ShowInfo(int showId, String name, Date startTime, Date endTime, ShowType type, String picture, String description, int showState) {
        this.showId = showId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.picture = picture;
        this.description = description;
        this.showState = showState;
    }
    public ShowInfo(int showId, String name, int stadiumId, String staName, String address, int seatRows, int seatColumns, Date startTime, Date endTime, ShowType type, String picture, String description) {
        this.showId = showId;
        this.name = name;
        this.stadiumId= stadiumId;
        this.staName=staName;
        this.address=address;
        this.seatRows=seatRows;
        this.seatColumns=seatColumns;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.picture = picture;
        this.description = description;

    }

    public ShowInfo(String name, int stadiumId, Date startTime, Date endTime, ShowType type, String picture, String description, int showState) {
        this.name = name;
        this.stadiumId = stadiumId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.picture = picture;
        this.description = description;
        this.showState = showState;
    }

    public ShowInfo(){

    }
    public int getShowState() {

        return showState;
    }

    public void setShowState(int showState) {
        this.showState = showState;
    }



    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }


    public int getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(int stadiumId) {
        this.stadiumId = stadiumId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShowType getType() {
        return type;
    }

    public void setType(ShowType type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
