package com.mahanlei.dao.impl;

import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{
private static UserDaoImpl userDao=new UserDaoImpl();
public static UserDaoImpl getInstance(){
    return userDao;
}
    public String findPassword(String uid) {
        String passw = null;
        DaoHelper daoHelper=new DaoHelperImpl();
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement= null;
        ResultSet resultSet=null;
        try {
            statement = connection.prepareStatement("select password FROM  userp WHERE uid=?");
            statement.setString(1,uid);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                passw=resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeResult(resultSet);
        }
        return passw;
    }
}
