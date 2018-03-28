package com.mahanlei.model;

public class TicketCount {
    private int number;
    String time;

    public TicketCount(String time,int number) {
        this.number = number;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
