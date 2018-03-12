package com.mahanlei.model;

public class MemberBill {
  private String mid;
  private double balance;
  private double consumption;

  public String getMid() {
    return mid;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public double getConsumption() {
    return consumption;
  }

  public void setConsumption(double consumption) {
    this.consumption = consumption;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

}
