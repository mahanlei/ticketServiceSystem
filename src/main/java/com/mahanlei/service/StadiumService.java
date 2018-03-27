package com.mahanlei.service;

import com.mahanlei.Util.Message;
import com.mahanlei.model.StadiumInfo;
import com.mahanlei.model.Ticket;

import java.util.List;

public interface StadiumService {
    /**
     * 获取某场馆的全部信息
     * @param stadiumId
     * @return
     */
    public StadiumInfo getStadiumInfo(int stadiumId);

    /**
     * 场馆注册，插入信息，但是状态置为0
     * @param stadiumInfo
     * @return
     */
    public Message addStadium(StadiumInfo stadiumInfo,String password);

    /**
     * 场馆注册申请/修改信息得到审批，状态置为1
     * @param stadiumId
     * @return
     */
    public Message activeStadium(int stadiumId);

    /**
     * 场馆申请修改信息（场馆名称），先改掉然后将状态置为2,同时生成一条申请记录
     * @param stadiumId
     * @param staName
     * @return
     */
    public Message updateStaInfo(int stadiumId,String staName);

    /**
     * 线下购票支付，修改Ticket状态
     * @param ticketList
     * @return
     */
    public Message doPay(List<Integer> ticketList);

    /**
     * 线下购票取消支付，修改Ticket与seat状态
     * @param ticketList
     * @return
     */
    public Message doUnPay(List<Integer> ticketList);

}
