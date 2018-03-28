package com.mahanlei.dao.impl;

import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.StatisticsDao;
import com.mahanlei.model.Ticket;
import com.mahanlei.model.TicketCount;
import com.mahanlei.model.TicketPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoImpl implements StatisticsDao {
    private static StatisticsDaoImpl statisticsDao=new StatisticsDaoImpl();

    public static StatisticsDaoImpl getStatisticsDao() {
        return statisticsDao;
    }
DaoHelper daoHelper= DaoHelperImpl.getDaoHelper();
    @Override
    public double getProfit(String id) {
        double result = 0.0;

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

    @Override
    public List<TicketCount> getTicketCount() {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        List<TicketCount> ticketCounts=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT DATE_FORMAT(createdTime,'%Y-%m-%d')  as time,count(*) number FROM ticket where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= createdTime and state=2 GROUP BY time;");
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                TicketCount ticketCount=new TicketCount(resultSet.getString("time"),resultSet.getInt("number"));
                ticketCounts.add(ticketCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);

        }
return ticketCounts;
    }

    @Override
    public List<TicketPrice> getTicketPrice() {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        List<TicketPrice> ticketCounts=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT DATE_FORMAT(createdTime,'%Y-%m-%d')  as time,sum(payPrice) price FROM ticket where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= createdTime and state=2 GROUP BY time;");
            resultSet=statement.executeQuery();
            while (resultSet.next()){

                TicketPrice ticketPrice=new TicketPrice(resultSet.getDouble("price"),resultSet.getString("time"));
                ticketCounts.add(ticketPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);

        }
        return ticketCounts;
    }

    @Override
    public List<Integer> getAllStadiumId() {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        List<Integer> staList=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT stadiumid FROM stadiuminfo");
            resultSet=statement.executeQuery();
            while (resultSet.next()) {
                int staid=resultSet.getInt("stadiumid");
                staList.add(staid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return staList;
        }

    @Override
    public int[] countMemberByAge() {
       int [] result=new int[5];
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement1= null;
        ResultSet resultSet1=null;
        PreparedStatement statement2= null;
        ResultSet resultSet2=null;
        PreparedStatement statement3= null;
        ResultSet resultSet3=null;
        PreparedStatement statement4= null;
        ResultSet resultSet4=null;
        PreparedStatement statement5= null;
        ResultSet resultSet5=null;
        try {
            statement1=connection.prepareStatement("SELECT count(*) number FROM memberinfo WHERE age>=0 AND age<20 AND state=1");
            resultSet1=statement1.executeQuery();
            while (resultSet1.next()){
                result[0]=resultSet1.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement2=connection.prepareStatement("SELECT count(*) number FROM memberinfo WHERE age>=20 AND age<30 AND state=1");
            resultSet2=statement2.executeQuery();
            while (resultSet2.next()){
                result[1]=resultSet2.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement3=connection.prepareStatement("SELECT count(*) number FROM memberinfo WHERE age>=30 AND age<40 AND state=1");
            resultSet3=statement3.executeQuery();
            while (resultSet3.next()){
                result[2]=resultSet3.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement4=connection.prepareStatement("SELECT count(*) number FROM memberinfo WHERE age>=40 AND age<50 AND state=1");
            resultSet4=statement4.executeQuery();
            while (resultSet1.next()){
                result[3]=resultSet4.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement5=connection.prepareStatement("SELECT count(*) number FROM memberinfo WHERE age>=50 AND state=1");
            resultSet5=statement5.executeQuery();
            while (resultSet5.next()){
                result[4]=resultSet5.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
return result;
    }

}
