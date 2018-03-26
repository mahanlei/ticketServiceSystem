package com.mahanlei.Util;

import java.text.DecimalFormat;

public class CalculatePrice {

    public static  double calculatePrice(double price,int discountType,int rank) {
        double discount = 0;
        if(rank==0){
           return price;
        }  else{
            switch (discountType) {
                case 0:
                    discount=1.0;
                    break;
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
                    discount = discount * 0.7;
                    break;
            }
            DecimalFormat df = new DecimalFormat("0.00");
            return Double.valueOf(df.format(price * discount));
        }
    }
    public static double getSeatPrice(int seatRows,int seatRow,int seatColumns,int seatColumn,double price){
//        排：前1/3的票价+20%原价>中间1/3原价>后1/3-20%原价
//        列：左1/3=右1/3=原价-20%，中间1/3原价
        double newPrice=price;
if(seatRow<=seatRows/3){
    newPrice=newPrice+0.3*price;
}else if(seatRow>seatRows/3*2){
    newPrice=newPrice-0.3*price;
}
        if(seatColumn<=seatColumns/3||seatColumn>seatColumns/3*2){
            newPrice=newPrice-0.2*price;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.valueOf(df.format(newPrice));

    }
}
