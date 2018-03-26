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

}
