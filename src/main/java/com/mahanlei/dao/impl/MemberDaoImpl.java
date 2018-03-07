package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.MemberDao;
import com.mahanlei.model.MemberInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDaoImpl  implements MemberDao{
    private static MemberDaoImpl memberDao=new MemberDaoImpl();
    public static MemberDaoImpl getInstance(){
        return  memberDao;
    }
    DaoHelper daoHelper=new DaoHelperImpl();
    public Message addMember(MemberInfo memberInfo,String password) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement1=null;
        PreparedStatement statement2=null;
        PreparedStatement statement3=null;

        ResultSet resultSet=null;
        try {
            statement1=connection.prepareStatement("SELECT * from memberinfo WHERE mid=?");
            statement1.setString(1,memberInfo.getMid());
            resultSet=statement1.executeQuery();
//System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(resultSet.next()){
                return Message.REGISTER_FAILED;
            }else {//不存在相同用户名，可插入
                try {
                    statement2=connection.prepareStatement("insert INTO memberinfo VALUES (?,?,?,?,?,?,?)");
                    statement2.setString(1,memberInfo.getMid());
                    statement2.setInt(2,memberInfo.getAge());
                    statement2.setString(3,memberInfo.getEmail());
                    statement2.setInt(4,memberInfo.getRank());
                    statement2.setInt(5,memberInfo.getPoints());
                    statement2.setInt(6,memberInfo.getState());
                    statement2.setString(7,memberInfo.getCode());

                    statement2.executeUpdate();

                    statement3=connection.prepareStatement("INSERT INTO userp VALUES (?,?)");
                    statement3.setString(1,memberInfo.getMid());
                    statement3.setString(2,password);
                    statement3.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement1);
            daoHelper.closePreparedStatement(statement2);
            daoHelper.closePreparedStatement(statement3);
            daoHelper.closeResult(resultSet);
        }
        return Message.REGISTER_SUCCESS;
    }

    public boolean activeMember(String code) {
        int num=0;
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;

        try {
            statement=connection.prepareStatement("update memberinfo set state=1 WHERE code=?");
            statement.setString(1,code);
           num= statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeConnection(connection);
            daoHelper.closePreparedStatement(statement);
        }
      return num!=0;
    }

    public MemberInfo getMemberInfo(String mid) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int age=0;
        String email=null;
        int rank=0;
        int points=0;
        int state=0;
        String  code=null;
        try {
            statement=connection.prepareStatement("SELECT * FROM memberinfo WHERE  mid=?");
            statement.setString(1,mid);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
             age=resultSet.getInt("age");
             email=resultSet.getString("email");
             rank=resultSet.getInt("rank");
             points=resultSet.getInt("points");
             state=resultSet.getInt("state");
             code=resultSet.getString("code");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
               MemberInfo memberInfo=new MemberInfo(mid,age,email,rank,points,state,code);
          return memberInfo;

    }

    public Message updateProfile(String  mid,int age, int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("update memberinfo SET age=?,state=? WHERE mid=? ");
            statement.setInt(1,age);
            statement.setInt(2,state);
            statement.setString(3,mid);
            n=statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if(n>0){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;

    }
}
