package com.mahanlei.dao.impl;

import com.mahanlei.Util.Message;
import com.mahanlei.dao.DaoHelper;
import com.mahanlei.dao.TicketDao;
import com.mahanlei.model.Seat;
import com.mahanlei.model.Ticket;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDaoImpl implements TicketDao {
    private static TicketDaoImpl ticketDao=new TicketDaoImpl();

    public static  TicketDaoImpl getTicketDao() {
        return ticketDao;
    }

    DaoHelper daoHelper= new DaoHelperImpl();

    public List<Seat> getAllSeat(int showId, int stadiumId) {
        Connection connection=daoHelper.getConnection();
        List<Seat> seatList=new ArrayList<Seat>();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int seatRow=0;
        int seatColumn=0;
        double price=0.0;
        int state=0;
        try {
            statement=connection.prepareStatement("SELECT seatRow,seatColumn,price,state FROM seatinfo WHERE showId=? AND stadiumId=?");
            statement.setInt(1,showId);
            statement.setInt(2,stadiumId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                seatRow=resultSet.getInt("seatRow");
                seatColumn=resultSet.getInt("seatColumn");
                price=resultSet.getDouble("price");
                state=resultSet.getInt("state");
                Seat seat=new Seat(showId,stadiumId,seatRow,seatColumn,price,state);
                seatList.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return seatList;
    }

    public List<Seat> getSeats(int showId, int stadiumId, int row) {
        Connection connection=daoHelper.getConnection();
        List<Seat> seatList=new ArrayList<Seat>();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int seatRow=0;
        int seatColumn=0;
        double price=0.0;
        int state=0;
        try {
            statement=connection.prepareStatement("SELECT seatColumn,price,state FROM seatinfo WHERE showId=? AND stadiumId=? AND seatRow=?");
            statement.setInt(1,showId);
            statement.setInt(2,stadiumId);
            statement.setInt(3,row);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                seatColumn=resultSet.getInt("seatColumn");
                price=resultSet.getDouble("price");
                state=resultSet.getInt("state");
                Seat seat=new Seat(showId,stadiumId,row,seatColumn,price,state);
                seatList.add(seat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return seatList;
    }

    public List<Seat> getUnoccupiedSeat(int showId, int stadiumId, int number) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Seat> seatList=new ArrayList<Seat>();
        int seatRow=0;
        int seatColumn=0;
        double price=0.0;
        try {
            statement=connection.prepareStatement("SELECT seatRow,seatColumn,price FROM seatinfo WHERE showId=? AND stadiumId=? AND state=0");
            statement.setInt(1,showId);
            statement.setInt(2,stadiumId);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                seatRow=resultSet.getInt("seatRow");
                seatColumn=resultSet.getInt("seatColumn");
                price=resultSet.getDouble("price");
                seatList.add(new Seat(showId,stadiumId,seatRow,seatColumn,price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
if(seatList.size()>=number){
            return  seatList.subList(0,number);
}else return null;
    }

    public Double getSeatPrice(int showId, int stadiumId, int seatRow, int seatColumn) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;

        double price=0.0;

        try {
            statement=connection.prepareStatement("SELECT price FROM seatinfo WHERE showId=? AND stadiumId=? AND seatRow=? AND seatColumn=?");
            statement.setInt(1,showId);
            statement.setInt(2,stadiumId);
            statement.setInt(3,seatRow);
            statement.setInt(4,seatColumn);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                price=resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return price;
    }

    public Message updateSeat(List<Seat> seatList) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<seatList.size();i++) {
            try {
                statement = connection.prepareStatement("UPDATE seatinfo SET state=? WHERE showId=? " +
                        "AND stadiumId=? AND seatRow=? AND seatColumn=?");
                statement.setInt(1,seatList.get(i).getState() );
                statement.setInt(2,seatList.get(i).getShowId());
                statement.setInt(3,seatList.get(i).getStadiumId());
                statement.setInt(4,seatList.get(i).getSeatRow());
                statement.setInt(5,seatList.get(i).getSeatColumn());
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

        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return Message.UPDATE_SUCCESS;

    }

    public Message updateSeatState(Seat seat) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement = connection.prepareStatement("UPDATE seatinfo SET state=? WHERE showId=? " +
                    "AND stadiumId=? AND seatRow=? AND seatColumn=?");
            statement.setInt(1,seat.getState() );
            statement.setInt(2,seat.getShowId());
            statement.setInt(3,seat.getStadiumId());
            statement.setInt(4,seat.getSeatRow());
            statement.setInt(5,seat.getSeatColumn());
          n=  statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(n!=0){
            return Message.UPDATE_SUCCESS;
        }
        else return Message.UPDATE_FAILED;
    }

    public Message updateTicketState(int tid, int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE ticket set state=? WHERE tid=?");
            statement.setInt(1,state);
            statement.setInt(2,tid);
           n= statement.executeUpdate();
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

    public Message updateTicketPayPrice(int tid, double price) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE ticket SET payPrice=? WHERE tid=?");
            statement.setDouble(1,price);
            statement.setInt(2,tid);
            n=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if (n>0){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;
    }

    public Message updateTicketRefunedTime(int tid, Date refunedTime) {
        Timestamp timeStamp = new Timestamp(refunedTime.getTime());
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE ticket SET refunedTime=? WHERE tid=?");
            statement.setTimestamp(1,timeStamp);
            statement.setInt(2,tid);
            n=statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        if (n>0){
            return Message.UPDATE_SUCCESS;
        }else return Message.UPDATE_FAILED;
    }

    public Message updateMemBill(String mid, double money) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE memberbill set balance=balance-?,consumption=consumption+? WHERE mid=?");
            statement.setDouble(1,money);
            statement.setDouble(2,money);
            statement.setString(3,mid);
            n= statement.executeUpdate();
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

    public Message updateProfit(String uid, double money) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE profilt set profilt=profilt+? WHERE uid=?");
            statement.setDouble(1,money);
            statement.setString(2,uid);
            n= statement.executeUpdate();
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

    public Message updatePoints(String mid, int points) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        int n=0;
        try {
            statement=connection.prepareStatement("UPDATE memberinfo set points=points+? WHERE mid=?");
            statement.setInt(1,points);
            statement.setString(2,mid);
            n= statement.executeUpdate();
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

    public Ticket getTicketInfo(int tid) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
       String mid=null;
        int showId=0;
        int stadiumId=0;
        int seatRow=0;
        int seatColumn=0;
        int state=0;
        Date createdTime=null;
        Date refunedTime=null;
        double payPrice=0.0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            statement=connection.prepareStatement("SELECT * FROM ticket WHERE tid=?");
            statement.setInt(1,tid);
          resultSet =  statement.executeQuery();
          while (resultSet.next()){
              mid=resultSet.getString("mid");
              showId=resultSet.getInt("showId");
              stadiumId=resultSet.getInt("stadiumId");
              seatRow=resultSet.getInt("seatRow");
              seatColumn=resultSet.getInt("seatColumn");
              state=resultSet.getInt("state");
            createdTime=resultSet.getTimestamp("createdTime");
//           createdTime=new Date(createdTimeStamp.getTime());
           refunedTime=resultSet.getTimestamp("refunedTime");
//            refunedTime=new Date(refunedTimeStamp.getTime());
              payPrice=resultSet.getDouble("payPrice");
          }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        Ticket ticket=new Ticket(tid,mid,showId,stadiumId,seatRow,seatColumn,state,createdTime,refunedTime,payPrice);

        return ticket;
    }

    public Message addTicket(List<Ticket> tickets) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i=0;i<tickets.size();i++) {
            try {
                statement = connection.prepareStatement("INSERT INTO ticket (mid, showId, stadiumId, seatRow, seatColumn, state,createdTime ,payPrice) VALUES (?,?,?,?,?,?,?,?)");
                statement.setString(1,tickets.get(i).getMid());
                statement.setInt(2,tickets.get(i).getShowId());
                statement.setInt(3,tickets.get(i).getStadiumId());
                statement.setInt(4,tickets.get(i).getSeatRow());
                statement.setInt(5,tickets.get(i).getSeatColumn());
                statement.setInt(6,tickets.get(i).getState());
                Timestamp timestamp=new Timestamp(tickets.get(i).getCreatedTime().getTime());
                statement.setTimestamp(7,timestamp);
                statement.setDouble(8,tickets.get(i).getPayPrice());
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
                return Message.SELECT_FAILED;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }finally {
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
return Message.SELECT_SUCCESS;
    }

    public List<Integer> getTid(String mid, int showId, int stadiumId,int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
       List<Integer> tidList=new ArrayList<Integer>();
        try {
            statement=connection.prepareStatement("SELECT tid FROM ticket WHERE mid=? AND showId=? AND  stadiumId=? AND state=?");
            statement.setString(1,mid);
            statement.setInt(2,showId);
            statement.setInt(3,stadiumId);
            statement.setInt(4,state);
            resultSet =  statement.executeQuery();
            while (resultSet.next()){
               int  tid=resultSet.getInt("tid");
               tidList.add(tid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }

        return tidList;
    }

    public int getATid(String mid, int showId, int stadiumId, int seatRow, int seatColumn) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int  tid=0;
        try {
            statement=connection.prepareStatement("SELECT tid FROM ticket WHERE mid=? AND showId=? AND  stadiumId=? AND seatRow=? AND seatColumn=? AND state=1");
            statement.setString(1,mid);
            statement.setInt(2,showId);
            statement.setInt(3,stadiumId);
            statement.setInt(4,seatRow);
            statement.setInt(5,seatColumn);
            resultSet =  statement.executeQuery();
            while (resultSet.next()){
                tid=resultSet.getInt("tid");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }

        return tid;
    }


    public List<Integer> getMyTicketsId(String mid, int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Integer> tidList=new ArrayList<Integer>();
        try {
            statement=connection.prepareStatement("SELECT tid FROM ticket WHERE mid=? AND state=?");
            statement.setString(1,mid);
            statement.setInt(2,state);
            resultSet =  statement.executeQuery();
            while (resultSet.next()){
                int  tid=resultSet.getInt("tid");
                tidList.add(tid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }

        return tidList;
    }

    @Override
    public List<Integer> getStaTickets(int stadiumId, int state) {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        List<Integer> tidList=new ArrayList<Integer>();
        try {
            statement=connection.prepareStatement("SELECT tid FROM ticket WHERE stadiumId=? AND state=?");
            statement.setInt(1,stadiumId);
            statement.setInt(2,state);
            resultSet =  statement.executeQuery();
            while (resultSet.next()){
                int  tid=resultSet.getInt("tid");
                tidList.add(tid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }

        return tidList;
    }

    @Override
    public int getShowTickets(int showId, int stadiumId, int state)  {
        Connection connection=daoHelper.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int result=0;
        try {
            statement=connection.prepareStatement("SELECT count(*) as number FROM ticket WHERE showId=? AND stadiumId=? AND state=?");
            statement.setInt(1,showId);
            statement.setInt(2,stadiumId);
            statement.setInt(3,state);
            resultSet=statement.executeQuery();
            while (resultSet.next()){
                result=resultSet.getInt("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            daoHelper.closeResult(resultSet);
            daoHelper.closePreparedStatement(statement);
            daoHelper.closeConnection(connection);
        }
        return result;

    }

}
