package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.Seat;
import com.mahanlei.model.Ticket;
import com.mahanlei.model.TicketInfoBrief;

import java.util.List;

public interface TicketService {
    /**
     * 获得每一行中座位的状态
     * @param showId
     * @param stadiumId
     * @param row
     * @return
     */
    public List<Seat> getSeats(int showId,int stadiumId,int row);
    /**
     * 获得不选座购买的用户的座位信息
     * @param showId
     * @param stadiumId
     * @param number
     * @return
     */
    public List<Seat> getUnoccupiedSeat(int showId,int stadiumId,int number);
    /**
     * 用户在选择座位，获得每个座位价格信息
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
     * @param totalPayPrice
     * @return 支付是否成功
     */
    public Message doPay(List<Integer> ticketList,double totalPayPrice);

    /**
     * 用户未在15分钟内完成支付，关闭交易，恢复座位状态
     * @param ticketList
     * @return
     */
    public Message closeDeal(List<Integer> ticketList);
    /**
     * 用户退票（票状态转为退订状态）
     * @param tid
     * @return
     */
    public Message refuned(int tid);

    /**
     * 获得某张票单的信息
     * @param tid
     * @return
     */
    public Ticket getTicketInfo(int tid);

    /**
     * 检票（票状态转为已消费）
     * @param tid
     * @return
     */
    public Message checkTicket(int tid);

    /**
     * 获得某用户某场演出的全部票单
     * @param mid
     * @param showId
     * @param stadiumId
     * @return
     */
    public List<Integer> getTid(String mid,int showId,int stadiumId,int state);
    public int getATid(String mid,int showId,int stadiumId,int seatRow,int seatColumn);

    /**
     * 获取某用户的某种类型票务信息
     * @param mid
     * @param state
     * @return
     */
    public List<TicketInfoBrief> getMyTicketInfo(String mid, int state);
    /**
     * 选择了优惠券后获得实际支付价格
     * @param tid
     * @param discountType
     * @return
     */
    public double getDisPrice(int tid,int discountType);
}
