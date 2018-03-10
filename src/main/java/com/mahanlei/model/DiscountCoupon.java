package com.mahanlei.model;

public class DiscountCoupon {
    private String mid;
    private int discountCouponType1;
    private int count1;
    private int discountCouponType2;
    private int count2;
    private int discountCouponType3;
    private int count3;
    private int discountCouponType4;
    private int count4;
    public DiscountCoupon(){

    }

    public DiscountCoupon(String mid, int discountCouponType1, int count1, int discountCouponType2, int count2, int discountCouponType3, int count3, int discountCouponType4, int count4) {
        this.mid = mid;
        this.discountCouponType1 = discountCouponType1;
        this.count1 = count1;
        this.discountCouponType2 = discountCouponType2;
        this.count2 = count2;
        this.discountCouponType3 = discountCouponType3;
        this.count3 = count3;
        this.discountCouponType4 = discountCouponType4;
        this.count4 = count4;
    }

    public DiscountCoupon(String mid, int discountCouponType1, int discountCouponType2, int discountCouponType3, int discountCouponType4) {
        this.mid = mid;
        this.discountCouponType1 = discountCouponType1;
        this.discountCouponType2 = discountCouponType2;
        this.discountCouponType3 = discountCouponType3;
        this.discountCouponType4 = discountCouponType4;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }


    public int getCount1() {
        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }


    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }


    public int getCount3() {
        return count3;
    }

    public void setCount3(int count3) {
        this.count3 = count3;
    }

    public int getDiscountCouponType1() {
        return discountCouponType1;
    }

    public void setDiscountCouponType1(int discountCouponType1) {
        this.discountCouponType1 = discountCouponType1;
    }

    public int getDiscountCouponType2() {
        return discountCouponType2;
    }

    public void setDiscountCouponType2(int discountCouponType2) {
        this.discountCouponType2 = discountCouponType2;
    }

    public int getDiscountCouponType3() {
        return discountCouponType3;
    }

    public void setDiscountCouponType3(int discountCouponType3) {
        this.discountCouponType3 = discountCouponType3;
    }

    public int getDiscountCouponType4() {
        return discountCouponType4;
    }

    public void setDiscountCouponType4(int discountCouponType4) {
        this.discountCouponType4 = discountCouponType4;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }
}
