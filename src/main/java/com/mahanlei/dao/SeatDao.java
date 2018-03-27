package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Seat;

import java.util.List;

public interface SeatDao {
    /**
     * 当发布了演出后，插入座位信息
     * @param seats
     * @return
     */
    public Message addSeats(List<Seat> seats);

    /**
     * 获得某个场馆某场演出的已售座位数
     * @param showId
     * @param stadiumId
     * @return
     */
    public int getSoldSeats(int showId,int stadiumId);



}
