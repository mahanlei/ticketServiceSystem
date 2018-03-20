package com.mahanlei.Util;

import java.text.DecimalFormat;

public class CalculatePrice {

    public static  double caculatePrice(double price,int discountType,int rank) {
        double discount = 0;
        if(discountType==0){
            return price;
        }else {
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
                    discount = discount * 0.7;
                    break;
            }
            DecimalFormat df = new DecimalFormat("0.00");
            return Double.valueOf(df.format(price * discount));
        }
    }
}
