package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.SeatDao;
import com.mahanlei.model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SeatDaoImpl implements SeatDao {
private static SeatDaoImpl seatDao=new SeatDaoImpl();

    public static SeatDaoImpl getSeatDao() {
        return seatDao;
    }
DaoHelper daoHelper=new DaoHelperImpl();
    @Override
    public Message addSeats(List<Seat> seats) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<seats.size();i++){
            try {
                statement=connection.prepareStatement("INSERT INTO seatinfo VALUES (?,?,?,?,?,?)");
                statement.setInt(1,seats.get(i).getShowId());
                statement.setInt(2,seats.get(i).getStadiumId());
                statement.setInt(3,seats.get(i).getSeatRow());
                statement.setInt(4,seats.get(i).getSeatColumn());
                statement.setDouble(5,seats.get(i).getPrice());
                statement.setInt(6,seats.get(i).getState());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                return Message.UPDATE_FAILED;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {

            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return Message.UPDATE_SUCCESS;
        }

    }

