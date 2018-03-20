package com.mahanlei.dao;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Seat;
import com.mahanlei.model.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketDao {
    /**
     * 获得某场演出的全部座位信息（已售，未售）
     * @param showId
     * @param stadiumId
     * @return
     */
    public List<Seat> getAllSeat(int showId, int stadiumId);
public List<Seat> getSeats(int showId, int stadiumId,int row);
    /**
     * 获得未售的所有座位
     * @param showId
     * @param stadiumId
     * @param number
     * @return
     */
    public List<Seat> getUnoccupiedSeat(int showId,int stadiumId,int number);

    /**
     * 获得某个座位的价格
     * @param showId
     * @param stadiumId
     * @param seatRow
     * @param seatColumn
     * @return
     */
    public Double getSeatPrice(int showId,int stadiumId,int seatRow,int seatColumn);
    /**
     * 更新座位的状态 0，1
     * @param seatList
     * @return
     */
    public Message updateSeat(List<Seat> seatList);

    /**
     * 将某个座位状态置为0，1
     * @param seat
     * @return
     */
    public Message updateSeatState(Seat seat);
    /**
     * 更新票的状态 0，1，2，3,4
     * @param tid
     * @return
     */
    public Message updateTicketState(int tid,int state);

    /**
     * 将实际价格存入数据库表ticket中
     * @param tid
     * @param price
     * @return
     */
    public Message updateTicketPayPrice(int tid,double price);

    /**
     * 更新票单的退订时间
     * @param tid
     * @param refunedTime
     * @return
     */
    public Message updateTicketRefunedTime(int tid,Date refunedTime);
    /**
     * 更新用户的账户余额，消费总额，会员
     * @param mid
     * @param money
     * @return
     */

    public Message updateMemBill(String mid,double money);
    /**
     * 更新场馆与经理的分红
     * @param uid
     * @param money
     * @return
     */
    public Message updateProfit(String uid,double money);
    /**
     * 根据支付的情况更新用户的积分情况
     * @param mid,points
     * @return
     */
    public Message updatePoints(String mid,int points);

    /**
     * 获得某张票单的信息
     * @param tid
     * @return
     */
    public Ticket getTicketInfo(int tid);

    /**
     * 生成用户订单（至少一个）
     * @param tickets
     * @return
     */
    public Message addTicket(List<Ticket> tickets);

    /**
     * 获得某用户对于某个演出下的所有票单
     * @param mid
     * @param showId
     * @param stadiumId
     * @return
     */
    public List<Integer> getTid(String mid,int showId,int stadiumId,int state);
    /**
     * 获取某用户的某种类型票务信息
     * @param mid
     * @param state
     * @return
     */
    public List<Integer> getMyTicketsId(String mid,int state);


}
