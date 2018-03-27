package com.mahanlei.service;

import com.mahanlei.model.SoldTicket;

import java.util.List;

public interface StaticsService {
    /**
     * 获得某个场馆所有演出的购票率
     * @param stadiumId
     * @return
     */
    public List<SoldTicket> getSoldTicketRate(int stadiumId);

    public List<SoldTicket> getAttendenceRate(int stadiumId);
    /**
     * 获得场馆/经理的分红
     * @param id
     * @return
     */
    public double getProfit(String id);

}
