package com.mahanlei.dao;

import java.util.List;

public interface StatisticsDao {
    /**
     * 获得场馆/经理的分红
     * @param id
     * @return
     */
    public double getProfit(String id);

}
