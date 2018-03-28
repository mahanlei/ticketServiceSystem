package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.ApplicationDao;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private static ApplicationDaoImpl applicationDao=new ApplicationDaoImpl();

    public static ApplicationDaoImpl getApplicationDao() {
        return applicationDao;
    }
DaoHelper daoHelper=new DaoHelperImpl();
    @Override
    public List<Application> getApplications(int type, int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int aid=0;
        int stadiumId=0;
        String staName=null;
        String address=null;
        int seatRows=0;
        int seatColumns=0;
        List<Application>  applicationList=new ArrayList<>();
        try {
            statement=connection.prepareStatement("SELECT * FROM application WHERE type=? AND state=?");
            statement.setInt(1,type);
            statement.setInt(2,state);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                aid=resultSet.getInt("aid");
                stadiumId=resultSet.getInt("stadiumId");
                staName=resultSet.getString("staName");
                address=resultSet.getString("address");
                seatRows=resultSet.getInt("seatRows");
                seatColumns=resultSet.getInt("seatColumns");
                Application application=new Application(aid,stadiumId,state,type,staName,address,seatRows,seatColumns);
                applicationList.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);

        }
        return applicationList;
    }

    @Override
    public Message agreeApplication(int aid,int stadiumId) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement1=null;
        PreparedStatement statement2=null;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement1=connection.prepareStatement("UPDATE application SET state=1 WHERE aid=?");
            statement1.setInt(1,aid);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement2=connection.prepareStatement("UPDATE stadiuminfo SET state=1 WHERE stadiumid=?");
            statement2.setInt(1,stadiumId);
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
        return Message.UPDATE_SUCCESS;

    }
}
