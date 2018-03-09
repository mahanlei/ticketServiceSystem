package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.Util.ShowType;
import com.mahanlei.Util.TransDataType;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.ShowDao;
import com.mahanlei.model.ShowInfo;
import com.mahanlei.model.ShowInfoBrief;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowDaoImpl implements ShowDao {
    private  static ShowDaoImpl showDao=new ShowDaoImpl();
    public static ShowDaoImpl getInstance(){
        return showDao;
    }
    DaoHelper daoHelper=new DaoHelperImpl();
    public List<ShowInfoBrief> getAllShowInfo() {
        List<ShowInfoBrief> list=new ArrayList<ShowInfoBrief>();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Connection connection=daoHelper.getConnection();
        try {
            statement=connection.prepareStatement("SELECT showId,name,type,picture FROM showInfo");
           resultSet= statement.executeQuery();
            while (resultSet.next()){
              int showID=resultSet.getInt("showId");
              String name=resultSet.getString("name");
              int type=resultSet.getInt("type");
                ShowType showType= TransDataType.intToShowType(type);
              String picture=resultSet.getString("picture");
              ShowInfoBrief showInfoBrief=new ShowInfoBrief(showID,name,showType,picture);
              list.add(showInfoBrief);
      }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return list;
    }


    public ShowInfo getShowInfo(int showId) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        int showID=0;
        String name=null;
        int stadiumId=0;
        String address=null;
        String staName=null;
        int seatRows=0;
        int seatColumns=0;
        Date startTime=null;
        Date endTime=null;
        ShowType showType=null;
        String picture=null;
        String description=null;


        try {
            statement=connection.prepareStatement("SELECT * FROM showInfo  ,stadiuminfo WHERE showInfo.stadiumId=stadiuminfo.stadiumId AND showInfo.showId=?  ");
            statement.setInt(1,showId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
               showID=resultSet.getInt("showId");
                name=resultSet.getString("name");
                stadiumId=resultSet.getInt("stadiumId");
               address=resultSet.getString("address");
               staName=resultSet.getString("staName");
                startTime=resultSet.getTimestamp("startTime");
                endTime=resultSet.getTimestamp("endTime");
                int type=resultSet.getInt("type");
                 showType= TransDataType.intToShowType(type);
                 picture=resultSet.getString("picture");
              description=resultSet.getString("description");
                seatRows=resultSet.getInt("seatRows");
                seatColumns=resultSet.getInt("seatColumns");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }

        ShowInfo showInfo=new ShowInfo(showID,name,stadiumId,staName,address,seatRows,seatColumns,startTime,endTime,showType,picture,description);
        return showInfo;
    }

    public Message addAShow(ShowInfo showInfo) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int i=0;
        int showID=showInfo.getShowId();
        String name=showInfo.getName();
        int stadiumId=showInfo.getStadiumId();
//        String address=showInfo.getAddress();
//        String staName=showInfo.getStaName();
//        int seatRows=showInfo.getSeatRows();
//        int seatColumns=showInfo.getSeatColumns();
        Date startTime=showInfo.getStartTime();
        Timestamp startTimeStamp = new Timestamp(startTime.getTime());
        Date endTime=showInfo.getEndTime();
        Timestamp endTimeStamp = new Timestamp(endTime.getTime());
        ShowType showType=showInfo.getType();
        int type=TransDataType.showTypeToInt(showType);
        String picture=showInfo.getPicture();
        String description=showInfo.getDescription();
        try {
            statement=connection.prepareStatement("INSERT INTO showInfo (name,stadiumId,startTime,endTime,type,picture,description)" +
                    "VALUES (?,?,?,?,?,?,?,)");
            statement.setString(1,name);
            statement.setInt(2,stadiumId);
            statement.setTimestamp(3,startTimeStamp);
            statement.setTimestamp(4,endTimeStamp);
            statement.setInt(5,type);
            statement.setString(6,picture);
            statement.setString(7,description);
           i= statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if(i>0){
            return Message.RELEASE_SUCCESS;
        }else {
            return Message.RELEASE_FAILED;
        }

    }
}
