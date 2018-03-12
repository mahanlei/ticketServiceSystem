package com.mahanlei.Util;

public class CalculatePrice {

    public static  double caculatePrice(double price,int discountType,int rank) {
        double discount = 0;
        switch (discountType) {
            case 2:
                discount = 0.95;
                break;
            case 3:
                discount = 0.9;
                break;
            case 4:
                discount = 0.8;
                break;
            case 5:
                discount = 0.75;
                break;

        }
        switch (rank) {
            case 1:
                discount = discount * 0.95;
                break;
            case 2:
                discount = discount * 0.9;
                break;
            case 3:
                discount = discount * 0.8;
                break;
            case 4:
                discount = discount * 0.75;
                break;
            case 5:
                discount = discountType * 0.7;
                break;
        }
        return price * discount;
    }
}
