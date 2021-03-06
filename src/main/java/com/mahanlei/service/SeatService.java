package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Seat;

import java.util.Date;
import java.util.List;

public interface SeatService {
    /**
     * 增加座位信息
     * @param showName
     * @param stadiumId
     * @param startTime
     * @return
     */
 public Message addSeats(String showName,int stadiumId,Date startTime,double price);
    /**
     * 获得某个场馆某场演出的已售座位数
     * @param showId
     * @param stadiumId
     * @return
     */
    public int getSoldSeats(int showId,int stadiumId);

}
