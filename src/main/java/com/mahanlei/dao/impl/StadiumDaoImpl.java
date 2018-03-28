package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.StadiumDao;
import com.mahanlei.model.Application;
import com.mahanlei.model.StadiumInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StadiumDaoImpl implements StadiumDao {
    private static StadiumDaoImpl stadiumDao=new StadiumDaoImpl();

    public static StadiumDaoImpl getStadiumDao() {
        return stadiumDao;
    }
    DaoHelper daoHelper= DaoHelperImpl.getDaoHelper();

    public StadiumInfo getStadiumInfo(int stadiumId) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String staName=null;
        String address=null;
        int seatRows=0;
        int seatColumns=0;
        int state=0;
        try {
            statement=connection.prepareStatement("SELECT * FROM stadiuminfo WHERE stadiumid=?");
            statement.setInt(1,stadiumId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                staName=resultSet.getString("staName");
                address=resultSet.getString("address");
                seatRows=resultSet.getInt("seatRows");
                seatColumns=resultSet.getInt("seatColumns");
                state=resultSet.getInt("state");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
      StadiumInfo  stadiumInfo=new StadiumInfo(stadiumId,staName,address,seatRows,seatColumns,state,TransDataType.intToString(stadiumId));
return stadiumInfo;
    }

    @Override
    public int getStaId(String staName, String address) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int staId=0;
        try {
            statement=connection.prepareStatement("SELECT stadiumid FROM stadiuminfo WHERE staName=? AND address=?");
            statement.setString(1,staName);
            statement.setString(2,address);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
staId=resultSet.getInt("stadiumid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return staId;
    }

    public Message addStadium(StadiumInfo stadiumInfo) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;

        try {
            statement=connection.prepareStatement("INSERT INTO stadiuminfo(staName,address,seatRows,seatColumns,state) VALUES (?,?,?,?,?)");
            statement.setString(1,stadiumInfo.getStaName());
            statement.setString(2,stadiumInfo.getAddress());
            statement.setInt(3,stadiumInfo.getSeatRows());
            statement.setInt(4,stadiumInfo.getSeatColumns());
            statement.setInt(5,stadiumInfo.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
            return Message.UPDATE_SUCCESS;
    }

    @Override
    public Message addStaPass(int staId, String pass) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement("INSERT INTO userp VALUES (?,?)");
            statement.setString(1, TransDataType.intToString(staId));
            statement.setString(2, pass);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
       return Message.UPDATE_SUCCESS;
    }

    @Override
    public Message addStaProfit(int staId) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        double profit=0.0;
        try {
            statement = connection.prepareStatement("INSERT INTO profilt VALUES (?,?)");
            statement.setString(1, TransDataType.intToString(staId));
            statement.setDouble(2, profit);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return Message.UPDATE_SUCCESS;
    }

    public Message activeStadium(int stadiumId) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE  stadiuminfo SET state=1 WHERE stadiumid=?");
            statement.setInt(1,stadiumId);
            n=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if(n!=0){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;
    }

    public Message updateStaInfo(int stadiumId, String staName) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE  stadiuminfo SET staName=?, state=2 WHERE stadiumid=?");
            statement.setString(1,staName);
            statement.setInt(2,stadiumId);
            n=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if(n!=0){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;
    }

    @Override
    public Message addApplication(Application application) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement("INSERT INTO application (stadiumId,state,type,staName,address, seatRows,seatColumns) VALUES (?,?,?,?,?,?,?)");
            statement.setInt(1,application.getStadiumId());
            statement.setInt(2,application.getState());
            statement.setInt(3,application.getType());
            statement.setString(4,application.getStaName());
            statement.setString(5,application.getAddress());
            statement.setInt(6,application.getSeatRows());
            statement.setInt(7,application.getSeatColumns());
          statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
            return Message.APPLY_SUCCESS;
    }
}
