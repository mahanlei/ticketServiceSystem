package com.mahanlei.dao.impl;

import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.StatisticsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticsDaoImpl implements StatisticsDao {
    private static StatisticsDaoImpl statisticsDao=new StatisticsDaoImpl();

    public static StatisticsDaoImpl getStatisticsDao() {
        return statisticsDao;
    }

    @Override
    public double getProfit(String id) {
        double result = 0.0;
        DaoHelper daoHelper=new DaoHelperImpl();
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        try {
            statement = connection.prepareStatement("select profilt FROM  profilt WHERE uid=?");
            statement.setString(1,id);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                result=resultSet.getDouble("profilt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeResult(resultSet);
        }
        return result;
    }
}
