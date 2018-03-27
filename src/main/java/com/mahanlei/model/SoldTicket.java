package com.mahanlei.model;

public class SoldTicket {
    private int showId;
    private double rate;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public SoldTicket(int showId, double rate) {

        this.showId = showId;
        this.rate = rate;
    }
}
