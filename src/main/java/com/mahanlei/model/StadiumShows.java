package com.mahanlei.model;

public class StadiumShows {
    private int stadiumId;
    private int number;

    public StadiumShows(int stadiumId, int number) {
        this.stadiumId = stadiumId;
        this.number = number;
    }

    public int getStadiumId() {
        return stadiumId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStadiumId(int stadiumId) {

        this.stadiumId = stadiumId;
    }
}
