package com.mahanlei.model;

public class TicketPrice {
    private double price;
    String time;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TicketPrice(double price, String time) {

        this.price = price;
        this.time = time;
    }
}
