package com.mahanlei.dao;

import com.mahanlei.model.TicketCount;
import com.mahanlei.model.TicketPrice;

import java.util.List;

public interface StatisticsDao {
    /**
     * 获得场馆/经理的分红
     * @param id
     * @return
     */
    public double getProfit(String id);

    /**
     * 获得近7天的每天成交量
     * @return
     */
    public List<TicketCount> getTicketCount();

    /**
     * 获得近7天的每天成交金额
     * @return
     */
    public  List<TicketPrice> getTicketPrice();
    public List<Integer> getAllStadiumId();

    public int[] countMemberByAge();
}
