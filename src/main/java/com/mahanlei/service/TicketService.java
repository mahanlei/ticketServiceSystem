package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Seat;

import java.util.List;

public interface TicketService {
    /**
     * 获得某场演出的全部座位信息（已售，未售）
     * @param showId
     * @param stadiumId
     * @return
     */
    public List<Seat> getAllSeat(int showId,int stadiumId);

    /**
     * 获得不选座购买的用户的座位信息
     * @param showId
     * @param stadiumId
     * @param number
     * @return
     */
    public List<Seat> getUnoccupiedSeat(int showId,int stadiumId,int number);
    /**
     * 用户在选择座位，最多6个，获得价格信息
     * @return price座位价格
     */
    public Double selectSeat(Seat seat);
    /**
     * 用户确认票单（生成票单，票状态为未支付状态）
     * @param seatList
     * @param mid
     * @return
     */
    public Message confirmTickets(List<Seat> seatList,String mid);
    /**
     * 用户完成支付（票状态转为预定成功未消费）
     * @param tid
     * @return 用户实际支付的价钱
     */
    public double doPay(int tid,int discountType);

    /**
     * 用户退票（票状态转为退订状态）
     * @param tid
     * @return
     */
    public Message refuned(int tid);

    /**
     * 获得某张票单的状态，是否是已支付状态
     * @param tid
     * @return
     */
    public int getTicketState(int tid);

    /**
     * 检票（票状态转为已消费）
     * @param tid
     * @return
     */
    public Message checkTicket(int tid);



}
