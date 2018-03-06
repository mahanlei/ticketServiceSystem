package com.mahanlei.model;

public class MemberInfo {
    private String mid;
    private int age;
    private String email;
    private int rank;
    private int points;
    private int state;
    private String  code;

    public MemberInfo() {
    }


    public MemberInfo(String mid, int age, String email, int rank, int points, int state, String code) {
        this.mid = mid;
        this.age = age;
        this.email = email;
        this.rank = rank;
        this.points = points;
        this.state = state;
        this.code = code;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
